package cn.phorcys.rpc.protocol.dubbo;

import cn.phorcys.rpc.framework.Invocation;
import cn.phorcys.rpc.framework.Protocol;
import cn.phorcys.rpc.framework.Url;
import io.netty.channel.Channel;

public class DubboProtocol implements Protocol {
    @Override
    public void start(Url url) {
        NettyServer server = new NettyServer(url);
        server.start();
    }

    @Override
    public String send(Url url, Invocation invocation) {
        NettyClient client = new NettyClient(url);
        client.start();
        Channel channel = client.getChannel();
        channel.writeAndFlush(invocation);

        Channel read = channel.read();
        return null;
    }
}
