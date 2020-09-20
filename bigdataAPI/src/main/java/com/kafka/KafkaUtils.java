package com.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author Spring
 */
public class KafkaUtils {

    public static void producer() {
        Properties props = new Properties();
        // kafka集群服务器
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        // 设置 ack
        props.put("acks", "0");
        // 设置重试次数，0表示不重试
        props.put("retries", 3);

        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        // key和value 的序列化类型
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //Kafka
        KafkaProducer producer = new KafkaProducer(props);
        producer.send(new ProducerRecord("asset_topic", "31"));
        producer.flush();
    }


    public static void consumer() {
        Properties props = new Properties();
        // kafka 服务器
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        // 指定消费者组
        props.put("group.id", "test");
        // 是否自动提交commit
        props.put("enable.auto.commit", "true");
        // 自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // key和value的反序列化类型
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 创建KafkaConsumer
        KafkaConsumer consumer = new KafkaConsumer(props);
        // 指定 订阅的topic，可以订阅多个
        consumer.subscribe(Arrays.asList("asset_topic"));
        while (true) {
            // 获取数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }

    }


    public static void main(String[] args) {
//        producer();
        consumer();
    }
}