package com.sparksql

import java.lang

import org.apache.spark.sql.{Dataset, SparkSession}

/**
 * @author: liudw
 * @date: 2021-1-6 15:41
 */

// 创建DataSet
object CreateDataSet {
    def main(args: Array[String]): Unit = {

        // 创建SparkSession对象
        val spark = SparkSession.builder().getOrCreate()
        import spark.implicits._

        val emp: Dataset[String] = spark.read.textFile("SparkAPI/data/users.csv")

        val rangeSet: Dataset[lang.Long] = spark.range(1 ,10)


        /**
         * 也可以通过RDD和DataFrame 转换得到DataSet
         * @see DataSetTransform
         */
    }
}
