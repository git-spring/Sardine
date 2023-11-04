package com.star.common.baseApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Spring
 * @date 2022/5/19 11:24
 * @describe : JDBC 方式连接 hive
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
     * 获取hive表的字段信息
     *
     * @param sql
     */
    public static void getMetadataInfo(String sql) {
        try {
            ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();                      // 获取查询到的数据集
            ResultSetMetaData metaData = rs.getMetaData();         // 获取元数据对象  从元数据中可以获取其它信息
            int columnCount = metaData.getColumnCount();           // 获取字段的数量
            for (int i = 1; i <= columnCount; i++) {
                String columnLabel = metaData.getColumnName(i);    // 获取字段的名称
                String columnType = metaData.getColumnTypeName(i); // 获取列的类型
                if (i == columnCount) {
                    System.out.println(columnLabel);               // 打印表头
                } else {
                    System.out.print(columnLabel + "\t");
                }
            }

            //rs.last();                                            // 把指针指向最后一行
            //int row = rs.getRow();                                // 获取当前行号，可以知道ResultSet中一共有多少行数据
            //System.out.println("共查出："+row +" 条记录");
            //rs.first();                                           // 把指针指向第一行

            // ------------------------------------------------   // 遍历数据
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object object = rs.getObject(i);
                    if (i == columnCount) {
                        System.out.println(object);
                    } else {
                        System.out.print(object + "\t");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}