package part2.Server.provider;

import part2.Server.serviceRegister.Impl.ZKServiceRegister;
import part2.Server.serviceRegister.ServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    //集合中存放服务的实例
    private Map<String,Object> interfaceProvider;

    private int port;
    private String host;

    private ServiceRegister serviceRegister;

    public ServiceProvider(String host,int port){
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        this.serviceRegister = new ZKServiceRegister();
    }

    public void provideServiceInterface(Object service){
        String serviceName = service.getClass().getName();
        Class<?>[] interfaceName = service.getClass().getInterfaces();

        for (Class<?> clazz:interfaceName){
            interfaceProvider.put(clazz.getName(),service);
            serviceRegister.register(clazz.getName(),new InetSocketAddress(host,port));
        }
    }

    public Object getService(String interfaceName){ return  interfaceProvider.get(interfaceName);}
}
