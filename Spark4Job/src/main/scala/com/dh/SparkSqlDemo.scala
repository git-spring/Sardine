package com.dh

import java.io.File

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlDemo {
    def main(args: Array[String]): Unit = {

        // warehouseLocation points to the default location for managed databases and tables
        val warehouseLocation = new File("spark-warehouse").getAbsolutePath

        val spark = SparkSession
                .builder
                // 把代码放到服务器上执行
                //.master("local[*]")
                //.appName("Spark Hive Example")
                //.config("spark.sql.warehouse.dir", warehouseLocation)
                // 开启hive自动分区
                .config("hive.exec.dynamic.partition", true)
                .config("hive.exec.dynamic.partition.mode", "nonstrict")
                .enableHiveSupport()
                .getOrCreate()

        import spark.implicits._
        import spark.sql

        // 从hive中读取数据
        val hiveTable: DataFrame = spark.sql("select * from dm_dhyw.dm_yw_alcxjyzbrbb_cartype limit 1")
        hiveTable.show()

        // SparkSQL 写数据到hive
        spark.sql("insert into table default.test values('1','zs')")
        //hiveTable.write.insertInto("xxx")
    }


}