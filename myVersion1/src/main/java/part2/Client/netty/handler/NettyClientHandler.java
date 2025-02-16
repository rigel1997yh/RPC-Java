package part2.Client.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import part2.common.Message.RpcResponse;

public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    //这是SimpleChannelInboundHandler的核心方法，用于读取服务端返回的数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
        channelHandlerContext.channel().attr(key).set(rpcResponse);

        channelHandlerContext.close();
    }
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
