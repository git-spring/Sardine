package com.utils;

/*
 *  @author:   liudw
 *  @date:  2020-10-23
 */

import com.common.Constant;

import java.util.HashMap;
import java.util.Map;

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
	 * @return topic partition offset 的组合
	 */
	public static Map<TopicAndPartition, Long> getOffsetFromRedis() {
		Jedis jedis = RedisUtils.getRedisConn();
		// 读取redis中的offset
		Map<String, String> map = jedis.hgetAll(Constant.KAFKA_TOPIC_OFFSETS);
		
		Map<TopicAndPartition, Long> offsetMap = new HashMap<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String[] split = entry.getKey().split(",");
			String topic  = split[0];
			int partition = Integer.valueOf(split[1]);
			Long offset = Long.valueOf(entry.getValue());
			TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
			offsetMap.put(topicAndPartition,offset);
		}
		return offsetMap;
	}


	/**
	 *  更新offset到redis
	 */
	public static void updateTopicOffset2Redis(){



		Jedis jedis = RedisUtils.getRedisConn();
//		jedis.hset();
	}
}
