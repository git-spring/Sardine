package com.star.logging.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author: liudw
 *  @date: 2021-3-22 14:20
 */

public class SLF4JTest {

	public static final Logger LOGGER = LoggerFactory.getLogger(SLF4JTest.class);

	public static void slf4jDemo() {
		// 日志输出
		LOGGER.error("error msg");
		LOGGER.warn("warn msg");
		LOGGER.info("info msg");   // 默认的日志级别
		LOGGER.debug("debug msg");
		LOGGER.trace("trace msg");

		// 使用点位符输出
		String name = "潜龙";
		int age = 20;
		LOGGER.info("用户:{},{}", name, age);

		// 输出异常信息
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error("出现异常", e);
		}

	}


	public static void main(String[] args) {
		slf4jDemo();
	}
}
