package com.star.utility;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

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

}
