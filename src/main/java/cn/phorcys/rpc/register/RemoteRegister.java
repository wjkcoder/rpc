package cn.phorcys.rpc.register;

import cn.phorcys.rpc.framework.Url;

public interface RemoteRegister {
    void register(String serviceName, Url url);
    Url getUrl(String serviceName);
}
