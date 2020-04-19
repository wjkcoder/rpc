package cn.phorcys.rpc.register.zookeeper;

import cn.phorcys.rpc.framework.Url;
import cn.phorcys.rpc.framework.UrlUtils;
import cn.phorcys.rpc.register.RemoteRegister;
import cn.phorcys.rpc.register.loadbanace.LoadBalance;
import cn.phorcys.rpc.register.loadbanace.RandomBalance;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ZkRegister implements RemoteRegister {

    private String zkAddress = "127.0.0.1:2181";
    private Integer sessionTimeout = 20000;
    private ZooKeeper zkClient;
    private String ROOT = "/dubbo/services";
    private static final Logger logger = Logger.getLogger(ZkRegister.class.getName());

    public ZkRegister() {
        try {
            this.zkClient = new ZooKeeper(zkAddress, sessionTimeout, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String getPath(String  serviceName){
        return ROOT + "/" + serviceName;
    }
    @Override
    public void register(String serviceName, Url url) {
        // 判断services目录下是否存在serviceName服务
        String servicePath = ROOT + "/" + serviceName;
        try {
            Stat exists = zkClient.exists(servicePath, false);
            if (exists == null) {
                logger.info(serviceName + " node not exits,now prepare to add");
                zkClient.create(servicePath, serviceName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            }
            // 获取serviceName节点下data
            // 存在serviceName，在serviceName路径下add node
            String newNode = servicePath + "/" + url.getHost() + ":" + url.getPort().toString();
            if (zkClient.exists(newNode, false) == null) {
                zkClient.create(newNode, newNode.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Url getUrl(String serviceName) {
        String path = getPath(serviceName);
        try {
            List<String> children = zkClient.getChildren(path, false);
            String pick = new RandomBalance().pick(children);
            return UrlUtils.needUrl(pick);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
