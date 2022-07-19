package com.star.file;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring
 * @date 2022/6/7 10:00
 * @describe :
 */

public class FileDemo {

    static Logger logger = Logger.getLogger(FileDemo.class);
    // 存储文件名称的集合,因为要使用递归,所以不能在方法内部创建(每次递归会被覆盖)
    static ArrayList<String> list = new ArrayList<>();

    /**
     * 遍历目录下的文件
     *
     * @param path 需要遍历的目录
     * @return 目录下所有的文件的名称
     */
    public static List<String> getFileName(String path) {
        logger.info("开始读取目录下的文件");
        File directory = new File(path);
        File[] files = directory.listFiles();              // 获取文件的全路径
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getFileName(files[i].getAbsolutePath()); // 如果是目录,则向下递归
            } else {
                list.add(files[i].getName());              // 如果是文件,则添加到集合中
            }
        }
        return list;
    }


    /**
     *  遍历目录下的文件
     *    返回文件的全路径
     * @param path
     * @return
     */
    public static List<String> getFileAbsPath(String path) {
        logger.info("开始读取目录下的文件");
        File directory = new File(path);
        File[] files = directory.listFiles();              // 获取文件的全路径
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getFileAbsPath(files[i].getAbsolutePath()); // 如果是目录,则向下递归
            } else {
                list.add(files[i].getAbsolutePath());              // 如果是文件,则把文件的全路径添加到集合中
            }
        }
        return list;
    }


    /**
     *  获取目录下文件的大小
     *   c盘文件好像不能获取
     * @param directory 目录路径
     */
    public static void getFileSize(String directory){
        //        List<String> fileName = getFileAbsPath("C:\\Users\\Spring\\AppData\\Roaming");
        List<String> fileName = getFileAbsPath(directory);
        for(String path : fileName){
            System.out.println(path+"\t\t\t"+ new File(path).length());
        }
    }


}