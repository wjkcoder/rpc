package cn.phorcys.rpc.consumer;

import cn.phorcys.rpc.framework.ProxyFactory;
import cn.phorcys.rpc.provider.api.DemoService;

public class Consumer {
    public static void main(String[] args) {
        DemoService proxy = ProxyFactory.getProxy(DemoService.class);
        String result = proxy.getDemo();
        System.out.println(result);
    }
}
