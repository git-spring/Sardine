package com.star.common.baseApi;

/*
 *  @author:   liudw
 *  @date:  2020-11-3
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hbase api 基于 hbase 2.x
 */
public class Hbase_v2x {

    private static Configuration conf = null;
    private static Connection conn = null;


    // 返回 连接对象
    static {
        try {

            conf = HBaseConfiguration.create();
            //conf.addResource("hbase-site.xml");

            // 通过 kerberos 认证连接hbase
            //System.setProperty("java.security.krb5.conf", "D:\\krb5.conf");
            //UserGroupInformation.setConfiguration(conf);
            //UserGroupInformation.loginUserFromKeytab("asset_app", "D:\\asset_app.keytab");

            // 创建Hbase 连接
            conn = ConnectionFactory.createConnection(conf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建命名空间
     *
     * @param nameSpace 命名空间
     */
    public void createNameSpace(String nameSpace) throws IOException {
        Admin admin = conn.getAdmin();
        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(nameSpace);

        try {
            admin.createNamespace(builder.build());
        } catch (IOException e) {
            System.out.println(nameSpace + " 命名空间已存在或不符合要求.");
            e.printStackTrace();
        }

        admin.close();
    }

    /**
     * 判断Hbase表是否存在
     *
     * @param nameSpace 命名空间
     * @param tableName 表名
     * @return true:存在 false:不存在
     */
    public boolean isTableExists(String nameSpace, String tableName) throws IOException {
        Admin admin = conn.getAdmin();

        boolean b = false;
        try {
            b = admin.tableExists(TableName.valueOf(nameSpace, tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 创建Hbase 表
     *
     * @param nameSpace    命名空间
     * @param tableName    表名
     * @param columnFamily 列族
     * @throws IOException
     */
    public void createTable(String nameSpace, String tableName, String... columnFamily) throws IOException {

        // 判断是否有列族(Hbase 建表至少需要一个列族)
        if (columnFamily.length < 1) {
            System.out.println("创建Hbase表至少需要一个列族");
            return;
        }

        Admin admin = conn.getAdmin();
        try {
            if (admin.tableExists(TableName.valueOf(nameSpace, tableName))) {
                System.out.println(nameSpace + ":" + tableName + " 表已存在");
                return;
            }

            // 表描述器
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(nameSpace, tableName));

            // 添加列族
            for (String f : columnFamily) {
                ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(f));
                // 设置一些参数
                columnFamilyDescriptorBuilder.setMaxVersions(3);
                // 设置列族
                tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptorBuilder.build());
            }
            // 创建表
            admin.createTable(tableDescriptorBuilder.build());
            System.out.println("create table " + nameSpace + ":" + tableName + " success.");
            admin.close();
        } catch (IOException e) {
            System.out.println("Oops...! create table failed!");
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     *
     * @param nameSpace 命名空间
     * @param tableName 表名
     * @return 表删除成功返回true
     */
    public void deleteTable(String nameSpace, String tableName) throws IOException {
        Admin admin = conn.getAdmin();
        try {
            if (!admin.tableExists(TableName.valueOf(nameSpace, tableName))) {
                System.out.println(nameSpace + ":" + tableName + " 表不存在,无法删除.");
                return;
            }
            admin.disableTable(TableName.valueOf(nameSpace, tableName));
            admin.deleteTable(TableName.valueOf(nameSpace, tableName));
            System.out.println("delete " + nameSpace + ":" + tableName + " table success");
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * put 写入一条数据
     *
     * @param tableName    表名
     * @param nameSpace    命名空间
     * @param rowkey       rowkey
     * @param columnFamily 列族
     * @param qualifier    列名
     * @param value        值
     * @return 写入数据成功返回true
     */
    public boolean putRow(String nameSpace, String tableName, String rowkey, String columnFamily, String qualifier, String value) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
            Put put = new Put(rowkey.getBytes());
            put.addColumn(columnFamily.getBytes(), qualifier.getBytes(), Bytes.toBytes(value));
            table.put(put);
            System.out.println("写入数据成功");
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 写入多条数据
     *
     * @param tableName 表名
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
            // table.batch();   https://blog.csdn.net/kongxx/article/details/79201836
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据rowkey获取一行数据
     *
     * @param nameSpace 命名空间
     * @param tableName 表名
     * @param rowkey    rowkey名称
     * @return 返回Result对象
     */
    public Result getValueByRowkey(String nameSpace, String tableName, String rowkey) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
            Get get = new Get(Bytes.toBytes(rowkey));
            Result result = table.get(get);
            if (result.size() > 0) {
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get获取指定行指定列 的最新数据
     *
     * @param nameSpace 命名空间
     * @param tableName 表名
     * @param rowkey    rowkey
     * @return
     */
    public String getCell(String nameSpace, String tableName, String rowkey, String columnFamily, String qualifier) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
            Get get = new Get(rowkey.getBytes());
            if (!get.isCheckExistenceOnly()) {
                get.addColumn(columnFamily.getBytes(), qualifier.getBytes());
                Result result = table.get(get);
                byte[] value = result.getValue(columnFamily.getBytes(), qualifier.getBytes());
                String s = Bytes.toString(value);
                return s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Scan 表
     * ResultScanner 对象里封装的就是Result 对象
     *
     * @param nameSpace 命名空间
     * @param tableName 表名
     * @return
     */
    public void scanTable(String nameSpace, String tableName) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
            Scan scan = new Scan();
            // 设置LIMIT=> 3
            scan.setLimit(3);

            ResultScanner resultScanner = table.getScanner(scan);

            for (Result result : resultScanner) {
                // 遍历result中的数据
                for (Cell cell : result.rawCells()) {
                    String rowkey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    // 获取列族
                    String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                    // 获取列
                    String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                    // 获取值(cell)
                    String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    System.out.println(rowkey + " " + family + " " + qualifier + " " + value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 限定 startrow 和 stoprow
     *
     * @param nameSpace   命名空间
     * @param tableName   表名
     * @param startRowkey
     * @param stopRowkey
     * @return
     */
    public ResultScanner scanByStartrowAndStoprow(String nameSpace, String tableName, String startRowkey, String stopRowkey) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
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

    /**
     * 使用Filter 进行过滤
     * 过滤的使用 {@link com.star.hbase.ScanFilter#filterList}
     *
     * @param nameSpace   命名空间
     * @param tableName   表名
     * @param startRowkey
     * @param stopRowkey
     * @return
     */
    public ResultScanner scanByFilterList(String nameSpace, String tableName, String startRowkey, String stopRowkey, FilterList filterList) {

        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
            Scan scan = new Scan();
            scan.withStartRow(startRowkey.getBytes());
            scan.withStopRow(stopRowkey.getBytes());
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
     * @param nameSpace 命名空间
     * @param tableName 表名
     * @param rowkey
     * @return
     */
    public boolean deleteRow(String nameSpace, String tableName, String rowkey) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
            Delete delete = new Delete(rowkey.getBytes());
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除指定列数据
     *
     * @param nameSpace
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param qualifier
     * @return
     */
    public boolean deleteColumn(String nameSpace, String tableName, String rowKey, String familyName, String qualifier) {
        try {
            Table table = conn.getTable(TableName.valueOf(nameSpace, tableName));
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
