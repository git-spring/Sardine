package com.JDBC;

import com.star.JDBC.ConnectionPool;
import com.star.JDBC.JDBCMySQL;
import org.junit.Test;

/**
 * @author Spring
 */
public class MySQLTEST {

    JDBCMySQL jdbcMySQL = new JDBCMySQL();
    ConnectionPool connectionPool = new ConnectionPool();

    @Test
    public void jdbcConnectMysqlTest() {

        jdbcMySQL.jdbcConnectMysqlTest();
    }

    @Test
    public void jdbcGetTableInfo(){
        jdbcMySQL.jdbcGetTableInfo();
    }

    @Test
    public void jdbcGetColumnInfo(){
        jdbcMySQL.jdbcGetColumnInfo("student");
    }

    @Test
    public void insertTable(){
        jdbcMySQL.insertTable();
    }

    @Test
    public void druidDemo(){
        connectionPool.druidDemo();
    }

    @Test
    public void druidDemo1(){
        connectionPool.druidDemo1();
    }
}