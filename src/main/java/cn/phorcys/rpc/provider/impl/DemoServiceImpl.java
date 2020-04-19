package cn.phorcys.rpc.provider.impl;

import cn.phorcys.rpc.provider.api.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String getDemo() {
        return "rpc call succeed";
    }
}
