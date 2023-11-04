package com.star.JDBC_;

import java.sql.*;

/**
 * @author Spring
 * @date 2022/4/19 10:02
 * @describe : JDBC 连接oracle测试
 */

public class OracleTest {

    public static void connectionOracle() {

        try {
            String url = "jdbc:oracle:thin:@192.168.56.115:1521:helowin";
            String username = "system";
            String password = "123456";
            Class.forName("oracle.jdbc.driver.OracleDriver");//加载数据驱动
            Connection conn = DriverManager.getConnection(url, username, password);// 连接数据库
            PreparedStatement psmt = conn.prepareStatement("select * from spring.test");
            ResultSet rs = psmt.executeQuery();
            // 遍历结果集:
            while (rs.next()) {
                System.out.print(rs.getInt("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connectionOracle();
    }
}