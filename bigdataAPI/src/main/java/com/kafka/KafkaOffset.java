package com.kafka;

/*
 *  @author:   liudw
 *  @date:  2020-10-15
 */

import com.common.ConfigInfo;
import com.common.Constant;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 *  手动维护kafka 的offset
 *
 *  老版kafka 的offset 存储在zk中;新版的offset 存储在broker中,kafka自己维护一个topic来管理offset
 *
 */
public class KafkaOffset {

	/**
	 *  kafka 的消费者
	 *   设置offset 不自动提交,而是手动提交
	 */
	public static void consumer() {
		// kafka 的配置信息
		Properties props = ConfigInfo.kafkaParam();
		// 创建KafkaConsumer
		KafkaConsumer consumer = new KafkaConsumer(props);
		// 指定 订阅的topic,可以订阅多个
		consumer.subscribe(Arrays.asList(Constant.TOPIC));

		// 获取数据
//		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf(" topic = %s, partition = %s, offset = %d, value = %s, timestamp=%d%n ",
					record.topic(), record.partition(), record.offset(), record.value(),record.timestamp());
//				System.out.println(record);
				Set assignment = consumer.assignment();
				System.out.println(assignment);
			}

			// consumer.close();

//		}
	}

	public static void main(String[] args) {
		consumer();
	}
}
