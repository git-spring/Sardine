package com.star.structedstreaming


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}


/**
 * @author: liudw
 * @date: 2021-1-15 10:36
 */
object FileSource {
    def main(args: Array[String]): Unit = {
        val spark: SparkSession = SparkSession.builder
            .master("local[*]")
            .appName("filesource")
            .getOrCreate()

        // 指定schema
        val usersSchema = StructType(
            List(
                StructField("id", IntegerType),
                StructField("name", StringType),
                StructField("age", IntegerType)
            )
        )

        import spark.implicits._

        val line = spark.readStream
            .format("csv")
            .schema(usersSchema)
            .load("SparkAPI/data/")

        val query = line.writeStream
            .format("console")
            .outputMode("update")
            .trigger(Trigger.ProcessingTime(1000))
            .start()

        query.awaitTermination()


    }
}
