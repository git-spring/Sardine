package com.log;

import com.star.logging.log4j.Log4jDemo;

import org.junit.Test;

/**
 *  @author: liudw
 *  @date: 2021-3-18 10:36
 */

public class Log4jTest {

	Log4jDemo log = new Log4jDemo();
	@Test
	public void lTest(){
		log.demo();
	}

}
