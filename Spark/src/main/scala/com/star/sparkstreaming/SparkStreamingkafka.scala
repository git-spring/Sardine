package com.star.sparkstreaming

import org.apache.hadoop.security.UserGroupInformation
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: liudw
 * @date: 2021-1-11 14:46
 */

// SparkStreaming 与Kakfa 整合使用
// 需要导入 spark-streaming-kafka-0-10_2.11 jar
object SparkStreamingkafka {

    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]")
            .setAppName("SparkStreaming-Kafka")
            .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        // conf.registerKryoClasses(Array(
        // classOf[Array[org.apache.kafka.clients.consumer.ConsumerRecord[String,String]]]
        // ))
        val ssc = new StreamingContext(conf, Seconds(5))
        val kafkaParams = getKafkaParams()
        val topics = Array("test4").toSet
        // 通过KafkaUtils 创建出DStream
        val dStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
            ssc,
            LocationStrategies.PreferConsistent,
            ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
        )
        // 在这里做数据处理
        dStream.print()

        ssc.start()

        ssc.awaitTermination()
    }


    // 获取kafka连接时的参数信息
    def getKafkaParams(): Map[String, Object] = {
        var kafkaParams = Map[String, Object](
            "bootstrap.servers" -> "10.2.98.128:9092",
            "key.deserializer" -> classOf[StringDeserializer],
            "value.deserializer" -> classOf[StringDeserializer],
            "auto.offset.reset" -> "earliest",
            "group.id" -> "console-consumer-17399")
        kafkaParams
    }
}


// 有状态与无状态的数据