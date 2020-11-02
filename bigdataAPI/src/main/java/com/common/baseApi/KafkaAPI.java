package com.common.baseApi;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *  kafka 的 生产者和消费者
 *
 *  kafka 的api有两种,高级api和低级api
 *  1 高级API
 *   1)高级API优点
 *      高级API 写起来简单
 *      不需要去自行去管理offset,系统通过zookeeper自行管理
 *      不需要管理分区,副本等情况,系统自动管理
 *      消费者断线会自动根据上一次记录在zookeeper中的offset去接着获取数据（默认设置1分钟更新一下zookeeper中存的的offset）
 *      可以使用group来区分对同一个topic 的不同程序访问分离开来（不同的group记录不同的offset,这样不同程序读取同一个topic才不会因为offset互相影响）
 *
 *   2)高级API缺点
 *      不能自行控制offset（对于某些特殊需求来说）
 *      不能细化控制如分区、副本、zk等
 *
 * 2 低级API
 1)低级 API 优点
 *      能够开发者自己控制offset,想从哪里读取就从哪里读取。
 *      自行控制连接分区,对分区自定义进行负载均衡
 *      对zookeeper的依赖性降低（如：offset不一定非要靠zk存储,自行存储offset即可,比如存在文件或者内存中）
 *
 *    2)低级API缺点
 *      太过复杂,需要自行控制offset,连接哪个分区,找到分区leader 等。
 *
 * @author Spring
 */
public class KafkaAPI {

	/*
	 ********************* 高级 API (The high-level Consumer API) *********************
	 */

	/**
	 *  kafka 的生产者
	 */
	public static void producer() throws ExecutionException, InterruptedException {
		Properties props = new Properties();
		// kafka集群服务器
		props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
		// 设置 ack
		props.put("acks", "0");
		// 设置重试次数,默认为 0
		props.put("retries", 3);

		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		// key和value 的序列化类型
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 使用自定义的分区器, 使用默认的则不加
		props.put("partitioner.class", "com.kafka.KafkaPartition");
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
		props.put("enable.auto.commit", "false");
		// 自动确认offset的时间间隔
		//        props.put("auto.commit.interval.ms", "1000");
		// key和value的反序列化类型
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// 创建KafkaConsumer
		KafkaConsumer consumer = new KafkaConsumer(props);
		// 指定 订阅的topic,可以订阅多个
		consumer.subscribe(Arrays.asList("asset_topic"));
		try {
			while (true) {
				// 获取数据
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf(" topic = %s, partition = %s, offset = %d, value = %s%n", record.topic(), record.partition(), record.offset(), record.value());
				}
				consumer.commitAsync(); // 异步提交offset, 正常处理数据时,异步提交,不影响效率
			}
		} catch (Exception e) {
			// 在这里处理异常
		} finally {
			try {
				consumer.commitSync(); // 最后一次(或程序报错时)提交使用同步阻塞式提交
			} finally {
				consumer.close();
			}
		}
	}


	public static void main(String[] args) throws ExecutionException, InterruptedException {
		producer();
		//        consumer();
	}


	/**
	 * 低级 API (The SimpleConsumer API)
	 * @see SimpleExample
	 */


}