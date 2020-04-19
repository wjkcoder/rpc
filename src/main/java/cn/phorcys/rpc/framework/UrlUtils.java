package cn.phorcys.rpc.framework;

public class UrlUtils {
    private static final String URL_FORMATTER = "";

    public static Url needUrl(String url) {
        String[] split = url.split(":");
        return new Url(split[0], Integer.valueOf(split[1]));
    }

    public static String needStr(Url url) {
        return url.getHost() + ":" + url.getPort();
    }
}
