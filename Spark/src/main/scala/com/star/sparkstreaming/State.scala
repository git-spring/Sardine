package com.star.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: liudw
 * @date: 2021-1-12 15:31
 */

/**
 * SparkStreaming 的有状态/无状态操作
 * 无状态操作：
 * 无状态转化操作就是把简单的RDD转化操作应用到每个批次上，也就是转化DStream中的每一个RDD
 * 有状态操作：
 * 实现跨批次之间维护状态，使用updateStateByKey 把数据存起来
 * 然后当前的DStream去和内存/磁盘中的数据进行交互，一起统计出来
 * 必须设置检查点路径
 * 定义状态更新函数
 *
 *
 */
object State {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("status")
        val ssc = new StreamingContext(conf, Seconds(2))

        // 设置检查点
        ssc.checkpoint("spark-warehouse")
        //设置socket源
        val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("10.2.98.132", 9999)

        // 使用updateStateByKey 进行有状态的操作
        val dStream: DStream[(String, Int)] = socketDStream.flatMap(_.split(" "))
            .map((_, 1))
            .updateStateByKey[Int](updateFunction _) // _ 把一个方法转换成一个函数

        dStream.print()

        ssc.start()

        ssc.awaitTermination()
    }

    def updateFunction(currentValues: Seq[Int], preValues: Option[Int]): Option[Int] = {
        val current = currentValues.sum
        println(currentValues)
        println(preValues)
        val pre = preValues.getOrElse(0)
        Some(current + pre)
    }
}
