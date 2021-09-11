package com.star.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author: liudw
  * @date: 2020-12-22 17:21
  */
object WordCount {
    def main(args: Array[String]): Unit = {

        // 创建SparkConf对象
        val conf = new SparkConf().setMaster("local[*]").setAppName("qia")
        // 创建SparkContext对象
        val sc = new SparkContext(conf)

        val lines = sc.textFile("Spark/in")
        val words = lines.flatMap(_.split(" "))
        val word = words.map((_, 1))
        val key = word.reduceByKey(_ + _)
        val res = key.foreach(println)
        print(res)

        println(key.dependencies)
        println(key.toDebugString)

    }
}
