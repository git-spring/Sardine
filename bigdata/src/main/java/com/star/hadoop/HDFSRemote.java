package com.star.hadoop;

/**
 *  @author: liudw
 *  @date: 2021-1-8 14:39
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 *  远程连接hdfs
 */
public class HDFSRemote {

	static FileSystem fs;

	static {

		try {
			Configuration conf = new Configuration();
			// conf.set("fs.defaultFS", "hdfs://10.2.98.128:9000");
			conf.addResource("hdfs-site.xml");
			conf.addResource("core-site.xml");
			// kerberos 认证
			System.setProperty("java.security.krb5.conf", "D:\\krb5.conf");
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab("asset_app", "D:\\asset_app.keytab");
			// 获取hdfs的操作对象，得到一个FileSystem对象
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 查看某个目录下的所有文件
	public static void lsFiles(String pathStr) {

		try {
			Path path = new Path(pathStr);
			FileStatus stats[] = fs.listStatus(path);
			for (int i = 0; i < stats.length; i++) {
				System.out.println(stats[i].getPath().toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 判断文件/目录是否存在
	public static void ifExists(String pathStr) {
		try {
			Path path = new Path(pathStr);
			boolean isExists = fs.exists(path);
			System.out.println(isExists);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
