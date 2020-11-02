package com.kafka;

/*
 *  @author:   liudw
 *  @date:  2020-11-2
 */


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *  kafka 发送数据的三种方式
 */
public class MyProducer {

	static {
		Properties props = new Properties();
		props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
		props.put("acks", "0");
		props.put("retries", 3);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer(props);
	}
	private static KafkaProducer<String,String> producer;

	/**
	 *  第一种 异步发送数据,只管发送,不管结果
	 */
	private void sendMessageAsync(){
		ProducerRecord<String,String> record = new ProducerRecord("ldd-test","name","this is a message send with async");
		producer.send(record);
		producer.close();
	}

	/**
	 *  第二种 同步发送数据,等待执行结果
	 */
	private void sendMessageSync() throws ExecutionException, InterruptedException {
		ProducerRecord<String,String> record = new ProducerRecord("ldd-test","name","this is a message send with sync");
		Future<RecordMetadata> future = producer.send(record);
		//等待kafka人确认回复,遇到错误则抛出异常,成功则返回RecordMetadata
		RecordMetadata recordMetadata = future.get();
	}

	/**
	 *  第三种 带回调函数发送数据
	 */
	private void sendMessageCallbak(){
		ProducerRecord<String,String> record = new ProducerRecord<String,String>("kafka-study","name","callback");
		producer.send(record,new MyProducerCallback());
	}

	/**
	 *  回调函数
	 */
	private class MyProducerCallback implements Callback {

		@Override
		public void onCompletion(RecordMetadata metadata, Exception e) {
			if (e !=null){
				e.printStackTrace();
				return;
			}
			// 可以在回调函数中记录失败的信息
			System.out.println(metadata.topic());
			System.out.println(metadata.partition());
			System.out.println(metadata.offset());
		}
	}
}
