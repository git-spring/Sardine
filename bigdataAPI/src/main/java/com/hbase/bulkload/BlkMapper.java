package com.hbase.bulkload;

/*
 *  @author:   liudw
 *  @date:  2020-9-28
 */

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *  Mapper 类
 *  数据格式 ：
 *        key1  fm1:col1 value1
 */
public class BlkMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

	@Override
	protected void map(LongWritable key, Text values, Context context) throws IOException, InterruptedException {

		// rowkey family column  value
		String[] split = values.toString().split("\t");
		String rowkey = split[0];
		String family = split[1].split(":")[0];
		String column = split[1].split(":")[1];
		String value = split[2];

		//将RowKey字节化处理并封装在ImmutableBytesWritable对象中
		ImmutableBytesWritable immu = new ImmutableBytesWritable(rowkey.getBytes());
		Put put = new Put(rowkey.getBytes());
		put.addColumn(family.getBytes(), column.getBytes(), value.getBytes());

		context.write(immu, put);
	}
}
