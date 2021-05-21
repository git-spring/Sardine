package com.logtest;

import com.star.logging.jul.JULWithProperties;

import org.junit.Test;

import java.io.IOException;

/**
 *  @author: liudw
 *  @date: 2021-3-17 16:26
 */

public class LogTest {

	@Test
	public  void demo() throws IOException {
		JULWithProperties jp = new JULWithProperties();
		jp.logProperties();
	}
}
