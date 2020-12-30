package com.sparkcore

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: liudw
 * @date: 2020-12-29 10:38
 */

// RDD 的缓存和检查点
object CheckPoint {

    /**
     * spark 中的cache和persist都可以用来缓存,cache底层调用的就是cache
     *
     * 缓存时都可以指定缓存级别,(默认是在内存中储存一份)
     * 调用缓存方法时不会立即缓存,触发后面的action算子时才会进行缓存
     *
     * checkpoint:
     *      检查点只能保存在磁盘(一般是HDFS)上,使用前需要设置检查点目录,也需要行动算子才会执行检查点,
     *      设置检查点后会切断RDD的依赖关系,
     *      而缓存不会切断依赖关系,就算缓存到磁盘上,也仍然会保留原有的RDD依赖
     *      如果数据量过大还是建议使用checkpoint
     *
     */

    // 缓存
    def cacheDemo(): Unit = {
        // 创建SparkContext对象
        val conf = new SparkConf().setMaster("local[*]").setAppName("cache")
        val sc = new SparkContext(conf)
        val listRdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8))
        // listRdd.cache() // cache 缓存时不能指定缓存级别,默认是在内存中储存一份
        listRdd.persist(StorageLevel.MEMORY_AND_DISK_SER_2)
        println(listRdd.toDebugString)
    }


    // 检查点
    def checkPointDemo(): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("checkpoint")
        val sc = new SparkContext(conf)

        // 设置检查点目录,使用检查点必须设置
        sc.setCheckpointDir("SparkChk")
        val listRdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8))

        listRdd.checkpoint() // 检查点
    }

    def main(args: Array[String]): Unit = {
        cacheDemo()
        // checkPointDemo()
    }

}
