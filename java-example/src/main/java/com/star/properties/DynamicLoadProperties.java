package com.star.properties;



import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * @author Spring
 */

// 动态获取 properties 文件
public class DynamicLoadProperties {
    static Logger logger = Logger.getLogger(DynamicLoadProperties.class);
    public static Properties loadProperties(String fileName) {

        try {
            Properties prop = new Properties();

            String prefixPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

            InputStream is = new FileInputStream(prefixPath + fileName);

            prop.load(is);
            return prop;
        } catch (FileNotFoundException e) {
            logger.error(fileName+"  is not found.",e);
        } catch (IOException e) {
            logger.error("文件状态错误.");
        }
        return null;
    }
}