package com.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: liudw
 * @date: 2020-12-23 14:14
 */

// 创建RDD的有三种方式
object CreateRdd {

    def main(args: Array[String]): Unit = {

        // 创建SparkContext对象
        val conf = new SparkConf().setMaster("local[*]").setAppName("createRdd")
        val sc = new SparkContext(conf)

        // 1.从集合中创建RDD ,底层是调用 parallelize(seq, numSlices)
        sc.makeRDD(List(1,2,3,4,5))

        // 2.从集合中创建RDD ,可以指定分片数量
        sc.parallelize(List(1,2,3,4,5),3)

        // 3.从外部存储中创建RDD
        sc.textFile("SparkAPI/in")

    }
}
