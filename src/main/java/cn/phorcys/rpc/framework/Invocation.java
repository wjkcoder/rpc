package cn.phorcys.rpc.framework;

import java.io.Serializable;

public class Invocation implements Serializable {
    private String serviceName;
    private String api;
    private Class[] paramTypes;
    private Object[] params;

    public Invocation(String serviceName, String api, Class[] paramTypes, Object[] params) {
        this.serviceName = serviceName;
        this.api = api;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
