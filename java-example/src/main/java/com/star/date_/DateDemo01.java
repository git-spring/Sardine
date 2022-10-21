package com.star.date_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Spring
 * @date 2022/10/14 10:51
 */


/**
 * 第一代日期类
 *  Date
 *  SimpleDateFormat
 *
 */
public class DateDemo01 extends Date{
    public static void main(String[] args) throws ParseException {

        // Date 类
        Date date = new Date();
        System.out.println("当前时间 : "+ date);   // 当前时间
        System.out.println("当前时间戳 : "+ date.getTime());   // 当前时间戳

        Date date1 = new Date(1665716901004L);    // 通过毫秒数获得时间
        System.out.println(date1);

        // SimpleDateFormat 类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String format = sdf.format(date);   // 格式化时间
        System.out.println(format);         // 2022-10-14 11:22:01 星期五
        Date parse = sdf.parse(format);   // 把一个String 类型的日期 转成Date
        System.out.println(parse);     // Fri Oct 14 11:20:09 CST 2022

    }
}