package com.common;

/*
 *  @author:   liudw
 *  @date:  2020-10-20
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigInfo {

	// 配置文件对象
	static Properties prop;

	static {
		prop = new Properties();
		InputStream in = ConfigInfo.class.getClassLoader().getResourceAsStream("company.conf");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Properties kafkaParam() {
		// kafka的配置信息
		Properties props = new Properties();

		// kafka 服务器
		props.put("bootstrap.servers", prop.getProperty("bootstrap.servers"));
		// 指定消费者组
		props.put("group.id", prop.getProperty("group.id"));
		// 设置自动提交偏移量为false,不自动提交offset
		props.put("enable.auto.commit", prop.getProperty("enable.auto.commit"));
		// 每次拉取的最大条数
		props.put("max.poll.records", prop.getProperty("max.poll.records"));
		// 设置消费的偏移量,earliest 表示各分区下有提交的offset时,从提交的offset开始消费,没有提交的offset时,从头开始消费
		props.put("auto.offset.reset", prop.getProperty("auto.offset.reset"));
		// key和value的反序列化类型
		props.put("key.deserializer", prop.getProperty("key.deserializer"));
		props.put("value.deserializer", prop.getProperty("value.deserializer"));

		return props;
	}


}
