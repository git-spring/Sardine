package com.star.IO;


import com.star.file.FileDemo;
import com.star.utility.StartIO;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// java 字符流 只能处理字符
public class ReaderWriter {
    static Logger logger = Logger.getLogger(ReaderWriter.class);

    public static void main(String[] args) throws IOException {


        List<String> fileName = FileDemo.getFileName("C:\\Users\\Spring\\Desktop\\mysql2oracle");
        String dir = "C:\\Users\\Spring\\Desktop\\mysql2oracle";   // 目录
        String dir1 = "C:\\Users\\Spring\\Desktop\\oracle\\";   // 目录

        String comments = "";  // 拼接注释
        for (String name : fileName) {
            String absPath = dir + "\\" + name;
            BufferedReader br = new BufferedReader(new FileReader(absPath));   // 读取到文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(dir1 + name));
            BufferedWriter bw1 = new BufferedWriter(new FileWriter("C:\\Users\\Spring\\Desktop\\comments.sql"));
            String tableName = "";  // 表名
            String pkString = "";  // 主键
            String indexString = "";  // 索引
            List<String> lidx = new ArrayList<>();  // 存放索引的集合
            // 下面做处理
            String text = "";  // 拼接的字符串
            String line;  // 每次读取到的数据
            while ((line = br.readLine()) != null) {

                // 处理 create
                if (line.startsWith("CREATE")) {
                    String[] split = line.split(" ");
                    tableName = "ODS.RE_" + split[2].replaceAll("`", "");
                    line = split[0] + " " + split[1] + " ODS.RE_" + split[2].replaceAll("`", "") + " " + split[4] + "\n";
                } else if (line.contains("COMMENT")) {
                    String[] split = line.replaceAll("`", "").trim().split(" ");
                    String columnName = split[0];
                    String columnType = typeCheck(split[1]);
                    String[] split1 = line.split("COMMENT");
                    line = columnName + " " + columnType + ",\n";

                    String comments1 = "COMMENT ON COLUMN " + tableName + "." + columnName + " IS " + split1[1].replaceAll(",", "") + ";\n";
                    comments = comments + comments1;
                } else if (line.trim().contains("SET FOREIGN_KEY_CHECKS")) {
                    line = "";
                } else if (line.contains("PRIMARY KEY")) { // 处理主键
                    pkString = line.split("`")[1];
                    line = "";
                } else if (line.contains("INDEX `")) {  // 处理索引
                    String[] split = line.split("`");
                    indexString = split[1] + " " + split[3];
                    lidx.add(indexString);
                    line = "";
                } else {
                    line = line + "\n";
                }
                text = text + line;
            }
            bw1.append(comments.toUpperCase());
            bw1.flush();
            if(pkString!=null&&pkString!="")    // 如果有主键,则拼接
            pkString = "ALTER TABLE " + tableName + " ADD CONSTRAINT PK_" + name.split("\\.")[0] + "_" + pkString + " PRIMARY KEY (" + pkString + ");\n";
            text = text + pkString;

            // 拼接索引
            for (String s : lidx) {
                String[] split = s.split(" ");
                String ss = "CREATE INDEX " + split[0] + " ON " + tableName + " (" + split[1] + ");\n";
                text = text + ss;
            }

            System.out.println(text.toUpperCase());
            bw.append(text.toUpperCase());
            bw.flush();
        }


        //readerDemo();
        //writerDemo();
        //copy("java-example/file/poem.txt","java-example/file/output.txt");
    }


    private static String typeCheck(String type) {
        String newType = null;
        if (type.toLowerCase().contains("int")||type.toLowerCase().contains("double")) {
            newType = "number";
        } else if (type.toLowerCase().contains("varchar") || type.toLowerCase().contains("char") || type.toLowerCase().equals("date")) {
            newType = type;
        } else if (type.toLowerCase().contains("datetime") || type.toLowerCase().contains("timestamp")) {
            newType = "date";
        } else if (type.toLowerCase().contains("decimal")) {
            newType = "number";
        }
        return newType;
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
            logger.error("文件状态发生错误", e);
        } finally {
            StartIO.safelyClose(br);
        }
    }

    // 字符输出流
    public static void writerDemo() {
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
            logger.error("文件状态发生错误", e);
        } finally {
            StartIO.safelyClose(bw);
        }
    }

    // 复制文本,或按指定逻辑处理文本
    public static void copy(String source, String desc) {

        List<String> fileName = FileDemo.getFileName(source); // 获取指定目录下的所有文件
        for (String file : fileName) {
            BufferedReader br = null;
            BufferedWriter bw = null;
            try {
                br = new BufferedReader(new FileReader(source + file));
                bw = new BufferedWriter(new FileWriter(desc + file));
                String line = "";                                 // 临时存储读取到的每行文本的变量
                String text = "";                                 // 拼接的文本
                while ((line = br.readLine()) != null) {
                    line = handleFile(line);                     // 对文本内容进行处理
                    text = text + line;                          // 把处理后的文本拼接
                }
                bw.write(text);
                bw.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 对文本内容进行处理
     *
     * @param line 读取到的每行文本
     * @return 处理后的每行文本
     */
    static String handleFile(String line) {
        if (line != null) {
            return line + "\n";
        }
        return null;
    }

}
