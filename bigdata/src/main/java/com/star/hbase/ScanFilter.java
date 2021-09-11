package com.star.hbase;

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
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Hbase scan 的过滤器
 */
public class ScanFilter {

    public static FilterList filterList() {

        /*
         * 过滤器, 根据需要添加
         */
        // TODO: java api 待测试
        // 基于列簇的过滤器
        FamilyFilter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("07")));
        // 基于列的过滤器  结合FamilyFilter
        QualifierFilter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("053")));
        // 基于列名前缀过滤
        ColumnPrefixFilter columnPrefixFilter = new ColumnPrefixFilter(Bytes.toBytes("053"));
        // 基于多个列名前缀过滤 MultipleColumnPrefixFilter   053 || 041 开头
        byte[][] bytes = new byte[][]{Bytes.toBytes("053"), Bytes.toBytes("041")};
        MultipleColumnPrefixFilter multipleColumnPrefixFilter = new MultipleColumnPrefixFilter(bytes);

        // 基于行键的过滤器RowkeyFilter
        Filter filter = null;
        String rowkey = "00";
        filter = new PrefixFilter(Bytes.toBytes(rowkey));   // 查询前缀为指定字符串
        filter = new RowFilter(CompareFilter.CompareOp.GREATER, new BinaryComparator(Bytes.toBytes(rowkey))); // 大于
        filter = new RowFilter(CompareFilter.CompareOp.LESS, new BinaryComparator(Bytes.toBytes(rowkey)));    // 小于

        // 行键包含过滤器 (包含此rowkey的数据)
        filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(rowkey));

        // 值过滤器
        ValueFilter valueFilter = null;
        valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(rowkey));
        valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(rowkey)));

        /*
         *	FilterList 代表一个过滤器列表，可以添加多个过滤器进行查询，多个过滤器之间的关系有
         *  与关系（符合所有）: FilterList.Operator.MUST_PASS_ALL
         *  或关系（符合任一）: FilterList.Operator.MUST_PASS_ONE
         */
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        // 添加 过滤器到 FilterList 中
        filterList.addFilter(familyFilter);
        filterList.addFilter(qualifierFilter);
        return filterList;
    }


}
