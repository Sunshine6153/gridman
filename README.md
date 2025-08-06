# 东软环保公众监督平台 - 网格员端

本项目是东北大学软件学院大一基础编程实训的大作业，开发目标是实现“东软环保公众监督平台”中的 **网格员端** 应用，提供网格员身份登录、问题查看、上报审核等基础功能。

本项目使用 **JavaFX** 实现前端界面，采用 **MVC 架构** 进行逻辑分层，提升可维护性和扩展性。

## 🧑‍💻 作者信息

- 姓名：Sunshine6153  
- 学校：东北大学 软件学院  
- 项目时间：2025年
  
---

## 📁 项目结构概览
├── src/
│ ├── controller/ # 控制器逻辑（C）
│ ├── model/ # 数据模型（M）
│ ├── view/ # JavaFX 界面布局（V）
│ ├── gridlogin.java # 项目主入口
│ └── utils/ # 工具类（如文件读写）
│
├── data/
│ ├── grid.txt # 示例网格数据（由 testGrid() 生成）
│ ├── gridman.txt # 示例网格员数据（由 testGridMan() 生成）
│
├── resources/
│ └── images/ # 图标、背景等资源
│
├── README.md
└── ...

---

## 🚀 项目启动方式

1. 确保本地安装了 JDK（推荐 JDK 17+）和 JavaFX。
2. 使用 IDE（如 IntelliJ IDEA 或 Eclipse）打开本项目。
3. 配置 JavaFX SDK 路径和 VM options（如需）。
4. 运行主类：gridlogin.java

---

## 🧪 测试方法说明

在 `gridlogin` 类中，包含两个测试方法用于初始化必要数据：

```java
public static void testGrid();      // 生成 grid.txt 网格数据
public static void testGridMan();   // 生成 gridman.txt 网格员信息



