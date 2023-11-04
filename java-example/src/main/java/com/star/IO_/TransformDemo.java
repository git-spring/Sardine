package com.star.IO_;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * @author liudw
 * @date 2022/11/7 11:22
 */

// java 转换流
// 转换流是字节流到字符流的桥梁
public class TransformDemo {

    public static void inputDemo() {
        Logger logger = Logger.getLogger(StreamDemo.class);
        InputStreamReader isr = null;
        String inputPath = "java-example/file/poem.txt";
        try {
            isr = new InputStreamReader(new BufferedInputStream(new FileInputStream(inputPath)), "UTF-8");
            logger.info("开始读取数据");
            char[] ch = new char[1024];
            int len;
            while ((len = isr.read(ch)) != -1) {
                System.out.println(ch);
            }
            logger.info("读取完毕");
        } catch (FileNotFoundException e) {
            logger.error("文件不存在");
        } catch (IOException e) {
            logger.error("文件状态发生错误");
        } finally {
            StartIO.safelyClose(isr);
        }
    }

    // 字节输出流
    public static void outputDemo() {
        Logger logger = Logger.getLogger(StreamDemo.class);
        OutputStreamWriter osw = null;
        String outputPath = "java-example/file/output.txt";
        try {
            osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outputPath)), "UTF-8");
            logger.info("文件的输出路径： " + outputPath);
            osw.write("this is a message,哈哈");
            osw.flush();
        } catch (FileNotFoundException e) {
            logger.error(outputPath + "文件路径不存在");
        } catch (IOException e) {
            logger.error("文件发生错误");
        } finally {
            StartIO.safelyClose(osw);
        }
    }

}