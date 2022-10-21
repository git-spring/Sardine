package com.star.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring
 * @date 2022/10/17 14:41
 */


/**
 *   java List
 *     List 中有3个常用的类
 *     ArrayList  : 基于动态数组 查询快,增删慢
 *     LinkedList : 基于链表    查询慢,增删快
 *     Vector     : 和 ArrayList 用法一样,但是线程安全,效率低. ArrayList 线程不安全,效率高
  */

public class ListDemo01 {
    public static void main(String[] args) {
        // 创建 ArrayList对象
        List list = new ArrayList();


        // 添加元素
        list.add("Larry ");
        list.add(0,"Alice");  // 在指定索引添加元素
        list.add(0,"Bob");
        list.addAll(list);   // 把集合添加到集合中

        // 删除元素
        //list.clear();            // 清空集合
        list.remove("Alice");   // 删除一个元素,若元素不存在,不会报错,若有多个相同元素,只会删除第一个
        list.remove(0);     //  删除指定索引的元素

        // 修改元素
        list.set(0,"Larry");        // 修改指定索引的元素

        // 查询元素
        Object obj = list.get(0);
        int idx = list.indexOf("Larry");

        // 遍历
        for(Object ele: list){
            System.out.println(ele);
        }


        // 其他
        list.size();      // 获取集合中元素数量
        list.contains("Larry"); // 判断集合中是否包含此元素
        list.isEmpty();   // 判断集合是否为空
        list.iterator();  // 获取集合的迭代器

    }
}