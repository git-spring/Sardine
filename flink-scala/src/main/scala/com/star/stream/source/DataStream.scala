package com.star.stream.source

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * @author: liudw
 * @date: 2021-1-26 15:23
 */

// flink 流处理读取数据的方式
object DataStream {
    def main(args: Array[String]): Unit = {

        val env = StreamExecutionEnvironment.getExecutionEnvironment

        // 1. 从集合中读取数据 因为这种方式的数据量是固定的，所以程序运行后不会等待
        val datalist = List(
            Info("1", "1547718199", 35),
            Info("6", "1547718201", 15),
            Info("", "1547718202", 26),
            Info("", "1547718205", 38)
        )
        val stream1 = env.fromCollection(datalist)

        // 2. 从文件中读取数据  因为这种方式的数据量是固定的，所以程序运行后不会等待
        val inpath = "flink-scala/in/word.txt"
        val stream2 = env.readTextFile(inpath)

        /**
         * 3. 从socket中读取 [[com.star.stream.WordCount]]
         *
         * 4. 从外部数据源读取数据(e.g. kafka) [[com.star.stream.source.FlinkKafka]]
         *
         * 5. 自定义数据源   [[com.star.stream.source.UDS]]
         *
         */


        stream2.print()

        env.execute("Aname")
    }

}


case class Info(id: String, tel: String, age: Int)