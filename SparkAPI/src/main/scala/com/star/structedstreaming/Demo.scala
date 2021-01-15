package com.star.structedstreaming


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime

/**
 * @author: liudw
 * @date: 2021-1-14 13:35
 */
object Demo {
    def main(args: Array[String]): Unit = {

        val spark: SparkSession = SparkSession.builder
            .master("local[1]")
            .appName("demo")
            .getOrCreate()

        import spark.implicits._

        val lines = spark.readStream
            .format("socket")
            .option("host", "10.2.98.132")
            .option("port", 9999)
            .load()

//        val words = lines.as[String].flatMap(_.split(" "))
//
//        val wordCounts = words.groupBy("value").count()

        val query = lines.writeStream
            .outputMode("update")
            .format("console")
            .start()

        query.awaitTermination()

        
    }

}
