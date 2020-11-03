package com.common.baseApi;

/*
 *  @author:   liudw
 *  @date:  2020-11-3
 */

/**
 *  Hbase api 基于 hbase 2.x
 */
public class Hbase_v2x {

	// TODO: 2020-11-3 hbase 2.x api
	/**
	 * 创建表
	 *
	 * @param tableName   表名
	 * @param columnFamily 列族
	 * @return 表创建成功返回true
	 */
	// public static void createTable(String tableName, String columnFamily) {
	// 	Admin admin = null;
	// 	try {
	// 		admin = conn.getAdmin();
	// 		if (admin.tableExists(TableName.valueOf(tableName))) {
	// 			System.out.println(tableName + "is already exists");
	// 		} else {
	// 			// 如果表不存在则创建
	// 			TableName name = TableName.valueOf(tableName);
	// 			// 表描述器
	// 			TableDescriptorBuilder tdb = TableDescriptorBuilder.newBuilder(name);
	// 			TableDescriptor td = tdb.build();
	// 			// 列族描述器
	// 			ColumnFamilyDescriptorBuilder cfdb = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
	// 			ColumnFamilyDescriptor cfd = cfdb.build();
	// 			// 添加列族
	// 			tdb.setColumnFamily(cfd);
	// 			// admin.addColumnFamily(name,cfd);
	// 			// 创建表
	// 			admin.createTable(td);
	// 			System.out.println("create table success");
	// 		}
	// 	} catch (IOException e) {
	// 		System.out.println("Oops...! create table failed!");
	// 		e.printStackTrace();
	// 	} finally {
	// 		try {
	// 			if (admin != null) admin.close();
	// 		} catch (IOException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
	// }


}
