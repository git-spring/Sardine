package com.star.datastructure;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Spring
 * @date 2022/10/17 17:43
 */


/**
 *  java Set
 *
 *  HashSet : 基于 HashMap实现, 集合内不允许重复值,集合内的值是无序的
 *  TreeSet : 1. SortedSet的实现类,基于 二叉树 实现
 *            2. 集合内的元素有顺序(升序或自定义排序)
 */
public class SetDemo01 {
    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet<>();
        // 添加元素
        hashSet.add("Alice");
        hashSet.add("Best");
        hashSet.add("Candy");
        hashSet.add("Delores");
        hashSet.add("Delores");// 重复数据不会被添加

        // 删除元素
        hashSet.remove("Candy");
        //hashSet.clear();   // 清空集合的元素

        // 修改数据
        // 不能修改

        // 获取元素 遍历

        // 遍历
        for(Object ele : hashSet){
            System.out.println(ele);
        }

        // 其他方法
        hashSet.isEmpty();     //  集合是否为空
        hashSet.size();        // 集合的元素个数
        hashSet.contains("Alice");   // 检查集合是否包含此元素
        Iterator<Object> iterator = hashSet.iterator(); // 获取迭代器

    }
}