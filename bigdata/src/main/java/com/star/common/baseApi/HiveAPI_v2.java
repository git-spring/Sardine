package com.star.common.baseApi;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Spring
 * @date 2022/5/19 11:24
 * @describe : HiveConf 方式连接 hive ， 需要把hadoop和hive的配置文件添加到resource中
 * 相比于JDBC的方式，HiveConf提供了更多hive的操作
 */

public class HiveAPI_v2 {

    private static HiveConf hiveConf;
    private static HiveMetaStoreClient metastoreClient;

    static {
        try {
            hiveConf = new HiveConf();
            metastoreClient = new HiveMetaStoreClient(hiveConf);
        } catch (MetaException e) {
            e.printStackTrace();
        }
    }

    /**
     *   获取表字段schema信息
     * @param dbName  数据库名称
     * @param tableName  表名称
     */
    public static void getMetadataInfo(String dbName, String tableName) {
        List<FieldSchema> fields = null;
        try {
            fields = metastoreClient.getFields(dbName, tableName);
            for (FieldSchema s : fields) {
                System.out.println(s);
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }


    public static void queryData(){
        Table table = new Table();

    }


}