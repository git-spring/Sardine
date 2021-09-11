package com.star.batch

import org.apache.flink.api.scala.ExecutionEnvironment


/**
  * @author: liudw
  * @date: 2021-1-25 10:00
  */

// flink 批处理
object WordCount {
    def main(args: Array[String]): Unit = {
        val input = "flink-scala/in/word.txt"
        // 获取批处理的执行环境
        val env = ExecutionEnvironment.getExecutionEnvironment
        // 获取数据
        val line = env.readTextFile(input)

        import org.apache.flink.api.scala._
        // 统计数据
        line.flatMap(_.toLowerCase.split(" "))
                .filter(_.nonEmpty)
                .map((_, 1))
                .groupBy(0)
                .sum(1)
                .print()

    }
}
