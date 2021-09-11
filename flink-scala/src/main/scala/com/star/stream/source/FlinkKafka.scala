package com.star.stream.source

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011
import org.apache.flink.api.scala._

/**
  * @author: liudw
  * @date: 2021-1-26 15:33
  */

// flink 连接kafka使用
object FlinkKafka {
    def main(args: Array[String]): Unit = {

        val env = StreamExecutionEnvironment.getExecutionEnvironment

        // kafka 配置信息
        val prop = new Properties()
        prop.setProperty("bootstrap.servers", "10.2.111.54:9092")
        prop.setProperty("auto.offset.reset", "latest")
        prop.setProperty("group.id", "console-consumer-17399")

        // 添加一个数据源(kafka)
        val stream = env.addSource(new FlinkKafkaConsumer011[String]("test4", new SimpleStringSchema(), prop))

        stream.print()

        env.execute("kafka test")

    }

}
