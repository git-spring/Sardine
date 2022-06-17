package com.star.JDBC;


import com.star.logging.log4j.Log4jDemo;
import com.star.properties.DynamicLoadProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * @author Spring
 */


public class JDBCMySQL {

    static Logger logger = Logger.getLogger(JDBCMySQL.class);
    static Connection conn = null;
    static DatabaseMetaData metaData = null;

    // 获取数据库连接
    static {
        try {
            conn = ConnectionUtils.getMysqlConnection("mysql1.driver", "mysql1.url", "mysql1.username", "mysql1.password");
            metaData = conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 使用jdbc 连接MySQL
    public void jdbcConnectMysqlTest(String tableName) {
        logger.info("使用JDBC连接MySQL数据库");
        // 编写sql
        String sql = "select * from " + tableName + " where sno >= ?;";
        try {
            // 预编译SQL:
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 设置参数
            pstmt.setString(1, "100");
            // 执行sql
            ResultSet rs = pstmt.executeQuery();
            List<String> list = jdbcGetColumnInfo(tableName);
            // 遍历结果集:
            logger.info("读取到数据,开始遍历");
            while (rs.next()) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(rs.getObject(list.get(i)) + " ");
                }
                System.out.println();
            }
            // 释放资源
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertTable() {
        logger.info("JDBC 批量向MySQL写入数据");
        try {
            conn.setAutoCommit(false);   // 关闭自动commit
            PreparedStatement pstm = conn.prepareStatement("insert into  sql50_1.test values (?,?,?)");
            Long time1 = System.currentTimeMillis();
            for (int i = 1; i <= 10000; i++) {
                pstm.setInt(1, i);
                pstm.setString(2, "孙悟空" + String.valueOf(i));
                pstm.setInt(3, i);
                pstm.addBatch();
            }
            Long time2 = System.currentTimeMillis();
            pstm.executeBatch();
            conn.commit();
            logger.info("写入成功!");
            Long time3 = System.currentTimeMillis();
            System.out.println(time2 - time1);
            System.out.println(time3 - time2);
            System.out.println(time3 - time1);
        } catch (SQLException e) {
            logger.error("写入失败...", e);
        }
    }

    /**
     * 获取表名
     */
    public List<String> jdbcGetTableInfo() {
        logger.info("JDBC 获取表名");

        List<String> tableList = new ArrayList<>();                 // 存储表名的集合
        try {
            // "%" 表示所有的
            ResultSet rs = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
            logger.info("当前库下的表:");
            while (rs.next()) {
                // 获取所有的表名
                // System.out.println(rs.getString("TABLE_NAME"));  // 获取表名
                String tableName = rs.getString("TABLE_NAME");
                tableList.add(tableName);
                logger.info(tableName);
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字段信息
     * 列的其他描述
     * TABLE_CAT String => 表类别（可为 null）
     * TABLE_SCHEM String => 表模式（可为 null）
     * TABLE_NAME String => 表名称
     * COLUMN_NAME String => 列名称
     * DATA_TYPE int => 来自 java.sql.Types 的 SQL 类型
     * TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
     * COLUMN_SIZE int => 列的大小。
     * BUFFER_LENGTH 未被使用。
     * DECIMAL_DIGITS int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。
     * NUM_PREC_RADIX int => 基数（通常为 10 或 2）
     * NULLABLE int => 是否允许使用 NULL。
     * columnNoNulls - 可能不允许使用 NULL 值
     * columnNullable - 明确允许使用 NULL 值
     * columnNullableUnknown - 不知道是否可使用 null
     * REMARKS String => 描述列的注释（可为 null）
     * COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
     * SQL_DATA_TYPE int => 未使用
     * SQL_DATETIME_SUB int => 未使用
     * CHAR_OCTET_LENGTH int => 对于 char 类型，该长度是列中的最大字节数
     * ORDINAL_POSITION int => 表中的列的索引（从 1 开始）
     * IS_NULLABLE String => ISO 规则用于确定列是否包括 null。 YES --参数可以包括 NULL NO --参数不可以包括 NULL  空字符串 --不知道参数是否可以包括 null
     * SCOPE_CATLOG String => 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
     * SCOPE_SCHEMA String => 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
     * SCOPE_TABLE String => 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
     * SOURCE_DATA_TYPE short => 不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null）
     * IS_AUTOINCREMENT String => 指示此列是否自动增加 YES --自动增加  NO --不自动增加 空字符串 --不能确定该列是否是自动增加参数
     * COLUMN_SIZE 列表示给定列的指定列大小。对于数值数据，这是最大精度。对于字符数据，这是字符长度。对于日期时间数据类型，这是 String 表示形式的字符长度（假定允许的最大小数秒组件的精度）。对于二进制数据，这是字节长度。对于 ROWID 数据类型，这是字节长度。对于列大小不适用的数据类型，则返回 Null。
     * <p>
     * 参数：
     * catalog - 类别名称；它必须与存储在数据库中的类别名称匹配；该参数为 "" 表示获取没有类别的那些描述；为 null 则表示该类别名称不应该用于缩小搜索范围
     * schemaPattern - 模式名称的模式；它必须与存储在数据库中的模式名称匹配；该参数为 "" 表示获取没有模式的那些描述；为 null 则表示该模式名称不应该用于缩小搜索范围
     * tableNamePattern - 表名称模式；它必须与存储在数据库中的表名称匹配
     * columnNamePattern - 列名称模式；它必须与存储在数据库中的列名称匹配
     */
    public List<String> jdbcGetColumnInfo(String TableName) {
        logger.info("JDBC 表的字段信息");
        List<String> columnList = new ArrayList<>();            // 存储单个字段的名称
        try {
            // "%" 表示所有的
            ResultSet rs = metaData.getColumns(conn.getCatalog(), "%", TableName, "%");
            while (rs.next()) {
                int columnIndex = rs.getInt("ORDINAL_POSITION");   // 字段的索引(顺序),从1开始
                String columnName = rs.getString("COLUMN_NAME");   // 字段名
                String columnType = rs.getString("TYPE_NAME");     // 字段类型
                int columnSize = rs.getInt("COLUMN_SIZE");         // 字段大小
                int digits = rs.getInt("DECIMAL_DIGITS");          // 小数部分位数
                String remarks = rs.getString("REMARKS");          // 字段注释
                // 把字段信息添加到map中,key为字段的顺序,value为字段名称
                columnList.add(columnIndex - 1, columnName);             // 字段名称按顺序排列
                // System.out.println(columnIndex + " " + columnName + " " + columnType + " " + columnSize + " " + digits + " " + remarks);
            }
            return columnList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取表的主键
     * 联合主键结果会有多行
     *
     * @param tableName
     * @see com.mysql.jdbc.DatabaseMetaData#getPrimaryKeys
     */
    public static void getPrimaryKeys(String tableName) {
        try {
            ResultSet primaryKeys = metaData.getPrimaryKeys(conn.getCatalog(), null, tableName);
            while (primaryKeys.next()) {
                String columnName = primaryKeys.getString("COLUMN_NAME");   // 获取主键字段名
                String pkName = primaryKeys.getString("PK_NAME");           // 获取主键名称
                System.out.println(columnName);
                System.out.println(pkName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  获取表的索引信息
     *    主键也是唯一索引
     * @param tableName
     * @throws SQLException
     */
    public static void getIndexInfo(String tableName) throws SQLException {
        ResultSet indexInfo = metaData.getIndexInfo(conn.getCatalog(), null, tableName, false, false);  // 获取所有的索引
        ResultSet indexInfo1 = metaData.getIndexInfo(conn.getCatalog(), null, tableName, true, false);  // 只获取唯一索引(主键也是唯一索引)
        while(indexInfo.next()){
            String columnName = indexInfo.getString("COLUMN_NAME");
            System.out.println(columnName);
        }
    }

}