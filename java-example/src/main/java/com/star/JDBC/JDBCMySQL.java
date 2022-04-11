package com.star.JDBC;


import java.sql.*;

/**
 * @author Spring
 */


public class JDBCMySQL {

    // 使用jdbc 连接MySQL
    public static void jdbcConnectMysqlTest() {

        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql50_1", "root", "123456");
            // 3.基本操作：执行SQL
            // 3.1获得执行SQL语句的对象
            Statement statement = conn.createStatement();
            // 3.2编写SQL语句:
            String sql = "select * from course;";
            // 3.3执行SQL:
            ResultSet rs = statement.executeQuery(sql);
            // 3.4遍历结果集:
            while (rs.next()) {
                System.out.print(rs.getInt("cno") + " ");
                System.out.print(rs.getString("cname") + " ");
                System.out.print(rs.getString("tno") + " ");
                System.out.println();
            }
            // 4.释放资源
            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}