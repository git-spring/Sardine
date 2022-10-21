package com.dh

import java.io.File
import org.apache.spark.sql._

object SparkSqlDemo {
    def main(args: Array[String]): Unit = {

        // warehouseLocation points to the default location for managed databases and tables
        val warehouseLocation = new File("spark-warehouse").getAbsolutePath

        val spark = SparkSession
                .builder
                // 把代码放到服务器上执行
                .master("local[*]")
                .appName("Spark Hive Example")
                //.config("spark.sql.warehouse.dir", warehouseLocation)
                // 开启hive自动分区
                .config("hive.exec.dynamic.partition", true)
                .config("hive.exec.dynamic.partition.mode", "nonstrict")
                .enableHiveSupport()
                .getOrCreate()

        import spark.implicits._

        // 从hive中读取数据
        val hiveTable: DataFrame = spark.sql("select count(*) from student ")
        hiveTable.show()
        val frame: DataFrame = hiveTable.select("id","name")
        val rows: Array[Row] = frame.collect()

        rows.foreach(println(_))

//val rows: Array[Row] = hiveTable.collect()
//        print(schema(1))

        // SparkSQL 写数据到hive
        //spark.sql("insert into table default.test values('1','zs')")
        //hiveTable.write.insertInto("xxx")
    }


}