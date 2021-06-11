package com.star.sparksql

import java.lang

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @author: liudw
 * @date: 2021-1-6 15:41
 */

// 创建DataSet
object CreateDataSet {
    def main(args: Array[String]): Unit = {

        // 创建SparkSession对象
        val spark = SparkSession.builder.master("local").getOrCreate()
        import spark.implicits._

        val users: Dataset[String] = spark.read.textFile("Spark/data/users.csv")
        val jsonDF: Dataset[Person] = spark.read.json("Spark/data/person.json").as[Person]

        val rangeSet: Dataset[lang.Long] = spark.range(1 ,10)

        // 通过createDataset 方法 传入 List/Seq/RDD 得到DataSet
        val listDS: Dataset[Int] = spark.createDataset(List(1,2,3,3))

        val hum: Seq[Human] = Seq(Human("ls",20))
        val humDS: Dataset[Human] = hum.toDS()


        /**
         * 也可以通过RDD和DataFrame 转换得到DataSet
         *   [[DataSetTransform]]
         */
    }
}

case class Person(id: Long, name: String, age: Long)
case class Human(name:String,age:Int)