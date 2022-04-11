package com.JDBC;

import com.star.JDBC.JDBCMySQL;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

/**
 * @author Spring
 */
public class MySQLTEST {

    JDBCMySQL jdbcMySQL = new JDBCMySQL();

    @Test
    public void jdbcConnectMysqlTest() {

        jdbcMySQL.jdbcConnectMysqlTest();
    }
}