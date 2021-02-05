package com.star.stream.source

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/**
 * @author: liudw
 * @date: 2021-1-26 16:45
 */

// flink 自定义数据源
// 自定义Source常用于本地测试、调试代码,基本不用于生产
object UDS {
    def main(args: Array[String]): Unit = {

        val env = StreamExecutionEnvironment.getExecutionEnvironment
        // 添加自定义的数据源
        val dataSource = env.addSource(new CustomSource())

        dataSource.print()

        env.execute()

    }

    class CustomSource extends SourceFunction[Long] {
        private var isRunning = true
        private var count = 1

        override def run(ctx: SourceFunction.SourceContext[Long]) = {
            while (isRunning) {
                ctx.collect(count)
                count += 1
                //每秒产生一条数据
                Thread.sleep(1000)
            }
        }

        override def cancel() = {
            isRunning = false
        }

    }

}
