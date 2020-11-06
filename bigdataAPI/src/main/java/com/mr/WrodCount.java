package com.mr;

/**
 *  @author: liudw
 *  @date: 2020-11-5
 */


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 *  mapreduce 之 wordcount
 *
 */
public class WrodCount extends Mapper<LongWritable, Text, Text, IntWritable> {


	public class CountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		/**
		 * map方法,每传入一行数据,就被调用一次
		 *
		 * @param key       这一行的起始点在文件中的偏移量
		 * @param value     传入的数据
		 * @param context   上下文对象
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			String[] str = value.toString().split(" ");
			for (String word : str) {
				context.write(new Text(word), new IntWritable(1));
			}
		}
	}


	public class CountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		int count = 0;

		/**
		 * 架每传递进来一个k-v 对，reduce方法被调用一次
		 * @param key
		 * @param values
		 * @param context
		 */
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			for (IntWritable value : values) {
				count += value.get();
			}
			context.write(key, new IntWritable(count));
		}
	}


	public static void main(String[] args) {
		Job job = null;
		try {
			job = Job.getInstance();
			job.setJobName("wordCount");
			Path in = new Path("hdfs://***:9000/user/hadoop/input/buyer_favorite1.txt");
			Path out = new Path("hdfs://***:9000/user/hadoop/output/wordCount");
			FileInputFormat.addInputPath(job, in);
			FileOutputFormat.setOutputPath(job, out);

			job.setJarByClass(WrodCount.class);// 设置处理该作业的类

			job.setMapperClass(CountMapper.class);// 设置mapper 类
			job.setReducerClass(CountReducer.class);// 设置reducer 类

			job.setOutputKeyClass(Text.class);// 设置输出结果key的类型
			job.setOutputValueClass(IntWritable.class);// 设置输出结果value的类型

			// 执行作业
			int i = job.waitForCompletion(true) ? 0 : 127;
			System.exit(i);
		} catch (IOException | InterruptedException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
