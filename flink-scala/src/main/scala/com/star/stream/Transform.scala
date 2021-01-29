package com.star.stream

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.scala._

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

        env.execute("------ map ------")
    }


    /**
     * keyBy
     * 根据Key进行分区，是根据key的散列值进行分区的
     */
    def keyByOP(): Unit = {
        val peoStream = line.map(x => {
            val column: Array[String] = x.split(",")
            People(column(0).toInt, column(1), column(2).toInt)
        })

        peoStream.keyBy("id")


    }


    def main(args: Array[String]): Unit = {
        // mapOP()

    }

}

case class People(id: Int, name: String, age: Int)
