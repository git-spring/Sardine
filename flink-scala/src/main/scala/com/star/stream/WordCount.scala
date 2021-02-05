package com.star.stream

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * @author: liudw
 * @date: 2021-1-25 13:40
 */

// flink 流处理
object WordCount {
    def main(args: Array[String]): Unit = {

        // 获取flink流处理执行环境
        val env = StreamExecutionEnvironment.getExecutionEnvironment
        // 监听服务器上的端口
        val line = env.socketTextStream("10.2.98.132",9999)

        import org.apache.flink.api.scala._
        // 处理数据
        val resultStream = line.flatMap(_.split(" "))
            .filter(_.nonEmpty)
            .map((_, 1))
            .keyBy(0)
            .sum(1)

        resultStream.print()

        // 启动执行任务
        env.execute()

    }
}
