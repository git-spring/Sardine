package com.IO;

import com.star.IO.ReaderWriter;
import com.star.file.FileDemo;
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
        List<String> fileName = f.getFileName("C:\\Users\\Spring\\Desktop\\test");
        Iterator<String> iterator = fileName.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void copy() {
        rw.copy("C:\\Users\\Spring\\Desktop\\mysql2oracle\\",
                "C:\\Users\\Spring\\Desktop\\oracle\\");
    }

}