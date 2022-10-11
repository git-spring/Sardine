package com.IO;

import com.star.IO_.ReaderWriter;
import com.star.file_.FileDemo;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author Spring
 * @date 2022/6/6 17:04
 * @describe :
 */

public class FileDemoTest {
    FileDemo f = new FileDemo();
    ReaderWriter rw = new ReaderWriter();

    @Test
    public void getFileName() {
        List<String> fileName = f.getFileName("C:\\Users\\Spring\\Desktop\\第二批次\\建表sql");
        Iterator<String> iterator = fileName.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next.split("\\.")[0]);
        }
    }

    @Test
    public void copy() {
        rw.copy("C:\\Users\\Spring\\Desktop\\mysql2oracle\\",
                "C:\\Users\\Spring\\Desktop\\oracle\\");
    }

    @Test
    public void fileSize() {
        f.getFileSize("E:\\04javaProject\\Sardine\\.git\\objects");
    }

    @Test
    public void test(){
        String s = "varchar(1)";
        int start = s.indexOf('(');
        int end = s.indexOf(')');

        String substring = s.substring(s.indexOf('(')+1, s.indexOf(')'));
        System.out.println(Integer.valueOf(substring)*2);
    }
}