package test;

import module.Gridman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CreateGridmanFile {
    public static void main(String[] args) {
        // 1. 准备数据
        Map<String, Gridman> gridmanMap = new HashMap<>();
        gridmanMap.put("user1", new Gridman("user1", "111111"));
        gridmanMap.put("user2", new Gridman("user2", "123456"));

        // 2. 生成序列化文件
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("gridman.txt"))) {
            oos.writeObject(gridmanMap);
            System.out.println("文件已重新生成（二进制格式，不可用文本编辑器打开）");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



