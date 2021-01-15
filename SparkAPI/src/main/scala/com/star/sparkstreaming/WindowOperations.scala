package com.star.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: liudw
 * @date: 2021-1-12 16:26
 */

// SparkStreaming 的窗口操作
// 每隔2秒钟,统计最近4秒钟的单词出现的频率,并打印出排名最靠前的3个词以及出现次数
//
object WindowOperations {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("windows")
        val ssc = new StreamingContext(conf, Seconds(2))  // 这里的时间是采集周期
        // 使用滑动窗口需要设置检查点
        ssc.checkpoint("spark-warehouse/data")

        val socketDstream = ssc.socketTextStream("10.2.98.132", 9999)
        val words: DStream[String] = socketDstream.flatMap(_.split(" "))

        // val winDStream = words.window(Seconds(4))

        // 统计窗口内的数据个数(总的个数)
        // val winDStream = words.countByWindow(Seconds(4),Seconds(2))

        // 按出现的次数统计出现的个数
        val winDStream = words.countByValueAndWindow(Seconds(4), Seconds(2))  // 窗口大小，滑动时机

        val finalDStream = winDStream.transform(item => {

            val reduceRdd = item.reduceByKey(_ + _)

            val reverseRdd = reduceRdd.map(x => (x._2, x._1)) // 把key和value反转,方便后面的sortByKey排序(也可以用sortby+自定义排序函数进行排序)

            val sortRdd = reverseRdd.sortByKey(false) // 按key排序(这里是相同单词的个数)

            val top3: Array[(String, Long)] = sortRdd.take(3)
                .map(x =>(x._2,x._1)) // 取出排名靠前的3个,并再次反转key和value

            println(top3.mkString(",,"))

            reduceRdd
        })

        finalDStream.print()

        ssc.start()

        ssc.awaitTermination()

    }
}
