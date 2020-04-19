package cn.phorcys.rpc.protocol.dubbo;

import cn.phorcys.rpc.framework.Url;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyServer {
    private String hostName;
    private Integer port;

    public NettyServer(Url url) {
        this.hostName = url.getHost();
        this.port = url.getPort();
    }

    public void listen(Url url) {
        this.hostName = url.getHost();
        this.port = url.getPort();
    }
    public void start() {

        NioEventLoopGroup master = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(master, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyServerHandler());
            }
        });
        try {
            ChannelFuture future = bootstrap.bind(hostName,port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            master.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
