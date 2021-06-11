package com.star.sparkcore

import org.apache.spark.Partitioner

/**
 * @author: liudw
 * @date: 2021-6-9 10:21
 */

// 自定义分区器
class SelfDefinePartitioner(partitions: Int) extends Partitioner {
    // 分区数量
    override def numPartitions = partitions

    // 分区规则
    override def getPartition(key: Any) = {

        val part = key.hashCode() % numPartitions

        part match {
            case x => x
        }
    }
}
