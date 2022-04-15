package com.star.JDBC;

import com.star.properties.DynamicLoadProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Spring
 */
public class ConnectionUtils {

    // 获取数据库的连接
    public static Connection getMysqlConnection() {
        // 获取properties配置文件
        Properties prop = DynamicLoadProperties.loadProperties("databases.properties");
        PreparedStatement pstmt = null;
        try {
            //1. 加载驱动
            Class.forName(prop.getProperty("mysql.driver"));
            // 2. 获得连接
            Connection conn = DriverManager.getConnection(
                    prop.getProperty("mysql.url"),
                    prop.getProperty("mysql.username"),
                    prop.getProperty("mysql.password")
            );
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}