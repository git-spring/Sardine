package com.star.hive.udf;


import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;
import java.util.List;

/**
 *  自定义排序
 *     使用函数对字段内的数据进行序号分配，再按序号进行排序，可以在hive中实现自定义排序
 *
 */

@Description(name = "order_numb",
        value = "_FUNC_(field[,value1,value2,...]) - 根据传入的value顺序返回序号 ",
        extended = "Example:\n"
                + "  > SELECT _FUNC_('a','b','a','c') FROM src LIMIT 1;\n"
                + "  2\n"
                + "  > author : xingkong" )
public class UDFOrderNumb extends UDF {
    private final IntWritable result = new IntWritable();
    private List<String> list = new ArrayList<>();

    // 如果只传入了字段，没有指定排序规则，则直接返回 1
    public IntWritable evaluate(Text s) {
        if (s == null) {
            return null;
        }
        result.set(1);
        return result;
    }

    // 按传入值的顺序返回一个序号，用于后面的排序
    public IntWritable evaluate(Text s,Text ...values ) {
        if (s == null) {
            return null;
        }
        // 把传进来的参数放到list中
        for (Text value: values ) {
            list.add(value.toString());
        }

        // 如果字段的值包含需要自定义排序的值，则把值的下标+1返回
        if (list.contains(s.toString())){
            result.set(list.indexOf(s.toString())+1);
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        UDFOrderNumb a = new UDFOrderNumb();
        // 后面的值和第一个a 比较，返回第一次出现的序号
        IntWritable evaluate = a.evaluate(new Text("a"), new Text("b"), new Text("a"), new Text("c"));
        System.out.println(evaluate);  // 2
    }

}
