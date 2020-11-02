package com.kafka;

/*
 *  @author:   liudw
 *  @date:  2020-10-15
 */

import com.common.ConfigInfo;
import com.common.Constant;
import com.utils.OffsetManager;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
	 *   设置offset 不自动提交,而是手动保存到redis中
	 */
	public static void consumer() {
		// kafka 的配置信息
		Properties props = ConfigInfo.kafkaConsumerParam();
		KafkaConsumer consumer = new KafkaConsumer(props);
		// 指定 订阅的topic,可以订阅多个
		consumer.subscribe(Arrays.asList(Constant.TOPIC));

		while (true) {
			ConsumerRecords records = consumer.poll(Duration.ofSeconds(1));
			// 从redis中获取上一次保存的offset
			Map<TopicPartition, Long> fromOffset = OffsetManager.getOffsetFromRedis();
			// 如果没有poll到数据,则重新拉取
			if (records.isEmpty()) {
				continue;
			}
			Set<TopicPartition> assignment = consumer.assignment();
			System.out.println(assignment);
			if (fromOffset.size() > 0) {
				for (TopicPartition tp : assignment) {
					// seek will Overrides the fetch offsets
					System.out.println(fromOffset.get(tp));
					consumer.seek(tp, fromOffset.get(tp) + 1);
				}
			}
			// 拉取数据,开始消费
			ConsumerRecords records1 = consumer.poll(Duration.ofSeconds(1));
			System.out.println("records.count()   =  " + records1.count());
			Iterator<ConsumerRecord<String, String>> iterator = records1.iterator();
			Map<TopicPartition, Long> offsetMap = new HashMap<>();
			while (iterator.hasNext()) {
				ConsumerRecord<String, String> record = iterator.next();
				System.out.printf("topic = %s, partition = %s, offset = %d, value = %s, timestamp=%d%n ", record.topic(), record.partition(), record.offset(), record.value(), record.timestamp());
				offsetMap.put(new TopicPartition(record.topic(), record.partition()), record.offset());
			}
			// 消费完一个批次的数据再保存offset
			if (offsetMap.size() > 0) {
				offset2redis(offsetMap);
			}
		}
	}

	public static void offset2redis(Map<TopicPartition, Long> offsetMap) {
		try {
			OffsetManager.updateTopicOffset2RedisBak();
			Thread.sleep(3000);
			OffsetManager.updateTopicOffset2Redis(offsetMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		consumer();
	}
}
