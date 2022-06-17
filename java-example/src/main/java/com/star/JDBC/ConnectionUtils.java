package com.star.JDBC;

import com.star.logging.log4j.Log4jDemo;
import com.star.properties.DynamicLoadProperties;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Spring
 */
public class ConnectionUtils {
    static Logger logger = Logger.getLogger(ConnectionUtils.class);

    // 获取数据库的连接
    public static Connection getMysqlConnection(String driver, String url, String username, String password) {
        // 获取properties配置文件
        Properties prop = DynamicLoadProperties.loadProperties("databases.properties");
        PreparedStatement pstmt = null;
        try {
            //1. 加载驱动
            Class.forName(prop.getProperty(driver));
            // 2. 获得连接
            Connection conn = DriverManager.getConnection(
                    prop.getProperty(url),
                    prop.getProperty(username),
                    prop.getProperty(password)
            );
            return conn;
        } catch (ClassNotFoundException e) {
            logger.error("驱动类加载失败...", e);
        } catch (SQLException e) {
            logger.error("database access error occurs or the url is null.", e);
        }
        return null;
    }
}