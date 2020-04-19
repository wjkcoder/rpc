package cn.phorcys.rpc.register;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {
    private static Map<String, Class> serviceMap=new HashMap<>();

    public static void register(String serviceName, Class cl) {
        serviceMap.put(serviceName, cl);

    }

    public static void setServiceMap(Map<String, Class> serviceMap) {
        LocalRegister.serviceMap = serviceMap;
    }
    public static Class getService(String serviceName){
        return serviceMap.get(serviceName);
    }
}
