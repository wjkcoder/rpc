package cn.phorcys.rpc.protocol.dubbo;

import cn.phorcys.rpc.framework.Url;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private String host;
    private Integer port;
    private Channel channel;

    public NettyClient(Url url){
        this.host = url.getHost();
        this.port = url.getPort();
    }

    public void start() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("channel established");
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        //发起异步连接请求，绑定连接端口和host信息
        final ChannelFuture future;
        try {
            future = bootstrap.connect(host, port).sync();
            future.addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture arg0) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("连接服务器成功");

                    } else {
                        System.out.println("连接服务器失败");
                        future.cause().printStackTrace();
                        worker.shutdownGracefully(); //关闭线程组
                    }
                }
            });

            this.channel = future.channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
