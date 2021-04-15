package com.star.table

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.DataTypes
import org.apache.flink.table.api.scala._
import org.apache.flink.table.descriptors.{FileSystem, OldCsv, Schema}


/**
 * @author: liudw
 * @date: 2021-3-1 9:35
 */

// flink table api & sql  -- kafka
object Example2 {
    def main(args: Array[String]): Unit = {

        val env = StreamExecutionEnvironment.getExecutionEnvironment

        val filePath = "flink-scala/in/word.txt"

        val tableEnv = StreamTableEnvironment.create(env)





        env.execute("table api")
    }
}
