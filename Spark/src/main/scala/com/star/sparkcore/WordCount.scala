package com.star.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

import java.util.Properties

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
        val words = lines.flatMap(_.split(" "))  // x=>x.split(" ")
        val word = words.map((_, 1)) // x=>(x, 1)
        val key = word.reduceByKey(_+_) // (x,y)=>(x+y)
        val res = key.foreach(println)
        print(res)

        println(key.dependencies)
        println(key.toDebugString)

    }
}
