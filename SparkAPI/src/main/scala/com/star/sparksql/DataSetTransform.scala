package com.star.sparksql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}


/**
 * @author: liudw
 * @date: 2021-1-5 15:45
 */

// RDD 、DataFrame 、 DataSet 的相互转换
object DataSetTransform {
    def main(args: Array[String]): Unit = {


        // 创建DataFrame
        val spark = SparkSession.builder().master("local").getOrCreate()
        import spark.implicits._
        val userDF = spark.read.csv("SparkAPI/data/users.csv")

        // RDD与DataFrame的转换
        val rdd1: RDD[Row] = userDF.rdd
        val df1: DataFrame = rdd1.map(x => {
            Users(x.getString(0).trim().toInt, x.getString(1), x.getString(2).trim().toInt)
        }).toDF()

        // DataFrame与DataSet的转换
        val ds1: Dataset[Users] = df1.as[Users]
        val df2: DataFrame = ds1.toDF()

        // RDD与DataSet的转换
        val ds2: Dataset[Users] = rdd1.map(x => {
            Users(x.getString(0).trim().toInt, x.getString(1), x.getString(2).trim().toInt)
        }).toDS()

        val rdd2: RDD[Users] = ds2.rdd


    }

    /**
     * @see https://blog.csdn.net/wx1528159409/article/details/88407237
     *
     * 1. RDD是最基础的数据类型，在向上转换时，需要添加必要的信息；
     *      转DataFrame：需要添加结构信息并加上列名  toDF("id","name","age")
     *      转DataSet：需要添加类型信息，写样例类       map（x=>{User(x)}）.toDS()
     * 2. DataFrame在向上转换时，本身包含结构信息，只添加类型信息即可；
     *      转DataSet：先写样例类，as[User]
     *      转RDD：df.rdd
     * 3. DataSet作为最上层的抽象，转换其他对象直接可以往下转；
     *      转DataFrame：ds.toDF
     *      转RDD：ds.rdd
     */
}


case class Users(id: Int, name: String, age: Int)