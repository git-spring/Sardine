package com.star.common.baseApi;

/*
 *  @author:   liudw
 *  @date:  2020-11-3
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Hbase api 基于 hbase 2.x
 */
public class Hbase_v2x {

	private static Configuration conf = null;
	// 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	private static ExecutorService executor = null;
	private static Connection conn = null;

	public static final String zkServer = "dcdl-dgv-cdh2.localhost.com";
	public static final String zkPort = "2181";
	public static final int threadPoolSize = 20;

	// 返回 连接对象
	static {
		try {
			conf = HBaseConfiguration.create();
			conf.set("hbase.zookeeper.quorum", zkServer);
			conf.set("hbase.zookeeper.property.clientPort", zkPort);
			//conf.set("hbase.defaults.for.version.skip", "true");
			executor = Executors.newFixedThreadPool(threadPoolSize);
			conn = ConnectionFactory.createConnection(conf, executor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建表
	 *
	 * @param tableName   表名
	 * @param columnFamily 列族
	 * @return 表创建成功返回true
	 */
/*	public void createTable(String tableName, String columnFamily) {
		Admin admin = null;
		try {
			admin = conn.getAdmin();
			if (admin.tableExists(TableName.valueOf(tableName))) {
				System.out.println(tableName + "is already exists");
			} else {
				// 如果表不存在则创建
				TableName name = TableName.valueOf(tableName);
				// 表描述器
				TableDescriptorBuilder tdb = TableDescriptorBuilder.newBuilder(name);
				TableDescriptor td = tdb.build();
				// 列族描述器
				ColumnFamilyDescriptorBuilder cfdb = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
				ColumnFamilyDescriptor cfd = cfdb.build();
				// 添加列族
				tdb.setColumnFamily(cfd);
				// admin.addColumnFamily(name,cfd);
				// 创建表
				admin.createTable(td);
				System.out.println("create table success");
			}
		} catch (IOException e) {
			System.out.println("Oops...! create table failed!");
			e.printStackTrace();
		} finally {
			close(admin);
		}
	}
*/
/*

	public ResultScanner scanByStartrowAndStoprow(String tableName, String startRowkey, String stopRowkey) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			scan.withStartRow(startRowkey.getBytes());
			scan.withStopRow(stopRowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			return scanner;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	/**
	 *  关闭资源
	 * @param admin
	 */
	private void close(Admin admin) {
		try {
			if (admin != null) admin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
