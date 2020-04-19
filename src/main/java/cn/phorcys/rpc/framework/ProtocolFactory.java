package cn.phorcys.rpc.framework;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ProtocolFactory {
    public static Protocol getProtocol(){
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();
    }
}
