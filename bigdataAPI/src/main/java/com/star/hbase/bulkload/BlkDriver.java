package com.star.hbase.bulkload;

/*
 *  @author:   liudw
 *  @date:  2020-9-28
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FsShell;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Hbase bulkload
 *  通过直接在HDFS上生产HFILE,减少I/O,提升性能,适合大批量写Hbase,或者第一次写入
 *
 */
public class BlkDriver {
	public static void main(String[] args) {
		// TODO: 2020-11-5 待测试
		// 以第一个参数作为Hive表名
		String hiveTableName = args[0];
		// 以第二个参数作为HBase表名
		String hbaseTableName = args[1];
		// 第三个参数为输入路径
		String inputPath = args[2];
		// 第四个参数为输出路径
		String outputPath = args[3];
		Configuration conf = HBaseConfiguration.create();
		Connection conn = null;

		try {
			conn = ConnectionFactory.createConnection(conf);
			Admin admin = conn.getAdmin();
			Table table = conn.getTable(TableName.valueOf(hbaseTableName));

			// 第一个Job就是普通MR，输出到指定的目录
			Job job = Job.getInstance(conf, "BlkDriver-" + hiveTableName);
			job.setJarByClass(BlkDriver.class);
			// 设置mapper 类
			job.setMapperClass(BlkMapper.class);

			job.setMapOutputKeyClass(ImmutableBytesWritable.class);
			job.setMapOutputValueClass(Put.class);

			// Text类型使用
			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(HFileOutputFormat2.class);

			// 设置是否开启推测执行
			job.setSpeculativeExecution(false);
			// 设置是否开启Reduce阶段
			job.setReduceSpeculativeExecution(false);
			// 设置Map输入与输出路径
			FileInputFormat.setInputPaths(job, new Path(inputPath));
			FileOutputFormat.setOutputPath(job, new Path(outputPath));

			// 配置MapReduce作业，以执行增量加载到给定表中,这里其实应该使用这个方法，里面传入第三个参数，
			// 获取Region定位器，实际上就是定位一个Region，然后把数据加载到指定的表中
			RegionLocator regionLocator = conn.getRegionLocator(TableName.valueOf(hbaseTableName));
			HFileOutputFormat2.configureIncrementalLoad(job, table, regionLocator);


			// 需要修改outputPath文件的权限才能正常写入。
			if (job.waitForCompletion(true)) {
				FsShell shell = new FsShell(conf);
				try {
					shell.run(new String[]{"-chmod", "-R", "777", outputPath});
				} catch (Exception e) {
					throw new IOException(e);
				}
				// 载入到hbase表
				LoadIncrementalHFiles loader = new LoadIncrementalHFiles(conf);
				loader.doBulkLoad(new Path(outputPath),admin , table, regionLocator);
			} else {
				System.exit(1);
			}
		} catch (IOException | InterruptedException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
