package com.JDBC;

import com.star.JDBC.ConnectionPool;
import com.star.JDBC.JDBCMySQL;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Spring
 */
public class MySQLTEST {

    JDBCMySQL jdbcMySQL = new JDBCMySQL();
    ConnectionPool connectionPool = new ConnectionPool();

    @Test
    public void jdbcConnectMysqlTest() {

        jdbcMySQL.jdbcConnectMysqlTest("student");
    }

    @Test
    public void jdbcGetTableInfo() {
        List<String> strings = jdbcMySQL.jdbcGetTableInfo();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String tableName = iterator.next();
            System.out.println(tableName);

        }
    }

    @Test
    public void jdbcGetColumnInfo() {
        List<String> list = jdbcMySQL.jdbcGetColumnInfo("test_reins");
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test
    public void getPrimaryKeys() {
        jdbcMySQL.getPrimaryKeys("test1_reins");
    }

    @Test
    public void getInfo() throws SQLException {
        jdbcMySQL.getIndexInfo("test_reins");
    }

    @Test
    public void insertTable() {
        jdbcMySQL.insertTable();
    }

    @Test
    public void druidDemo() {
        connectionPool.druidDemo();
    }

    @Test
    public void druidDemo1() {
        connectionPool.druidDemo1();
    }


}