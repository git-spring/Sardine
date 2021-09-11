package com.star.sparksql

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * @author: liudw
  * @date: 2021-1-5 13:50
  */

// 创建DataFrame
object CreateDF {
    def main(args: Array[String]): Unit = {

        // 创建SparkSession 对象
        val spark: SparkSession = SparkSession.builder.master("local[*]").appName("createDF").getOrCreate()
        import spark.implicits._

        // 通过读取文件生成(json、csv、...)
        val jsonDF: DataFrame = spark.read.json("Spark/data/emp.json")
        val csvDF: DataFrame = spark.read.csv("Spark/data/users.csv")
        val textDF = spark.read.text("Spark/data/users.text")
        val parquetDF = spark.read.parquet("Spark/data/users.parquet")
        val orcDF = spark.read.orc("Spark/data/users.orc")
        // 上面读取文件的方法  都是如下的调用方式
        val formatDF = spark.read.format("json").load("Spark/data/emp.json")


        // 从hive中查询得到
        // 创建临时表，当前会话有效    createGlobalTempView()  可以创建全局的
        jsonDF.createOrReplaceTempView("user")
        val hiveDF: DataFrame = spark.sql("select * from user")
        hiveDF.show()

        val hum: Seq[Human] = Seq(Human("ls", 20))
        val humDS: DataFrame = hum.toDF()

        // 通过序列创建
        val seqDF: DataFrame = spark.createDataFrame(Seq(
            (1, "liu", 20),
            (2, "six", 19),
            (3, "seven", 21)
        ))


        // 释放资源
        spark.stop()
    }
}
