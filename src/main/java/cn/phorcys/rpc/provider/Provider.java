package cn.phorcys.rpc.provider;

import cn.phorcys.rpc.framework.Protocol;
import cn.phorcys.rpc.framework.ProtocolFactory;
import cn.phorcys.rpc.framework.Url;
import cn.phorcys.rpc.provider.api.DemoService;
import cn.phorcys.rpc.provider.impl.DemoServiceImpl;
import cn.phorcys.rpc.register.LocalRegister;
import cn.phorcys.rpc.register.RemoteRegistry;
import cn.phorcys.rpc.register.zookeeper.ZkRegister;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Provider {
    public static void main(String[] args) throws UnknownHostException {
        // 1.本地注册
        LocalRegister.register(DemoService.class.getName(), DemoServiceImpl.class);

        // 2.远程注册
            InetAddress localHost = InetAddress.getLocalHost();
        Url url = new Url(localHost.getHostAddress(), 8080);
        RemoteRegistry.register(DemoService.class.getSimpleName(), url);

        // 服务注册到zk
        ZkRegister zkClient = new ZkRegister();
        zkClient.register(DemoService.class.getSimpleName(), url);

        // 3.启动server
        Protocol server = ProtocolFactory.getProtocol();
        server.start(url);
    }
}
