package com.star.IO;


import com.star.utility.StartIO;
import jdk.nashorn.internal.runtime.Source;
import org.apache.log4j.Logger;

import java.io.*;

// java 字符流 只能处理字符
public class ReaderWriter {

    public static void main(String[] args) throws IOException {
        //readerDemo();
        writerDemo();
        //copy("java-example/file/poem.txt","java-example/file/output.txt");
    }

    //字符输入流
    public static void readerDemo() {
        Logger logger = Logger.getLogger(ReaderWriter.class);
        BufferedReader br = null;
        String inputPath = "java-example/file/poem.txt";


        try {
            br = new BufferedReader(new FileReader(inputPath));
            // 文件是否准备好
            if (!br.ready()) {
                logger.error("文件暂时无法读取 ：" + inputPath);
                return;
            }
            logger.info("文件路径 ： " + inputPath);
            logger.info("***************开始读取***************");
            //int result = br.read(); // 读取单个字符 读取到的是int类型，可以强转成char
            //System.out.println((char)result);

            // 逐行读取
            logger.info("***************文件内容***************");
            String text;
            while ((text = br.readLine()) != null) {
                //System.out.println(text);
                logger.info(text);
            }
            logger.info("***************读取完毕***************");

            // 一次读取一个字符数组
            /*
            int size;
            char[] buf = new char[1024];
            while ((size=br.read(buf,0,buf.length))!=-1){
                System.out.println(new String(buf,0,size));
            }
            */
        } catch (IOException e) {
            logger.error("文件状态发生错误",e);
        } finally {
            StartIO.safelyClose(br);
        }
    }

    // 字符输出流
    public static void writerDemo() {
        Logger logger = Logger.getLogger(ReaderWriter.class);
        BufferedWriter bw = null;
        String outputPath = "java-example/file/output.txt";

        String s = "列缺霹雳,丘峦崩摧。";
        char[] c = s.toCharArray();

        try {
            logger.info("文件路径 ： " + outputPath);
            logger.info("***************开始写入***************");
            bw = new BufferedWriter(new FileWriter(outputPath));
            //bw.write(32456);  // 写入单个字符
            bw.write("李白"); // 写入字符串
            bw.newLine();  // 写入空行
            bw.write("云青青兮欲雨,", 0, 7); // 写入字符串的一部分
            bw.append("水澹澹兮生烟。"); // 追加
            bw.newLine();  // 写入空行
            bw.write(c, 0, c.length);  // 写入字符数组
            bw.flush();
            logger.info("***************写入完成***************");
        } catch (IOException e) {
            logger.error("文件状态发生错误",e);
        } finally {
            StartIO.safelyClose(bw);
        }
    }

    // 复制文本
    static void copy(String source, String desc) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(source));
        BufferedWriter bw = new BufferedWriter(new FileWriter(desc));

        String str ;
        while ((str = br.readLine()) != null) {
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
    }

}
