package com.star.logging.jul;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author: liudw
 * @date: 2021-3-17 14:28
 */


// JUL (java.util.logging)
public class JULDemo {
    static Logger logger;

    static {
        // 获取日志记录对象
        logger = Logger.getLogger("com.star.logging.jul.JULDemo");
    }

    //
    public static void demo() {

        // 日志输出记录
        logger.info("log of info level");
        logger.log(Level.INFO, "another info log");

        // 通过占位符 方式输出变量值
        String name = "Aname";
        Integer age = 10;
        logger.log(Level.INFO, "用户信息:{0},年龄:{1}", new Object[]{name, age});

    }

    // 自定义日志级别
    public static void logLevel() {

        // 关闭系统默认配置
        logger.setUseParentHandlers(false);

        // 创建ConsoleHandler 日志输出到控制台
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);

        // 设置日志级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        logger.log(Level.SEVERE, "server");
        logger.log(Level.WARNING, "waring");
        logger.log(Level.INFO, "info");   // info 是jul 默认的日志级别，比info级别高的会输出，低的不会输出
        logger.log(Level.CONFIG, "config");
        logger.log(Level.FINE, "fine");
        logger.log(Level.FINER, "finer");
        logger.log(Level.FINEST, "finest");
    }

    // 输出日志到文件中
    public static void logToFile() throws IOException {

        // 关闭系统默认配置
        logger.setUseParentHandlers(false);

        // 创建FileHandler 设置日志输出路径
        FileHandler fileHandler = new FileHandler("java-example/log/all.log");
        // 创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();


        // 进行关联
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        // 设置日志级别
        logger.setLevel(Level.ALL);
        fileHandler.setLevel(Level.ALL);

        logger.log(Level.SEVERE, "server");
        logger.log(Level.WARNING, "waring");
        logger.log(Level.INFO, "info");
        logger.log(Level.CONFIG, "config");
        logger.log(Level.FINE, "fine");
        logger.log(Level.FINER, "finer");
        logger.log(Level.FINEST, "finest");
    }

}
