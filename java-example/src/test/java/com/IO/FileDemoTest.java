package com.IO;

import com.star.IO_.ReaderWriter;
import com.star.file_.FileDemo;
import com.star.file_.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Spring
 * @date 2022/6/6 17:04
 * @describe :
 */

public class FileDemoTest {
    FileUtil f = new FileUtil();
    ReaderWriter rw = new ReaderWriter();

    @Test
    public void getFileName() throws FileNotFoundException {
        List<String> fileName = f.getFileName("C:\\Users\\Spring\\Desktop");
        Iterator<String> iterator = fileName.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next.split("\\.")[0]);
        }
    }

    @Test
    public void copy() throws FileNotFoundException {
        rw.copy("C:\\Users\\Spring\\Desktop\\mysql2oracle\\",
                "C:\\Users\\Spring\\Desktop\\oracle\\");
    }

    @Test
    public void fileSize() throws FileNotFoundException {
        Map<String, Long> fileSize = f.getFileSize("E:\\04javaProject\\Sardine\\.git\\objects");
        for (String key : fileSize.keySet()) {
            System.out.println(key+" ------- "+  fileSize.get(key));
        }
    }

    @Test
    public void clearFileTest()   {
        f.clearUp(new File("C:\\Users\\Spring\\Desktop\\a"));
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