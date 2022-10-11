package com.star.IO_;


import com.star.utility.StartIO;
import org.apache.log4j.Logger;

import java.io.*;

// java 字节流
public class StreamDemo {

    public static void main(String[] args) throws IOException {
        inputDemo();
        //outputDemo();
        //copy("java-example/file/poem.txt", "java-example/file/output.txt", 1024);
    }

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
            int len = 0;
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

    // 复制
    // 从输入流读取数据,使用输出流输出
    public static void copy(String source, String desc, int buffSize) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(desc));

        byte[] buf = new byte[buffSize];
        int len;

        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
            out.flush();
        }
    }




}



