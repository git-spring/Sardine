package com.star.table

import com.star.stream.Sensor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.table.api.Table
import org.apache.flink.table.api.scala._
import org.apache.flink.table.descriptors.ConnectorDescriptor


/**
  * @author: liudw
  * @date: 2021-3-1 9:35
  */

// flink table 操作
object Example {
    def main(args: Array[String]): Unit = {
        val env = StreamExecutionEnvironment.getExecutionEnvironment

        val inputpath = "flink-scala/in/word.txt"
        val inputStream = env.readTextFile(inputpath)

        val dataStream = inputStream.map(data => {
            val arr = data.split(",")
            Sensor(arr(0), arr(1).toLong, arr(2).toDouble)
        })

        // 创建表执行环境
        val tableEnv = StreamTableEnvironment.create(env)

        // 基于流创建一个表
        val dataTable: Table = tableEnv.fromDataStream(dataStream)


        // 调用table api
        val resultTable = dataTable
                .select("id,temperature")
                .filter("id=='sensor_10'")

        // 直接用sql实现
        tableEnv.createTemporaryView("dataTable", dataTable)
        val sql = "select id,temperature from dataTable where id ='sensor_10'"
        val sqlResultTable = tableEnv.sqlQuery(sql)

        // 转换成 flink dataStream 输出
        resultTable.toAppendStream[(String, Double)].print("result")
        sqlResultTable.toAppendStream[(String, Double)].print("sql result")

        env.execute("table api")
    }
}
