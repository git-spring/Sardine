package com.star.day02;



/**
 * @author Spring
 * @date 2022/8/30 11:26
 */

//java array 数组
// 数组就是一组 同类型 的数据按一定顺序排列的集合
public class ArrayDemo {
    public static void main(String[] args) {
        //定义数组
        int[] ints = {1, 2, 3};
        double[] dous = {1.0, 5, 9.8, 10};

        String[] strs = {"Tom", "Alice"};   // 定义数组并初始化
        //String[] strs2 = new String[]{"Tom","Alice"};
        String[] strs1 = new String[5];    // 定义一个有5个元素的数组,里面的元素为默认值

        // 访问元素 通过下标
        System.out.println(strs[1]);
        // 修改数组中的元素
        strs[1] = "A";
        System.out.println(strs[1]);
        // 获取长度
        System.out.println("strs.length = " + strs.length);

        System.out.println("--遍历--------------------");
        // 遍历
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
    }

}