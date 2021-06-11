package com.star.sparkcore

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: liudw
 * @date: 2020-12-29 15:18
 */


/**
 * 广播变量
 *
 *  广播变量在每个节点上保存一个只读的变量的缓存, 而不用在每个task运行的时候从 Driver 传输,可以提高性能
 *
 *  广播变量只能在 Driver 端定义，不能在 Executor 端定义
 *  在 Driver 端可以修改广播变量的值，在Executor端无法修改广播变量的值
 *
 *  Executor 端用到了 Driver 的变量
 *      如果不使用广播变量, 在 Executor 有多少 task 就有多少 Driver端的变量副本
 *      如果使用广播变量, 在每个 Executor 中只有一份 Driver端的变量副本
 *
 */
object BroadCast {
    def main(args: Array[String]): Unit = {

        // 数据量较大的变量，需要被广播
        val data = 1 to 10000 toArray

        val conf = new SparkConf().setMaster("local[*]").setAppName("broadcast")
        val sc = new SparkContext(conf)

        // 把变量广播出去
        val bc: Broadcast[Array[Int]] = sc.broadcast(data)

        val listRdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8))

        // 过滤listRdd和广播变量有交集的变量
        val rdd2 = listRdd.filter(x => bc.value.contains(x))

        rdd2.foreach(println)

    }
}
