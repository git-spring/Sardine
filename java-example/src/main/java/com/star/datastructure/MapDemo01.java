package com.star.datastructure;

/**
 * @author Spring
 * @date 2022/10/18 10:05
 */


import java.util.HashMap;
import java.util.Map;

/**
 * java Map
 *   HashMap : 基于哈希表的Map接口实现,
 *             底层是一个数组结构,数组中的每一项又是一个链表当新建一个HashMap的时候,就会初始化一个数组
 *             Map 的key不能重复,且key-value对是无序的
 *   TreeMap : 会对集合内的key排序,默认自然排序,也可以在创建对象时指定排序器
 *   HashTable : HashTable是遗留类,很多常用功能与HashMap类似,
 *              不同的是它承自Dictionary类,并且是线程安全的,
 *              并发性不如ConcurrentHashMap,因为ConcurrentHashMap引入了分段锁
 *              Hashtable不建议在新代码中使用,不需要线程安全的场合可以用HashMap替换,需要线程安全的场合可以用ConcurrentHashMap替换
 */
public class MapDemo01 {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        // 添加元素
        hashMap.put(1, "天机老人-天机棒");
        hashMap.put(2, "上官金虹-子母龙凤环");
        hashMap.put(3, "李寻欢-小李飞刀");
        hashMap.put(4, "郭嵩阳-嵩阳铁剑");
        hashMap.put(5, "吕凤先-银戟温侯");
        hashMap.put(6, "无名氏-*");
        hashMap.put(7, "西门柔-神鞭");
        hashMap.put(8, "诸葛刚-金刚铁拐");
        hashMap.put(9, "伊哭-青魔手");
        hashMap.put(10, "玉箫道人-东海玉箫");
        hashMap.put(11, "阿飞-随身宝剑🗡");

        // 删除元素
        hashMap.remove(6);
        //hashMap.clear();   // 清空Map
        // 修改元素
        hashMap.put(12, "荆无命-剑客");   // put 时,如果key已存在,则修改value,如果key不存在,则添加key-value对
        hashMap.replace(11, "高行空-判官笔");  // 替换,如果key不存在,不悔添加新的key-value
        // 获取元素
        String s = hashMap.get(11);
        String defaultValue = hashMap.getOrDefault(15, "defaultValue");  // key存在,获取value,key不存在,则获取默认值

        // 遍历
        //for (int key : hashMap.keySet()) {  // 获取到key的集合再遍历
        //    System.out.println(hashMap.get(key));
        //}
        //for (String value : hashMap.values()) {  // 直接获取value
        //    System.out.println(value);
        //}

        for (Map.Entry<Integer, String> entries : hashMap.entrySet()) {  // 获取到key-value对
            System.out.println(entries.getValue());
        }

        // 其他方法
        hashMap.size();
        hashMap.containsKey("");
        hashMap.containsValue("");
        hashMap.isEmpty();

    }
}