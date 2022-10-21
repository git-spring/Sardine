package com.star.datastructure;

import java.util.LinkedList;


/**
 * @author Spring
 * @date 2022/10/17 15:19
 */


// java LinkedList 链表
public class ListDemo02 {
    public static void main(String[] args) {
        // 创建一个 LinkedList 对象
        LinkedList linkedList = new LinkedList();
        // 添加元素
        linkedList.add("Dog");

        linkedList.addFirst("Fish");     // 在开头加入元素
        linkedList.addLast("Elephant");  // 在末尾加入元素
        linkedList.add(1, "Horse");   // 在指定位置添加元素
        linkedList.add("Dog");
        linkedList.add("Dog");
        linkedList.add("Fish");
        linkedList.add("Fish");
        linkedList.add("Horse");

        // 删除元素
        //linkedList.clear();   // 清空元素
        linkedList.remove();     // 移除一个元素,默认移除第一个
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.remove("Fish");                 // 移除第一次出现的指定元素
        linkedList.remove(2);
        linkedList.removeFirstOccurrence("Dog");   // 移除第一次出现的指定元素 ,调用 remove(Object o)
        linkedList.removeLastOccurrence("Dog");   // 移除最后一次出现的指定元素

        // 修改元素
        linkedList.set(1, "GoldFish");

        // 查询元素

        Object obj = linkedList.get(0);
        Object first = linkedList.getFirst();
        Object last = linkedList.getLast();
        int idx = linkedList.indexOf("Larry");

        // 遍历
        for (Object ele : linkedList) {
            System.out.println(ele);
        }
    }
}