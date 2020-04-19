package cn.phorcys.rpc.framework;

public interface Protocol {
    void start(Url url);

    String send(Url url, Invocation invocation);
}
