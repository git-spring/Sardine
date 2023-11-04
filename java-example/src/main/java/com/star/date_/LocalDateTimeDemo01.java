package com.star.date_;

/**
 * @author Spring
 * @date 2022/10/14 15:33
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *  第三代日期类
 *   LocalDate  年月日
 *   LocalTime  时分秒
 *   LocalDateTime 年月日时分秒 JDK8 加入
 */
public class LocalDateTimeDemo01 {
    public static void main(String[] args) {

        // LocalDateTime
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int month = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        int second = ldt.getSecond();
        System.out.println("年: " + year);
        System.out.println("月: " + month);
        System.out.println("日: " + dayOfMonth);
        System.out.println("时: " + hour);
        System.out.println("分: " + minute);
        System.out.println("秒: " + second);

        // DateTimeFormatter 格式日期类 和 SimpleDateFormat 类似
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = dtf.format(ldt);
        System.out.println(date);    // 2022-10-14 16:08:57

    }
}