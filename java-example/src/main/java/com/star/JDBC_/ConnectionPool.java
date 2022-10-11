package com.star.JDBC_;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Spring
 * @date 2022/4/15 15:35
 * @describe : druid 数据库连接池
 */

public class ConnectionPool {
    static Logger logger = Logger.getLogger(ConnectionPool.class);

    // 使用配置文件
    public static void druidDemo() {

        try {
            logger.info("druid 数据库连接池demo");
            // 使用配置文件
            Properties prop = new Properties();
            // 配置文件路径没找到相对路径
            prop.load(new FileInputStream("E:\\04javaProject\\Sardine\\java-example\\src\\main\\resources\\druid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            Connection conn = dataSource.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select * from student");
            ResultSet rs = psmt.executeQuery();
            // 遍历结果集:
            while (rs.next()) {
                System.out.print(rs.getInt("sno") + " ");
                System.out.print(rs.getString("sname") + " ");
                System.out.print(rs.getString("ssex") + " ");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 不使用配置文件
    public static void druidDemo1() {

        try {
            logger.info("druid 数据库连接池demo");
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/sql50_1?characterEncoding=UTF-8&useSSL=false");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            Connection conn = dataSource.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select * from student");
            ResultSet rs = psmt.executeQuery();
            // 遍历结果集:
            while (rs.next()) {
                System.out.print(rs.getInt("sno") + " ");
                System.out.print(rs.getString("sname") + " ");
                System.out.print(rs.getString("ssex") + " ");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}