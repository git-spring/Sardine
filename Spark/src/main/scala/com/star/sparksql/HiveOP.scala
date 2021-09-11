package com.star.sparksql

import java.io.File

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * @author: liudw
  * @date: 2021-1-8 10:57
  */

// SparkSQL 操作 hive,需要引入spark-hive的jar包,并配置好HDFS等环境
object HiveOP {
    def main(args: Array[String]): Unit = {

        // warehouseLocation points to the default location for managed databases and tables
        val warehouseLocation = new File("spark-warehouse").getAbsolutePath

        val spark = SparkSession
                .builder
                // 把代码放到服务器上执行
                // .master("local[*]")
                // .appName("Spark Hive Example")
                .config("spark.sql.warehouse.dir", warehouseLocation)
                // 开启hive自动分区
                .config("hive.exec.dynamic.partition", true)
                .config("hive.exec.dynamic.partition.mode", "nonstrict")
                .enableHiveSupport()
                .getOrCreate()

        import spark.implicits._
        import spark.sql

        // 从hive中读取数据
        val hiveTable = spark.sql("select * from hiveTable")
        hiveTable.show()

        // SparkSQL 写数据到hive
        spark.sql("insert into table xxx select * from yyy")
        hiveTable.write.insertInto("xxx")
    }
}