package cn.phorcys.rpc.protocol.http;

import cn.phorcys.rpc.framework.Invocation;
import cn.phorcys.rpc.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class HttpServerHandler {
    void handle(HttpServletRequest req, HttpServletResponse res){

        try {
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation o = (Invocation)ois.readObject();
            String serviceName = o.getServiceName();
            String api = o.getApi();
            Class[] paramTypes = o.getParamTypes();
            // 从本地注册列表拿到interface的实现类
            Class service = LocalRegister.getService(serviceName);
            Method method;
            Method[] methods = service.getMethods();
            method = service.getMethod(api,paramTypes);
            String result = (String)method.invoke(service.newInstance(), o.getParams());
            IOUtils.write(result,res.getOutputStream());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
