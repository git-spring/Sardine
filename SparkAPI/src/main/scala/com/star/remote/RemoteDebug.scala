package com.star.remote

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: liudw
 * @date: 2021-1-8 13:31
 */

// 使用idea远程提交任务到spark集群上
object RemoteDebug {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
            .setAppName("WordCount")
            // 设置yarn-client模式提交
            .setMaster("yarn")
            // 设置resourcemanager的ip
            .set("yarn.resourcemanager.hostname", "master")


        // TODO: 使用idea远程提交任务到spark集群上

    }
}
