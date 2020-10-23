package com.kafka;

/*
 *  @author:   liudw
 *  @date:  2020-9-25
 */


import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 *  Kafka 自定义分区
 * @see org.apache.kafka.clients.producer.internals.DefaultPartitioner#partition
 */
public class KafkaPartition implements Partitioner {


	/**
	 *    自定义分区逻辑
	 * @param topic
	 * @param key
	 * @param bytes
	 * @param o1
	 * @param bytes1
	 * @param cluster
	 * @return
	 */
	@Override
	public int partition(String topic, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
		// 查看这个topic 中partition 的数量
		List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
		int size = partitionInfos.size();

		// 可以自定义分区的策略
		int partitionNum = Integer.parseInt((String) key);
		partitionNum = key.hashCode();
		return Math.abs(partitionNum % size);
	}

	@Override
	public void close() {

	}


	@Override
	public void configure(Map<String, ?> map) {

	}
}
