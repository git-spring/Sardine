package com.star.table

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.{DataTypes, Table}
import org.apache.flink.table.api.scala._
import org.apache.flink.table.descriptors.{FileSystem, OldCsv, Schema}


/**
 * @author: liudw
 * @date: 2021-3-1 9:35
 */

// flink table 操作
object Example1 {
    def main(args: Array[String]): Unit = {
        val env = StreamExecutionEnvironment.getExecutionEnvironment

        val filePath = "flink-scala/in/word.txt"

        val tableEnv = StreamTableEnvironment.create(env)
        tableEnv.connect(new FileSystem().path(filePath))
            .withFormat(new OldCsv())
            .withSchema(new Schema()
                .field("id", DataTypes.STRING())
                .field("timestamp", DataTypes.BIGINT())
                .field("temperature", DataTypes.DOUBLE())
            )
                .createTemporaryTable("inputTable")

        val inputTable = tableEnv.from("inputTable")
        inputTable.toAppendStream[(String,Long,Double)].print()


        env.execute("table api")
    }
}
