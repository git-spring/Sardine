package com.star.stream

import java.text.SimpleDateFormat

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author: liudw
 * @date: 2021-1-29 14:51
 */

// flink 的算子
object Transform {
    // 创建flink流处理的执行环境
    private val env = StreamExecutionEnvironment.getExecutionEnvironment
    val line = env.readTextFile("flink-scala/in/word.txt")

    /**
     * map :
     * 把每个元素变换后,映射成另一个元素
     *
     */
    def mapOP(): Unit = {


        // 使用lambda函数
        val mapStream1 = line.map(x => x)

        // 使用MapFunction
        val mapStream2 = line.map(new MapFunction[String, String] {
            override def map(value: String) = {
                value.toUpperCase()
            }
        })

        mapStream2.print()

    }


    /**
     * keyBy
     * 根据Key进行分区，是根据key的散列值进行分区的
     */
    def keyByOP(): Unit = {
        val sensorStream = line.map(x => {
            val column: Array[String] = x.split(",")
            Sensor(column(0).trim, column(1).toLong, column(2).toDouble)
        })

        val keyByStream: KeyedStream[Sensor, Tuple] = sensorStream.keyBy(0)
        val keyType = keyByStream.getKeyType
        println(keyType)
        keyByStream.print()

    }


    def filterOP(): Unit = {
        val filterStream = line.filter(_.nonEmpty)
        filterStream.print()
    }


    def windowOP(): Unit = {
        line.map(
            data => {
                val arr = data.split(",")
                Sensor(arr(0).toString, arr(1).toLong, arr(2).toDouble)
            }
        )
            .map(data => (data.id, data.temperature))
            .keyBy(_._1)
            .window(TumblingEventTimeWindows.of(Time.seconds(10)))

    }


    def splitOP(): Unit = {
        line.map(
            data => {
                val arr = data.split(",")
                Sensor(arr(0).toString, arr(1).toLong, arr(2).toDouble)
            }
        )
    }

    def main(args: Array[String]): Unit = {
        // mapOP()
        windowOP()
        env.execute()

    }
}

case class Sensor(id: String, timestamp: Long, temperature: Double)

class BoundedOutOfOrdernessGenerator extends AssignerWithPeriodicWatermarks[String] {
    override def getCurrentWatermark = ???

    override def extractTimestamp(element: String, previousElementTimestamp: Long) = ???
}