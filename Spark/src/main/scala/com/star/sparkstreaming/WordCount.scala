package com.star.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: liudw
 * @date: 2021-1-11 13:44
 */

// 使用SparkStreaming 完成wordcount
object WordCount {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc: StreamingContext = new StreamingContext(conf, Seconds(5))
        // 监听ip:port 的数据   可以在服务器上使用 nc -lk 9999  发送数据
        val socketDstream: ReceiverInputDStream[String] = ssc.socketTextStream("10.2.98.132", 9999)
        val res: DStream[(String, Int)] = socketDstream
            .flatMap(_.split(" "))
            .map((_, 1))
            .reduceByKey(_ + _)

        // 监控文件夹(不常用)
        val textFileStream: DStream[String] = ssc.textFileStream("Spark/in")

        res.print()

        ssc.start()

        ssc.awaitTermination()

        // ssc.stop()

        /**
         * SparkStreaming 可以监听端口、监听文件夹、采集kafka中的数据等等
         *
         */


    }
}
