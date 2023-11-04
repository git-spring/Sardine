package com.star.datastructure;

/**
 * @author Spring
 * @date 2022/10/18 14:29
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  java 迭代器
 */
public class IteratorDemo01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> list1 = list;

        // 获取迭代器对象
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){           // hasNext 方法判断有没有下一个元素
            Integer next = iterator.next();  // next 方法获取下一个元素
            if(next==5){
                iterator.remove();           // 移除元素
            }
        }
        System.out.println(list);

        // 增强for循环,底层就是迭代器
        for(int i :list1){
            System.out.println(i);
        }


    }
}