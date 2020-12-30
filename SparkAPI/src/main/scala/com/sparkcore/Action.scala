package com.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: liudw
 * @date: 2020-12-25 15:33
 */

// Spark 中的行动算子
object Action {

    def reduceDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 10)
        val reduceRdd = listRdd.reduce((x, y) => {
            x + y
        })
        println(reduceRdd)
    }

    /**
     * collect
     * 把数据收集到driver 中，并以数组的形式返回所有元素
     */
    def collectDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 10)
        val collectRdd: Array[Int] = listRdd.collect()

        collectRdd.foreach(
            array => {
                println(array)
            }
        )
    }

    /**
     * count
     * 返回RDD中元素的个数
     */
    def countDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 10)
        val count: Long = listRdd.count()

        println(count)
    }

    /**
     * countByKey
     * 按key统计出现的次数
     */
    def countByKeyDemo(): Unit ={
        val sc = createSC()
        val listRdd = sc.makeRDD(List(("a", 1), ("b", 2), ("b", 3), ("a", 4), ("c", 1), ("b", 2), ("c", 3), ("d", 4)))
        val res = listRdd.countByKey()
        println(res)

    }

    /**
     * foreach
     * 与 rdd.collect().foreach()  不是同一个函数
     * 两个函数执行的位置不一样
     * rdd.foreach()   是在Executor中执行
     * rdd.collect().foreach() 是在Driver中执行     (rdd.collect() 返回的是一个数组)
     */
    def foreachDemo(): Unit ={

    }

    /**
     * first
     * 取出RDD中的第一个元素
     */
    def firstDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 10)
        val first: Int = listRdd.first()

        println(first)
    }


    /**
     * take
     * 取出RDD中的前N个元素
     */
    def takeDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 10)
        val eles: Array[Int] = listRdd.take(3)
        println(eles.mkString(","))
    }

    /**
     * takeOrdered
     * 取出排序后的前N个元素
     */
    def takeOrderedDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(1, 5, 8, 3, 4, 7))
        val idx = listRdd.takeOrdered(2)
        println(idx.mkString(","))

    }

    /**
     * aggregate
     *
     * aggregateByKey的初始值只在分区内第一次生成key时有效
     * aggregate不仅分区内有效,分区间也会有效
     *
     */
    def aggregateDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(1, 5, 8, 3, 4, 7))
        // val aggregateRdd = listRdd.aggregate(0)(_+_,_+_)
        val aggregateRdd = listRdd.aggregate(10)(_ + _, _ + _)
        println(aggregateRdd)
        println(listRdd.partitions.size)

    }

    /**
     * fold
     * fold是aggregate的简化操作
     * 分区内和分区间的运算规则一样的时候，可以直接使用fold
     */
    def foldDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(1, 5, 8, 3, 4, 7))
        val foldRdd = listRdd.fold(10)(_ + _)
        println(foldRdd)
    }

    /**
     * saveAsTextFile
     * 把结果存储到文件中
     */
    def saveAsTextFileDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(1, 5, 8, 3, 4, 7))
        listRdd.saveAsTextFile("SparkAPI/out")
    }

    /**
     * saveAsObjectFile
     * 把数据以字节的形式保存到文件中
     */
    def saveAsObjectFileDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(1, 5, 8, 3, 4, 7))
        listRdd.saveAsObjectFile("SparkAPI/out")
    }



    def main(args: Array[String]): Unit = {
        countByKeyDemo()
    }


    def createSC() = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("Action")
        val sc = new SparkContext(conf)
        sc
    }
}
