package cn.phorcys.rpc.protocol.http;

import cn.phorcys.rpc.framework.Invocation;
import cn.phorcys.rpc.framework.Protocol;
import cn.phorcys.rpc.framework.Url;

public class HttpProtocol implements Protocol {
    @Override
    public void start(Url url) {
        HttpServer server = new HttpServer();
        server.listen(url);
        server.start();
    }

    @Override
    public String send(Url url, Invocation invocation) {
        return HttpClient.post(url, invocation);
    }
}
