package common;

import module.GridDeliver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeliverFileutils {
    public static void writeMapobject(String fileName, Map map) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();  // 添加目录创建
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(map);
                oos.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map readMapObject(String fileName) {
        File file = new File(fileName);
        Map map = null;
        try {
            if (file.length() == 0) {
                map = new HashMap();
            } else {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                map = (Map<String, GridDeliver>) ois.readObject();
            }
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
