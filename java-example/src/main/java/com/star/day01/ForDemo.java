package com.star.day01;

/**
 * @author Spring
 * @date 2022/8/25 15:33
 */


// java for 循环
public class ForDemo {
    /**
     * for 循环语句是支持迭代的一种通用结构,是最有效,最灵活的循环结构。
     * <p>
     * 语法 :
     * for (初始化;布尔表达式;更新) {
     * //代码语句
     * }
     * 执行顺序:
     * 1 初始化 2 布尔表达式 3 代码语句 4 更新
     */

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        // 可以在外面初始化
        int i = 0;
        for (; i < 5; ) {
            System.out.println("i= " + i);
            i++;
        }

        // 与while 循环类似,如果里面不写停止条件,则会无限循环
        int count = 0;
        for (; ; ) {
            if (count < 3) {
                System.out.println("ok");
                count++;
            } else {
                break;
            }
        }
    }
}
