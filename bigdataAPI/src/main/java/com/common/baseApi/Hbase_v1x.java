package com.common.baseApi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  基于 HBASE 1.x
 * @author Spring
 */
public class Hbase_v1x {
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
	public void createTable(String tableName, String columnFamily) {
		Admin admin = null;
		try {
			admin = conn.getAdmin();
			HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
			if (admin.tableExists(TableName.valueOf(tableName))) {
				System.out.println(tableName + "is already exists");
			} else {
				hTableDescriptor.addFamily(new HColumnDescriptor(columnFamily));
				admin.createTable(hTableDescriptor);
				System.out.println("create table success");
			}

		} catch (IOException e) {
			System.out.println("Oops...! create table failed!");
			e.printStackTrace();
		} finally {
			try {
				if (admin != null) admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除表
	 *
	 * @param tableName 表名
	 * @return 表删除成功返回true
	 */
	public void deleteTable(String tableName) {
		try {
			Admin admin = conn.getAdmin();
			admin.disableTable(TableName.valueOf(tableName));
			admin.deleteTable(TableName.valueOf(tableName));
			System.out.println("delete table success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * put 写入一条数据
	 *
	 * @param tableName    表名
	 * @param rowkey       rowkey 名称
	 * @param columnFamily 列族
	 * @param qualifier    列名
	 * @param value        值
	 * @return 写入数据成功返回true
	 */
	public boolean putRow(String tableName, String rowkey, String columnFamily, String qualifier, String value) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Put put = new Put(rowkey.getBytes());
			put.addColumn(columnFamily.getBytes(), qualifier.getBytes(), Bytes.toBytes(value));
			table.put(put);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *  写入多条数据
	 *
	 * @param tableName    表名
	 */
	public void putRow(String tableName) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			List<Put> puts = new ArrayList<Put>();
			Put put1 = new Put(Bytes.toBytes("0001"));
			put1.addColumn(Bytes.toBytes("c"), Bytes.toBytes("f1"), Bytes.toBytes("01"));

			Put put2 = new Put(Bytes.toBytes("0002"));
			put2.addColumn(Bytes.toBytes("c"), Bytes.toBytes("f2"), Bytes.toBytes("02"));

			Put put3 = new Put(Bytes.toBytes("0003"));
			put3.addColumn(Bytes.toBytes("c"), Bytes.toBytes("f3"), Bytes.toBytes("03"));

			Put put4 = new Put(Bytes.toBytes("0004"));
			put4.addColumn(Bytes.toBytes("c"), Bytes.toBytes("f4"), Bytes.toBytes("04"));

			puts.add(put1);
			puts.add(put2);
			puts.add(put3);
			puts.add(put4);

			table.put(puts);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据rowkey获取一行数据
	 *
	 * @param tableName 表名
	 * @param rowkey    rowkey名称
	 * @return 返回Result对象
	 */
	public Result getValueByRowkey(String tableName, String rowkey) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Get get = new Get(Bytes.toBytes(rowkey));
			Result result = table.get(get);
			if (result.size() > 1) {
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get获取指定行指定列(cell)的最新版本的数据
	 *
	 * @param tableName
	 * @param rowkey
	 * @param columnFamily
	 * @param qualifier
	 * @return
	 */
	public void getCell(String tableName, String rowkey, String columnFamily, String qualifier) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Get get = new Get(rowkey.getBytes());
			if (!get.isCheckExistenceOnly()) {
				get.addColumn("f".getBytes(), "c".getBytes());
				Result result = table.get(get);
				byte[] value = result.getValue(columnFamily.getBytes(), qualifier.getBytes());
				String s = Bytes.toString(value);
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检索全表
	 * ResultScanner 对象里封装的就是Result 对象
	 *
	 * @param tableName
	 * @return
	 */
	public void scanTable(String tableName) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			ResultScanner resultScanner = table.getScanner(scan);
			for (Result result : resultScanner) {
				byte[] value = result.getValue("f".getBytes(), "c".getBytes());
				System.out.println(value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  限定 startrow 和 stoprow
	 * @param tableName
	 * @param startRowkey
	 * @param stopRowkey
	 * @return
	 */
	public ResultScanner scanByStartrowAndStoprow(String tableName, String startRowkey, String stopRowkey) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			scan.setStartRow(startRowkey.getBytes());
			scan.setStopRow(stopRowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			return scanner;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用Filter 进行过滤
	 * 过滤的使用 {@link com.hbase.ScanFilter#filterList}
	 * @param tableName
	 * @param startRowkey
	 * @param stopRowkey
	 * @return
	 */
	public ResultScanner scanByFilterList(String tableName, String startRowkey, String stopRowkey, FilterList filterList) {

		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			// 添加过滤器
			scan.setFilter(filterList);
			ResultScanner scanner = table.getScanner(scan);
			return scanner;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除指定行数据
	 *
	 * @param tableName
	 * @param rowkey
	 * @return
	 */
	public boolean deleteRow(String tableName, String rowkey) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Delete delete = new Delete(rowkey.getBytes());
			table.delete(delete);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 删除指定列数据
	 * @param tableName
	 * @param rowKey
	 * @param familyName
	 * @param qualifier
	 * @return
	 */
	public boolean deleteColumn(String tableName, String rowKey, String familyName, String qualifier) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Delete delete = new Delete(Bytes.toBytes(rowKey));
			// 删除某列的内容
			delete.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(qualifier));
			table.delete(delete);
			// 删除某列族的内容
			Delete delete1 = new Delete(Bytes.toBytes(rowKey));
			delete1.addFamily(Bytes.toBytes(familyName));
			table.delete(delete1);

			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}


}