package com.star.utility;

import javax.swing.filechooser.FileSystemView;
import java.io.File;



/**
 * @author Spring
 * @date 2022/9/14 14:30
 */


public class Utils {

    // 获取桌面路径
    public static String getDesktopPath() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();
        return com.getPath();
    }


}