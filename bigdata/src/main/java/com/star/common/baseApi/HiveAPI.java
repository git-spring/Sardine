package com.star.common.baseApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Spring
 */
public class HiveAPI {

    // hive-jdbc 方式操作 hive
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;

    public static final String driverName = "org.apache.hive.jdbc.HiveDriver";   // 此Class 位于 hive-jdbc的jar包下//org.apache.hive.jdbc.HiveDriver
    public static final String url = "jdbc:hive2://10.248.107.51:10000/dm_dhyw";
    public static final String userName = "ods";
    public static final String password = "cci@Dh0901";

    static {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     *
     * @param sql
     * @return 返回结果集
     */
    public static ResultSet queryData(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 演示遍历 ResultSet
     *
     * @param sql
     */
    public static void foreachResultSet(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //            // 1.
            //            while (rs.next()){
            //                // 通过字段名获取对应的值
            //                String uploader = rs.getString("uploader");
            //                int videos = rs.getInt("videos");
            //                int friends = rs.getInt("friends");
            //                System.out.println(uploader+"\t"+videos+"\t"+friends);
            //            }

            // 2. 获取元数据  从元数据中可以获取其它信息
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取列名称
            String columnLabel = metaData.getColumnLabel(1);
            // 获取列的 Class 名称(列的类型)
            String columnClassName = metaData.getColumnClassName(2);
            // 获取列数量
            int columnsCount = metaData.getColumnCount();

            // 3. 动态获取所有数据

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}