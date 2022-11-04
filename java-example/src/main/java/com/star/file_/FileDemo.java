package com.star.file_;

import java.io.File;
import java.io.IOException;

/**
 * @author liudw
 * @date 2022/11/4 9:48
 */

// File 类常用方法
public class FileDemo {
    public static void main(String[] args) throws IOException {
        // 创建File对象,路径可以是目录或文件
        File file = new File("java-example/log/");

        // 常用方法

        // 权限相关
        file.canRead();                   // 是否可读
        file.canWrite();                  // 是否可写
        file.canExecute();                // 是否可执行

        file.setReadOnly();               // 设置可读
        file.setWritable(true);           // 设置可写
        file.setExecutable(true);         // 设置可执行

        // 信息相关
        file.exists();                    // 文件/目录是否存在
        file.isDirectory();               // 是否目录
        file.isFile();                    // 是否文件
        file.length();                    // 获取文件的大小

        String name = file.getName();     // 获取名称
        String parent = file.getParent(); // 获取名称的父目录

        String path = file.getPath();     // 获取相对路径
        String absolutePath = file.getAbsolutePath();  // 获取绝对路径    // E:\04javaProject\Sardine\..\java-example\log\testA.log
        File absoluteFile = file.getAbsoluteFile();    // 获取绝对路径的File对象
        // 与 getAbsolutePath() 一样,返回绝对路径,但是返回结果更简洁
        File canonicalFile = file.getCanonicalFile();
        String canonicalPath = file.getCanonicalPath();    // 简洁的绝对路径,没有.. 这样的符号 E:\04javaProject\java-example\log\testA.log


        // 获取目录下的所有文件,包括目录, 返回的只是文件名称
        String[] list = file.list();
        // 获取目录下的所有文件,包括目录, 返回是文件的绝对路径
        File[] files = file.listFiles();

        // 文件操作
        //file.createNewFile();
        //file.delete();
        //file.mkdirs();



    }
}