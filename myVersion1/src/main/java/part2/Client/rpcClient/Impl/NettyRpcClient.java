package part2.Client.rpcClient.Impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import part2.Client.rpcClient.RpcClient;
import part2.common.Message.RpcRequest;
import part2.common.Message.RpcResponse;

public class NettyRpcClient implements RpcClient {
    private String host;
    private int port;
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    public NettyRpcClient(String host, int port) {
        this.host=host;
        this.port=port;
    }
// neety客户端初始化
    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class);
                //NettyClientInitializer这里 配置netty对消息的处理机制
//                .handler(new NettyClientInitializer());
}
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        return null;
    }
}
