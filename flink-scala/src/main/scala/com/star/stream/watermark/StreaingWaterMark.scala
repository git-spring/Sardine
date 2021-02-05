package com.star.stream.watermark


import java.text.SimpleDateFormat

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector

import scala.collection.mutable.ArrayBuffer

/**
 * @author: liudw
 * @date: 2021-2-4 9:47
 */

/**
 * flink 的水位线
 *
 * 输入数据格式：
 * nc -lk 9999
 * 0001,153835988200
 * 0001,153835988600
 * 0001,153835989200
 * 0001,153835988600
 *
 */
object StreaingWaterMark {
    def main(args: Array[String]): Unit = {

        val env = StreamExecutionEnvironment.getExecutionEnvironment
        val text = env.socketTextStream("10.2.98.132", 9999)

        // 处理数据
        val inputMap = text.flatMap(_.split(" "))
            .map((_, 1))

        // 抽取timestamp, 生成watermark
        val watermarkStream = inputMap.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[(String, Int)] {
            var currentMaxTimestamp = 0
            val maxOutOfOrderness = 10000 // 最大允许的乱序时间

            private val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

            // 定义生成watermark的逻辑,
            override def getCurrentWatermark = {
                new Watermark(currentMaxTimestamp - maxOutOfOrderness)
            }

            // 定义如何提取watermark
            override def extractTimestamp(element: (String, Int), previousElementTimestamp: Long) = {
                val timestamp = element._2
                currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp)
//                println("key:" + element._1 + ",eventtime:[" + element._2 + "|" + sdf.format(element._1) + "],currentMaxTimestamp:[" + currentMaxTimestamp + "|" +
//                    sdf.format(currentMaxTimestamp) + "],watermark:[" + getCurrentWatermark().getTimestamp() + "|" + sdf.format(getCurrentWatermark().getTimestamp()) + "]")
println("key:"+element._1)
                timestamp
            }
        })
        val windowStream = watermarkStream.keyBy(0)
            .window(TumblingEventTimeWindows.of(Time.seconds(3))) //
            .apply((x, y, z, j: Collector[String]) => {
                val key = x.toString
                var arr = new ArrayBuffer[Long]()
                val it = z.iterator

                while (it.hasNext) {
                    val next = it.next()
                    arr += next._2
                }
                import java.text.SimpleDateFormat
                val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                val result = key + "," + arr.size + "," + sdf.format(arr(0)) + "," + sdf.format(arr(arr.size - 1)) + "," + sdf.format(y.getStart) + "," + sdf.format(y.getEnd)

                j.collect(result)
            })

        windowStream.print()

        env.execute()

    }
}
