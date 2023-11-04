package com.star.file_;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Spring
 * @date 2022/6/7 10:00
 * @describe :
 */

// java File 工具类
public class FileUtil {

    static Logger logger = Logger.getLogger(FileUtil.class);
    // 存储文件名称的集合,因为要使用递归,所以不能在方法内部创建(每次递归会被覆盖)
    static ArrayList<String> list = new ArrayList<>();


    // 删除指定目录下的文件/空目录
    public static void clearUp(File directory )  {
        clearFile(directory);
        clearEmptyDirectory(directory);

    }

    public static void clearDirectory(String directory)  {
        clearUp(new File(directory));
    }


    // 删除目录下以 指定字符串结尾的文件
    public static void clearFile(File directory) {
        if (directory != null) {
            if (directory.getName().endsWith(".lastUpdated")) {
                directory.delete();
            }
        }
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                clearFile(file);
            }
        }
    }

    // 删除空目录
    public static void clearEmptyDirectory(File directory) {
        if (directory != null && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files.length == 0) {
                System.out.println(directory + " is a empty dirtory.");
                directory.delete();
            }else{
                for (File file: files){
                    clearEmptyDirectory(file);
                }
            }
        }
    }


    /**
     * 遍历目录下的文件
     *
     * @param directory 需要遍历的目录
     * @return 目录下所有的文件的名称
     */
    public static List<String> getFileName(File directory) throws FileNotFoundException {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new FileNotFoundException(directory + "  not exists or not a directory");
        }

        logger.info("开始读取目录下的文件");
        File[] files = directory.listFiles();              // 获取文件的全路径
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getFileName(new File(files[i].getAbsolutePath()));   // 如果是目录,则向下递归
            } else {
                list.add(files[i].getName());              // 如果是文件,则添加到集合中
            }
        }
        return list;
    }

    public static List<String> getFileName(String path) throws FileNotFoundException {
        return getFileName(new File(path));
    }

    /**
     * 遍历目录下的文件
     * 返回文件的全路径
     *
     * @param directory
     * @return
     */
    public static List<String> getFileAbsPath(File directory) throws FileNotFoundException {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new FileNotFoundException(directory + " not exists or not a directory");
        }
        logger.info("开始读取目录下的文件");
        File[] files = directory.listFiles();              // 获取文件的全路径
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getFileAbsPath(new File(files[i].getAbsolutePath())); // 如果是目录,则向下递归
            } else {
                list.add(files[i].getAbsolutePath());              // 如果是文件,则把文件的全路径添加到集合中
            }
        }
        return list;
    }


    public static List<String> getFileAbsPath(String path) throws FileNotFoundException {
        return getFileAbsPath(new File(path));
    }

    /**
     * 获取目录下文件的大小
     * c盘下 Users, Windows, Program Files, Program Files (x86) 不能读取
     *
     * @param directory
     * @return
     * @throws FileNotFoundException
     */
    public static Map<String, Long> getFileSize(File directory) throws FileNotFoundException {
        Map<String, Long> map = new HashMap<>();
        List<String> fileName = getFileAbsPath(directory);
        for (String path : fileName) {
            map.put(path, new File(path).length());
        }
        return map;
    }
    
    public static Map<String, Long> getFileSize(String directory) throws FileNotFoundException {
        return getFileSize(new File(directory));
    }


}