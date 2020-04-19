package cn.phorcys.rpc.protocol.dubbo;

import cn.phorcys.rpc.framework.Invocation;
import cn.phorcys.rpc.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation o = (Invocation) msg;
        String interfaceName = o.getServiceName();

        String api = o.getApi();
        Class[] paramTypes = o.getParamTypes();
        // 从本地注册列表拿到interface的实现类
        Class service = LocalRegister.getService(interfaceName);
        Method method = service.getMethod(api,paramTypes);
        String result = (String)method.invoke(service.newInstance(), o.getParams());
        ctx.writeAndFlush(result);
    }
}
