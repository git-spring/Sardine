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
 *  广播变量在每个节点上保存一个只读的变量的缓存, 而不用在每个task运行的时候从driver传输,可以提高性能
 *
 *  广播变量只能在Driver端定义，不能在Executor端定义
 *  在Driver端可以修改广播变量的值，在Executor端无法修改广播变量的值
 *  如果executor端用到了Driver的变量，如果不使用广播变量在Executor有多少task就有多少Driver端的变量副本
 *  如果Executor端用到了Driver的变量，如果使用广播变量在每个Executor中只有一份Driver端的变量副本
 */
object BroadCast {
    def main(args: Array[String]): Unit = {

        // 数据量较大的变量，需要被广播
        val data = 1 to 10000 toArray

        val conf = new SparkConf().setMaster("local[*]").setAppName("broadcast")
        val sc = new SparkContext(conf)

        // 把变量广播出去
        val bd: Broadcast[Array[Int]] = sc.broadcast(data)

        val listRdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8))

        // 过滤listRdd和广播变量有交集的变量
        val rdd2 = listRdd.filter(x => bd.value.contains(x))

        rdd2.foreach(println)

    }
}
