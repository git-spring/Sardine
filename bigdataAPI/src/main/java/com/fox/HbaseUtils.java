package com.fox;

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
import org.apache.hadoop.hbase.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Spring
 */
public class HbaseUtils {
    private static Configuration conf = null;
    // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    private static ExecutorService executor = null;
    private static Connection conn = null;

    public static final String zkServer = "node01,node02,node03";
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
     * @param columFamily 列族
     * @return 表创建成功返回true
     */
    public boolean createTable(String tableName, String columFamily) {

        try {
            Admin admin = conn.getAdmin();
            HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            if (admin.tableExists(TableName.valueOf(tableName))) {
                System.out.println(tableName + "is already exists");
            } else {
                hTableDescriptor.addFamily(new HColumnDescriptor(columFamily));
                admin.createTable(hTableDescriptor);
                System.out.println("create table success");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除表
     *
     * @param tableName 表名
     * @return 表删除成功返回true
     */
    public boolean deleteTable(String tableName) {
        try {
            Admin admin = conn.getAdmin();
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
            System.out.println("delete table success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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
     * 使用Pair 写入数据
     *
     * @param tableName    表名
     * @param rowkey       rowkey 名称
     * @param columnFamily 列族
     * @param pairList     数据 键值对 包含 列名和写入的数据
     * @return 写入数据成功返回true
     */
    public boolean putRow(String tableName, String rowkey, String columnFamily, List<Pair<String, String>> pairList) {
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowkey));
            pairList.forEach(pair -> put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(pair.getFirst()), Bytes.toBytes(pair.getSecond())));
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 根据rowkey获取一行数据
     *
     * @param tableName 表名
     * @param rowkey    rowkey名称
     * @return 返回Result对象
     */
    public static Result getValueByRowkey(String tableName, String rowkey) {
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
     * 获取指定行指定列(cell)的最新版本的数据
     *
     * @param tableName
     * @param rowkey
     * @param columnFamily
     * @param qualifier
     * @return
     */
    public static String getCell(String tableName, String rowkey, String columnFamily, String qualifier) {
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Get get = new Get(rowkey.getBytes());
            if (!get.isCheckExistenceOnly()) {
                get.addColumn("f".getBytes(), "c".getBytes());
                Result result = table.get(get);
                byte[] value = result.getValue(columnFamily.getBytes(), qualifier.getBytes());
                return Bytes.toString(value);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检索全表
     * ResultScanner 对象里封装的就是Result 对象
     *
     * @param tableName
     * @return
     */
    public static ResultScanner scanTable(String tableName) {
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            return scanner;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检索时过滤表中数据
     *
     * @param tableName
     * @param startRowkey
     * @param stopRowkey
     * @param filterList
     * @return
     */
    public ResultScanner scanByFilterList(String tableName, String startRowkey, String stopRowkey, FilterList filterList) {
        // TODO: 2020/9/4 filter 未实现
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            scan.setStartRow(startRowkey.getBytes());
            scan.setStopRow(stopRowkey.getBytes());
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
            delete.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(qualifier));
            table.delete(delete);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // TODO: 2020/9/4 继续补充api

}