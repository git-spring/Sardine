package com.utils;

/*
 *  @author:   liudw
 *  @date:  2020-10-23
 */

/**
 *  Kafka 的 topic 和 partition 组合
 *
 */
public class TopicAndPartition {

	String topic ;
	int partition;

	public TopicAndPartition(String topic ,int partition){
		this.topic=topic;
		this.partition=partition;
	}
}
