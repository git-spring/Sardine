package com.star.datastructure;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Spring
 * @date 2022/10/18 9:50
 */


// java TreeSet
public class SetDemo02 {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        // 添加元素
        treeSet.add("Alice");
        treeSet.add("Candy");
        treeSet.add("Best");
        treeSet.add("Delores");
        treeSet.add("Delores");        // 重复数据不会被添加

        // 删除元素
        treeSet.remove("Best");
        //treeSet.clear();    // 清空集合
        // 修改元素

        // 遍历
        for (Object ele : treeSet){
            System.out.println(ele);
        }

        // 其他方法
        treeSet.isEmpty();
        treeSet.size();
        treeSet.contains("Best");

        // 从指定位置获取新的集合 第二个参数默认为false ,表示不包含
        SortedSet sortedSet = treeSet.headSet("Delores",false);
        SortedSet sortedSet1 = treeSet.tailSet("Best",true);

        System.out.println(sortedSet);
    }
}