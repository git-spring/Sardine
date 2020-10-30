package com.common.baseApi;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 *  kafka 的 生产者和消费者
 * @author Spring
 */
public class KafkaAPI {

    /**
     *  kafka 的生产者
     */
    public static void producer() {
        Properties props = new Properties();
        // kafka集群服务器
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        // 设置 ack
        props.put("acks", "0");
        // 设置重试次数，默认为 0
        props.put("retries", 3);

        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        // key和value 的序列化类型
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 使用自定义的分区器, 使用默认的则不加
        props.put("partitioner.class","com.kafka.KafkaPartition");
        //Kafka
        KafkaProducer producer = new KafkaProducer(props);
        producer.send(new ProducerRecord("ldd-test", "31"));
        producer.flush();
    }

    /**
     *  kafka 的消费者
     */
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
                System.out.printf(" topic = %s, partition = %s, offset = %d, value = %s%n", record.topic(), record.partition(), record.offset(), record.value());
//                System.out.println(record);
                Set assignment = consumer.assignment();
                System.out.println(assignment);
            }
            consumer.close();
        }

    }


    public static void main(String[] args) {
        producer();
//        consumer();
    }
}