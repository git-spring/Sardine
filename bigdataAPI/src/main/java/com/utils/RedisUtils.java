package com.utils;

/*
 *  @author:   liudw
 *  @date:  2020-10-23
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	private static JedisPoolConfig config;
	private static JedisPool jedisPool;
	private static Jedis jedis;


	public static final int maxIdle = 10;
	public static final long maxWaitMillis = 1000;
	public static final int maxTotal = 50;
	public static final int minIdle = 5;
	public static final String redisHost = "dcdl-dgv-cdh8.localhost.com";
	public static final int redisPort = 6379;


	/**
	 *  对外部提供 redis 的连接对象
	 * @return jedis对象
	 */
	public static Jedis getRedisConn(){
		config = new JedisPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setMaxTotal(maxTotal);
		config.setMinIdle(minIdle);
		jedisPool = new JedisPool(config, redisHost, redisPort);
		jedis = jedisPool.getResource();
		jedis.auth("1qaz@WSX3edc");
		// 使用15号库
		jedis.select(15);
		return jedis;
	}





}
