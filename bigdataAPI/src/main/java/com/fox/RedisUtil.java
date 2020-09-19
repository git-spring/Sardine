package com.fox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import static redis.clients.jedis.BinaryClient.LIST_POSITION.BEFORE;

/**
 * @author Spring
 */
public class RedisUtil {

    /*
     * redis-server 带配置文件启动 ./redis-server redis.conf &
     * redis-cli  连接 ./redis-cli --h node02
     */

    private static JedisPoolConfig config;
    private static JedisPool jedisPool;
    private static Jedis jedis;


    public static final int maxIdle = 10;
    public static final long maxWaitMillis = 1000;
    public static final int maxTotal = 50;
    public static final int minIdle = 5;
    public static final String redisHost = "dcdl-dgv-cdh7.localhost.com";
    public static final int redisPort = 6379;

    // 获取jedis连接
    static {
        config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        jedisPool = new JedisPool(config, redisHost, redisPort);
        jedis = jedisPool.getResource();
    }

    // 对key的操作
    public static void opKey() {
        // 检查一个key是否存在
        Boolean a1 = jedis.exists("a1");
        // 删除key ,可以一次删除多个
        jedis.del("delkey");
        // 查看key的类型
        jedis.type("a1");
        // 返回 匹配到的key, * 为通配符
        Set<String> keys = jedis.keys("*");
        // 重命名key
        jedis.rename("oldkey","newkey");
        // 返回 当前数据库中key的数量
        jedis.dbSize();
        // 设定key的过期时间 单位 s
        jedis.expire("a1",1000);
        // 返回 key的过期时间 单位 s
        jedis.ttl("a1");
        // 以 ms 为单位返回key的过期时间
        jedis.pttl("a1");
        // 把key 移动到另一个库中
        jedis.move("a1",2);
        /* 删除当前数据库的所有key
        jedis.flushDB();
           删除所有数据库的所有key
        jedis.flushAll();
        */

    }

    // 对 String 类型的操作
    public static void opString() {
        // redis数据库的密码
        jedis.auth("123456");
        // 设置key-string 如果key存在,则会更新
        jedis.set("a1", "10000");
        // 获取key的值
        String a1 = jedis.get("a3");
        // 设置key的值,并返回key的旧值,当旧值不存在时,返回null,如果旧的key不为String类型,则报错
        String a2 = jedis.getSet("a1", "20");
        // 批量获取值
        jedis.mset("a1","100","a2","200");
        // 批量获取多个key的值
        List<String> mget = jedis.mget("a1", "a2");
        // setnx (set if not exists) 如果key不存在则设置,key存在则不进行任何操作
        jedis.setnx("a1", "1000");
        //设置key的过期时间和值,key存在则会更新值
        jedis.setex("a3", 500, "value with expire time");
        // key 增加 1
        jedis.incr("a1");
        // key 增加指定的数字
        jedis.incrBy("a1",20);
        // key 减少 1
        jedis.decr("a1");
        // key 减少指定的数字
        jedis.decrBy("a1",20);
        // 获取 子字符串
        jedis.getrange("a1",0,3);
        // 在key 后面拼接指定字符串
        jedis.append("a1","append");
    }

    // 对 hash 的操作
    public static void opHash() {
        // 向key 中添加field和value , 这样会形成一个对象 person[name:zhangsan,age:30,city:sz]
        jedis.hset("person","name","zhangsan");
        jedis.hset("person","age","30");
        jedis.hset("person","city","sz");
        // 获取 key 中 field的值
        jedis.hget("person","name");
        // 批量设置field 和value
        Map<String, String> hash = new HashMap<>();
        hash.put("gender", "male");
        hash.put("job", "engineer");
        jedis.hmset("person", hash);
        // 批量获取field和value
        jedis.hmget("person","gender","job");
        // key 中指定 field 的value 增加 指定的 值
        jedis.hincrBy("person","age",1);
        // 检查key 中是否存在 指定的 field
        jedis.hexists("person","name");
        // 删除key中指定的field
        jedis.hdel("person","city");
        // 返回 key中field 的个数
        jedis.hlen("person");
        // 返回key中所有的field
        jedis.hkeys("person");
        // 返回key中所有field的value
        jedis.hvals("person");
        // 返回key中的field 和value
        jedis.hgetAll("person");

    }

    // 对 list 的操作
    public static void opList(){
        // 从左边向list中添加数据, 一次可以添加多个
        jedis.lpush("l1","firstvalue","secondvalue");
        // 从右边向list中添加数据, 一次可以添加多个
        jedis.rpush("l1","thirdvalue","forthvalue");
        // 获取key中的元素,索引从0开始,-1 表示最后一个
        jedis.lrange("l1",0,-1);
        // 截取list中的元素, 截取后其余的值会丢失
        jedis.ltrim("l1",0,3);
        // 返回key中元素的个数
        jedis.llen("l1");
        // 返回key中指定index位置上的元素
        jedis.lindex("l1",3);
        // 更新指定位置上的值
        jedis.lset("l1",1,"updatevalue");
        // 在指定元素的前面或后面添加元素
        jedis.linsert("l1", BEFORE,"firstvalue","insertvalue");
        // 删除指定元素,并指定数量  l1 ["10,"10","10","20","30"],  lrem("l1",2,"10"), 会变成 l1 ["10","20","30"]
        jedis.lrem("l1",2,"10");
        // 删除最左边的元素并返回值
        jedis.lpop("l1");
        // 删除最右边的元素并返回值
        jedis.rpop("l1");

    }

    // 对 set 的 操作
    public static void opSet(){
        // 向key中添加数据,一次可以添加多个，key中已存在的元素,不会添加
        jedis.sadd("s1","zhangsan","lis","six");
        // 返回key中的返回元素
        jedis.smembers("s1");
        // 删除key中的元素，一次可以删除多个
        jedis.srem("s1","six");
        // 把member 从一个key中移动到另一个key中
        jedis.smove("s1","s2","six");
        // 返回key 中元素的个数
        jedis.scard("s1");
        // 检查key中是否存在指定元素
        jedis.sismember("s1","six");
        // 多个集合的交集
        jedis.sinter("s1","s2","s3");
        jedis.sinterstore("deskey","s1","s2","s3"); // 把集合的交集保存到desKey中
        // 多个集合的并集
        jedis.sunion("s1","s2","s3");
        jedis.sunionstore("deskey","s1","s2","s3"); //把集合的并集保存到desKey中
        // 多个集合的差集
        jedis.sdiff("s1","s2","s3");
        jedis.sdiffstore("deskey","s1","s2","s3"); //把集合的差集保存到desKey中
    }

    // redis 事务
    public static void opTransaction(){
    	// 开启事务
	    Transaction trans = jedis.multi();
	    trans.set("a1","10"); // 开启事务后使用 Transaction 对象执行任务
	    trans.set("a2","20");
	    // 提交事务
	    trans.exec();

    }

	// redis watch
	public static void opWatch(){
		jedis.watch("a1","a2");
		Transaction trans = jedis.multi();
		trans.set("a1","10");
		trans.set("a2","20");
		trans.exec();   // 如果在事务提交之前，watch监听的key被其它客户端修改，事务会执行失败，
	}

    // TODO: 2020-9-10  java 连接redis集群

}