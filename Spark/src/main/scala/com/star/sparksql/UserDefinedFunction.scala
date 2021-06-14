package com.star.sparksql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author: liudw
 * @date: 2021-6-10 15:44
 */
// Spark 创建自定义函数
object UserDefinedFunction {


    val  spark: SparkSession = SparkSession.builder.master("local[*]").appName("createDF").getOrCreate()
    import spark.implicits._

    def sparkUDF(): Unit ={
        // 通过读取文件生成(json、csv、...)
        val df: DataFrame = spark.read.json("Spark/data/emp.json")

        // 创建临时表
        df.createOrReplaceTempView("emps")

        // 注册UDF 函数  (在name 前面加上 "name: ")
        spark.udf.register("prefixName",(name:String)=>{
            "name: " + name
        })
        spark.sql("select age, prefixName(name) from emps").show
    }


    def sparkUDAF(): Unit ={}

    def main(args: Array[String]): Unit = {
        sparkUDF()
    }
}
