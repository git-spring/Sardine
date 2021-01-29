package com.star.utils;

/*
 *  @author:   liudw
 *  @date:  2020-10-23
 */

import com.star.common.ConfigInfo;
import com.star.common.Constant;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 *  将kafka的offset 保存在redis中管理
 *  提供存储、获取offset的方法
 *
 *  offset 在redis中的类型为hash
 *  key   : 自定义标识 (TOPIC_OFFSETS) kafka的offset都存储在这个key下
 *  field : topic,partition
 *  value : offset
 */
public class OffsetManager {

	/**
	 *  从redis中获取kafka的offset
	 * @return
	 */
	public static Map<TopicPartition, Long> getOffsetFromRedis() {
		Jedis jedis = RedisUtils.getRedisConn();
		// 读取redis中的offset
		Map<String, String> map = jedis.hgetAll(Constant.KAFKA_TOPIC_OFFSETS);
		Map<TopicPartition, Long> offsetMap = new HashMap<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String[] split = entry.getKey().split(Constant.SEPARATOR);
			String topic = split[0];
			int partition = Integer.valueOf(split[1]);
			Long offset = Long.valueOf(entry.getValue());
			TopicPartition topicPartition = new TopicPartition(topic, partition);
			offsetMap.put(topicPartition, offset);
		}
		return offsetMap;
	}


	/**
	 *  更新offset到redis
	 */
	public static void updateTopicOffset2Redis(Map<TopicPartition, Long> offsetMap) {
		Jedis jedis = RedisUtils.getRedisConn();
		for (Map.Entry<TopicPartition, Long> entries : offsetMap.entrySet()) {
			TopicPartition topicPartition = entries.getKey();
			String topic = topicPartition.topic();
			int partition = topicPartition.partition();
			String offset = String.valueOf(entries.getValue());
			jedis.hset(Constant.KAFKA_TOPIC_OFFSETS, topic + Constant.SEPARATOR + partition, offset);
		}
	}

	/**
	 *  把offset 更新到redis的备份key中
	 */
	public static void updateTopicOffset2RedisBak() {
		Jedis jedis = RedisUtils.getRedisConn();
		Map<TopicPartition, Long> offsetMap = getOffsetFromRedis();
		if (offsetMap.size() > 0) {
			for (Map.Entry<TopicPartition, Long> entries : offsetMap.entrySet()) {
				TopicPartition topicPartition = entries.getKey();
				String topic = topicPartition.topic();
				int partition = topicPartition.partition();
				String offset = String.valueOf(entries.getValue());
				jedis.hset(Constant.KAFKA_TOPIC_OFFSETS + "_BACKUP", topic + Constant.SEPARATOR + partition, offset);
			}
		}
	}

	public static Set<TopicPartition> tpSet(KafkaConsumer consumer) {
		List<PartitionInfo> list = consumer.partitionsFor(Constant.TOPIC);
		HashSet<TopicPartition> set = new HashSet<>();
		for (PartitionInfo tpi : list) {
			TopicPartition topicPartition = new TopicPartition(Constant.TOPIC, tpi.partition());
			set.add(topicPartition);
		}
		return set;
	}

	/**
	 *  获取指定topic下各个分区的last offset
	 * @param topic
	 */
	public static Map<TopicPartition, Long> getOffsetForTopic(String topic) {
		Properties props = ConfigInfo.kafkaProducerParam();
		KafkaConsumer consumer = new KafkaConsumer(props);
		List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);

		List<TopicPartition> tp = new ArrayList<>();
		partitionInfos.forEach(info -> {
			tp.add(new TopicPartition(topic, info.partition()));
			consumer.assign(tp);
			// get last offset of per partition
			// consumer.seekToEnd(tp);

		});
		// get last offset of per partition
		Map<TopicPartition, Long> partitionOffsets = consumer.endOffsets(tp);
		return partitionOffsets;
	}


	public static void main(String[] args) {
		System.out.println(getOffsetForTopic("ldd-test"));
	}

}
