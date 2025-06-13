package test;

import moudle.Grid;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CreateGridFile {
    public static void main(String[] args) {
        // 1. 创建测试数据
        Map<String, Grid> gridMap = new HashMap<>();

        // 添加几个测试网格数据
        gridMap.put("grid001", new Grid("user1", "A区", "东侧公园", "一级", "垃圾分类问题"));
        gridMap.put("grid002", new Grid("user2", "B区", "商业中心", "二级", "噪音投诉"));
        gridMap.put("grid003", new Grid("user3", "C区", "住宅小区", "三级", "绿化维护"));

        // 2. 指定文件路径（建议使用完整路径）
        String filename = "grid.txt";

        // 3. 写入序列化文件
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(gridMap);
            System.out.println("文件已生成: " + new File(filename).getAbsolutePath());
            System.out.println("包含数据: " + gridMap);
        } catch (IOException e) {
            System.err.println("生成文件失败: ");
            e.printStackTrace();
        }
    }
}