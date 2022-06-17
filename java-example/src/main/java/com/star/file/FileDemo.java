package com.star.file;

import com.star.IO.ReaderWriter;
import org.apache.log4j.Logger;

import java.io.File;
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
     * @return 目录下所有的文件名称
     */
    public static List<String> getFileName(String path) {
        logger.info("开始读取目录下的文件");
        File directory = new File(path);
        File[] files = directory.listFiles();              // 获取文件的全路径
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                return getFileName(files[i].getAbsolutePath()); // 如果是目录,则向下递归
            } else {
                list.add(files[i].getName());              // 如果是文件,则添加到集合中
            }
        }
        return list;
    }


}