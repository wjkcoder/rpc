package cn.phorcys.rpc.framework;


import java.io.Serializable;

public class Url implements Serializable {
    private String scheme =ProtocolEnum.PROTOCOL_HTTP;
    private String host;
    private Integer port;

    public Url(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
