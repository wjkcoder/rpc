package cn.phorcys.rpc.register;

import java.util.Iterator;
import java.util.ServiceLoader;

public class RegisterModeFactory {
    public static RemoteRegister getProtocol(){
        ServiceLoader<RemoteRegister> serviceLoader = ServiceLoader.load(RemoteRegister.class);
        Iterator<RemoteRegister> iterator = serviceLoader.iterator();
        return iterator.next();
    }
}
