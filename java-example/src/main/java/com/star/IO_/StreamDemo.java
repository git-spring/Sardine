package com.star.IO_;


import org.apache.log4j.Logger;

import java.io.*;


/**
 * java 字节流
 *   因为中文字节数的原因,显示中文可能乱码, 所以一般使用转换流
 *   @see InputStreamReader
 *   @see OutputStreamWriter
 */

public class StreamDemo {

    // 字节输入流
    public static void inputDemo() {
        Logger logger = Logger.getLogger(StreamDemo.class);
        BufferedInputStream bufferedInputStream = null;
        String inputPath = "java-example/file/poem.txt";
        String result;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(inputPath));
            // throw new FileNotFoundException();  // 测试异常
            logger.info("开始读取数据");
            byte[] buf = new byte[1024];
            int len ;
            while ((len = bufferedInputStream.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
            logger.info("读取完毕");
        } catch (FileNotFoundException e) {
            logger.error("文件不存在");
        } catch (IOException e) {
            logger.error("文件状态发生错误");
        } finally {
            StartIO.safelyClose(bufferedInputStream);
        }
    }

    // 字节输出流
    public static void outputDemo() {
        Logger logger = Logger.getLogger(StreamDemo.class);
        BufferedOutputStream bufferedOutputStream = null;
        String outputPath = "java-example/file/output.txt";
        System.out.println(outputPath.getClass());
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputPath));
            logger.info("文件的输出路径： " + outputPath);
            bufferedOutputStream.write("message".getBytes());
        } catch (FileNotFoundException e) {
            logger.error(outputPath + "文件路径不存在");
        } catch (IOException e) {
            logger.error("文件发生错误");
        } finally {
            StartIO.safelyClose(bufferedOutputStream);
        }
    }


}



