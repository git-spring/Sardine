package com.star.logging.log4j;


import org.apache.log4j.Logger;

/**
 *  @author: liudw
 *  @date: 2021-3-18 10:24
 */

// apache log4j
public class Log4jDemo {

	// log4j demo
	public static void demo() {
		// 初始化配置信息(暂不使用配置文件)
		// BasicConfigurator.configure();

		// 获取日志记录对象
		Logger logger = Logger.getLogger(Log4jDemo.class);

		// 记录日志信息  log4j 的默认日志级别是debug
		logger.fatal("fatal log");  // 严重错误,一般会造成系统崩溃并终止运行
		logger.error("error log");  // 错误信息
		logger.warn("warn log");    // 警告信息,可能会出现问题
		logger.info("info log");    // 运行信息,数据连接,网络连接,IO操作等
		logger.debug("debug log");  // 调试信息,一般在开发中使用
		logger.trace("trace log");  // 追踪信息,记录程序所有的流程信息

	}

}
