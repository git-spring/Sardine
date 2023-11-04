package com.star.logging.jul;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author: liudw
 * @date: 2021-3-17 15:37
 */


// JUL使用配置文件
public class JULWithProperties {


    public static void logProperties() throws IOException {
        // 通过类加载器读取配置文件
        InputStream is = JULWithProperties.class.getClassLoader().getResourceAsStream("logging.properties");
        // 创建LogManager
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(is);

        Logger logger = Logger.getLogger("com.star.logging.jul.JULWithProperties");
        logger.log(Level.SEVERE, "server");
        logger.log(Level.WARNING, "waring");
        logger.log(Level.INFO, "info");
        logger.log(Level.CONFIG, "config");
        logger.log(Level.FINE, "fine");
        logger.log(Level.FINER, "finer");
        logger.log(Level.FINEST, "finest");

    }


}
