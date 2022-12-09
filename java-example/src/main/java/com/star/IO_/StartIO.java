package com.star.IO_;

import java.io.*;


// IO 流工具类
public class StartIO {

    // 关闭输入输出流
    public static void safelyClose(Closeable... io) {
        for (Closeable closeable : io) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // copy ,并处理文件内容
    public static void copy(String source, String desc) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(source));
        BufferedWriter out = new BufferedWriter(new FileWriter(desc));
        String line = "";

        while ((line= in.readLine()) != null) {
             line = handleLine(line);
            out.write(line+"\n");
            out.flush();
        }
    }

    public static String handleLine(String str){
        return "## "+str;
    }


}
