package com.star.datastructure;

import java.util.Hashtable;
import java.util.TreeMap;

/**
 * @author Spring
 * @date 2022/10/18 11:01
 */

// java TreeMap
// 与 HashMap 的主要区别
// 1. HashMap是无序的,  TreeMap 的key是有序的
// 2. 实现不同, HashMap继承AbstractMap类,是基于hash表实现的；TreeMap继承SortedMap类,是基于红黑树实现的
public class MapDemo02 {
    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap = new TreeMap<>();

        // 添加元素
        treeMap.put(1, "天机老人-天机棒");
        treeMap.put(2, "上官金虹-子母龙凤环");
        treeMap.put(11, "阿飞-随身剑🗡");
        treeMap.put(3, "李寻欢-小李飞刀");
        treeMap.put(4, "郭嵩阳-嵩阳铁剑");
        treeMap.put(7, "西门柔-神鞭");
        treeMap.put(5, "吕凤先-银戟温侯");
        treeMap.put(6, "无名氏-*");
        treeMap.put(8, "诸葛刚-金刚铁拐");
        treeMap.put(9, "伊哭-青魔手");
        treeMap.put(10, "玉箫道人-东海玉箫");

        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        for(int key : treeMap.keySet()){
            System.out.println(treeMap.get(key));
        }

    }
}



