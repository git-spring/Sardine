package com.hbase;

/*
 *  @author:   liudw
 *  @date:  2020-11-3
 */

import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 *  Hbase scan 的过滤器
 */
public class ScanFilter {

	public static List<FilterList> filterList() {

		ArrayList<FilterList> filterList = new ArrayList<>();

		/*
		 * 过滤器, 根据需要添加
		 */
		// TODO: 2020-11-3 添加对应的shell命令
		// 基于列簇的过滤器  {COLUMN=>'cf'}
		FamilyFilter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("cf")));
		// 基于列的过滤器  结合FamilyFilter  {COLUMN=>'cf:column'}
		QualifierFilter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("name")));
		// 基于列名前缀过滤
		ColumnPrefixFilter columnPrefixFilter = new ColumnPrefixFilter(Bytes.toBytes("000"));
		// 基于多个列名前缀过滤 MultipleColumnPrefixFilter   000 || 001 开头
		byte[][] bytes = new byte[][]{Bytes.toBytes("000"), Bytes.toBytes("001")};
		MultipleColumnPrefixFilter multipleColumnPrefixFilter = new MultipleColumnPrefixFilter(bytes);

		// 基于行键的过滤器RowkeyFilter
		Filter filter = null;
		String rowkey = "000000001";
		filter = new PrefixFilter(Bytes.toBytes(rowkey.trim()));   // 与rowkey相等的会被查询到
		filter = new RowFilter(CompareFilter.CompareOp.NOT_EQUAL, new BinaryComparator(Bytes.toBytes(rowkey))); // 不相等
		filter = new RowFilter(CompareFilter.CompareOp.LESS, new BinaryComparator(Bytes.toBytes(rowkey)));  // 大于

		// 行键包含过滤器
		filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(rowkey));

		return filterList;
	}

}
