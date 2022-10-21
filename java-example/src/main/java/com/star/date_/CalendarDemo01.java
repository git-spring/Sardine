package com.star.date_;

/**
 * @author Spring
 * @date 2022/10/14 11:24
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 第二代日期类
 *  Calendar 日历类
 */
public class CalendarDemo01 {
    public static void main(String[] args) throws ParseException {
        // 获取 Calendar对象
        Calendar cal = Calendar.getInstance();
        // 获取当前时间
        Date date = cal.getTime();     // Fri Oct 14 14:44:53 CST 2022
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);  // 月份需要+1 因为从0开始
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);  // 24小时制
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

        System.out.println( year+"-"+(month+1)+"-"+day+" "+hour+":"+minute+":"+second);

        // 设置时间
        cal.setTime(date);
        System.out.println(cal.getTime());   // Fri Oct 14 15:13:05 CST 2022
        cal.set(Calendar.YEAR,2022);
        cal.set(Calendar.MONTH,9);    // 设置月份时要-1, 因为从0开始
        cal.set(Calendar.DATE,14);
        cal.set(Calendar.HOUR_OF_DAY,15);
        cal.set(Calendar.MINUTE,27);
        cal.set(Calendar.SECOND,20);
        System.out.println(cal.getTime());   // Fri Oct 14 15:27:20 CST 2022

        // 格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(cal.getTime());
        System.out.println(format);      // 2022-10-14 15:27:20


    }
}