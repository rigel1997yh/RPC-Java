package part2.Client.serviceCenter;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.net.InetSocketAddress;
import java.util.List;

public class ZKServiceCenter implements ServiceCenter{
    private CuratorFramework client;
    private static final String ROOT_PATH = "MyRPC";

    public ZKServiceCenter(){
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
    this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .sessionTimeoutMs(40000).retryPolicy(policy).namespace(ROOT_PATH).build();
    this.client.start();
        System.out.println("zk connected");
    }

    @Override
    public InetSocketAddress serviceDiscovery(String serciceName) {
        try{
            List<String> strings = client.getChildren().forPath("/"+serciceName);
            String string = strings.get(0);
            return parseAddress(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getServiceAddress (InetSocketAddress serverAddress){
        return  serverAddress.getHostName()+":"+serverAddress.getPort();

    }

    private InetSocketAddress parseAddress(String string) {
        String[] result = string.split(":");
        return  new InetSocketAddress(result[0],Integer.parseInt(result[1]));
    }
}
