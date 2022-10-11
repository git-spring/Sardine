package com.star.string_;

/**
 * @author Spring
 * @date 2022/10/11 10:55
 */


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java 中 String ,StringBuffer ,StringBuilder
 *   1. String是不可变字符序列，StringBuilder和StringBuffer是可变字符序列。
 *   2. 执行速度StringBuilder > StringBuffer > String。
 *   3. StringBuilder是非线程安全的，StringBuffer是线程安全的。
 */


public class StringDemo01 {

    String value;

    public StringDemo01(String value) {
        this.value = value;
    }

    public StringDemo01() {}

    /**
     *   查找指定字符串第N次出现的位置索引
      * @param str     指定查找的字符串
     * @param number   第N次出现
     * @return         第N次出现的位置索引
     * @throws Exception   如果传入的次数大于字符出现的次数,则报错
     */
    public int indexOf(String str, int number) throws Exception {

        List<Integer> list = new ArrayList();          // 用于存放字符出现的位置

        Pattern pattern = Pattern.compile(str);
        Matcher findMatcher = pattern.matcher(value);
        while (findMatcher.find()) {
            list.add(findMatcher.start());
        }

        if(list.size() != 0 && list.size()<number)
            throw new Exception("字符 \""+str+"\" 没有出现 " + number+" 次, 只有 " +list.size()+" 次");
        // 如果没有出现则返回-1,
        return list.size() == 0? -1:list.get(number - 1);
    }

}
















// 测试效率
abstract class Template{
     abstract  void  timeConsume();

     public  void calculate(){
         long start = System.currentTimeMillis();
         timeConsume();
         long end = System.currentTimeMillis();
         System.out.println(end - start);
     }
}
