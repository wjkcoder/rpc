package cn.phorcys.rpc.register;

import cn.phorcys.rpc.framework.MapFile;
import cn.phorcys.rpc.framework.Url;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class RemoteRegistry  {
    private static Map<String, List<Url>> serviceMap = new HashMap<>();

    public static void register(String serviceName, Url url) {
        List<Url> urls = serviceMap.get(serviceName);
        if (Objects.isNull(urls)) {
            serviceMap.put(serviceName, Collections.singletonList(url));
        } else {
            urls.add(url);
        }
        MapFile.writeFile(serviceMap);

    }

    public static Url getUrl(String serviceName) {
        serviceMap = (Map<String, List<Url>>) MapFile.readFile();
        List<Url> urls = serviceMap.get(serviceName);
        if (Objects.isNull(urls)) {
            throw new RuntimeException("注册中心未找到可用服务节点:" + serviceName);
        }
        int size = urls.size();
        Random random = new Random();
        return urls.get(random.nextInt(size));

    }
}
