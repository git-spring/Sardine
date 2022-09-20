package com.star.day01;

/**
 * @author Spring
 * @date 2022/8/25 15:34
 */

// java switch 匹配
// switch 表达式 可以是 byte、short、char、int类型及 这4种类型的包装类型,还有 枚举,String 共6种
// case 中的表达式可以是常量表达式,枚举常量
public class SwitchDemo {
    public static void main(String[] args) {
        String s = "hello";
        switch (s) {
            case "world":
                System.out.println("hello world");
                break; // 不写break 会穿透
            case "hello":
                System.out.println("hello hello");
            case "s":
                System.out.println("上面没有写break,这里会执行");
                break;
            default:   // 上面的都没有匹配上,则执行默认匹配
                System.out.println("默认匹配");
        }

    }
}