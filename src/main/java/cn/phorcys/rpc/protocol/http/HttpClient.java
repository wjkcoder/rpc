package cn.phorcys.rpc.protocol.http;

import cn.phorcys.rpc.framework.Invocation;
import cn.phorcys.rpc.framework.Url;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class HttpClient {

    public static String post (Url connect, Invocation invocation){
        Objects.requireNonNull(connect);
        try {
            URL url = null;
            try {
                url = new URL(connect.getScheme(), connect.getHost(), connect.getPort(), "/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/xml");
            OutputStream outputStream = connection.getOutputStream();

            try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
                oos.writeObject(invocation);
                oos.flush();

            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            InputStream inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
