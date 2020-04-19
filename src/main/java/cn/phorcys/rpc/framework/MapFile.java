package cn.phorcys.rpc.framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MapFile {
    public static void writeFile(Object o) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./map.txt"))) {
                oos.writeObject(o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readFile() {
        String filePath ="./map.txt";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
