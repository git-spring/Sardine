package com.sparkcore

import org.apache.spark.{Partitioner, SparkConf, SparkContext}


/**
 * @author: liudw
 * @date: 2020-12-23 15:31
 */

// Spark 的转换算子
object Transform {


    def createSC() = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("Transform")
        val sc = new SparkContext(conf)
        sc
    }

    /**
     * map算子  map(func)
     * 把RDD中的每一个元素都作用于func函数上
     */
    def mapDemo(): Unit = {
        val sc = createSC()
        val dataRdd = sc.makeRDD(Range(1, 10))
        val res = dataRdd.map(_ * 2)
        res.foreach(println)
    }

    /**
     * mapPartitions
     * 与map类似,但是是运行在RDD的每个分区上
     *
     */
    def mapPartitionsDemo(): Unit = {
        val sc = createSC()
        val dataRdd = sc.makeRDD(Range(1, 10))
        val partitonsRdd = dataRdd.mapPartitions(eles => {
            eles.map(_ * 2)
        })
        partitonsRdd.foreach(println)
    }

    /**
     * mapPartitionsWithIndex
     * 与mapPartitions类似,但是多提供了一个integer型的参数表示分区号
     */
    def mapPartitionsWithIndexDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Range(1, 10))
        val tupleRdd = listRdd.mapPartitionsWithIndex {
            case (num, content) => {
                content.map((_, " 分区号: " + num))
            }
        }
        tupleRdd.collect().foreach(println)
    }


    /**
     * flatMap
     * 将原RDD中的数据扁平化处理
     */
    def flatMapDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(List(1, 2), List(3, 4), List(5, 6)))
        val tupleRdd = listRdd.flatMap(it => it)
        tupleRdd.collect().foreach(println)
    }

    /**
     * glom
     * 把每一个分区中的元素转换成一个数组
     */
    def glomDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.parallelize(1 to 20, 4)
        val glomRdd = listRdd.glom()
        glomRdd.collect().foreach(
            array => {
                println(array.mkString(","))
            })
    }

    /**
     * groupBy
     * groupBy算子接收一个函数,这个函数返回的值作为key,然后通过这个key来对里面的元素进行分组
     */
    def groupByDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(List(("a", 1), ("b", 2), ("b", 3), ("a", 4), ("c", 1), ("b", 2), ("c", 3), ("d", 4)))
        val groupByRdd = listRdd.groupBy(i => i._2)
        groupByRdd.collect().foreach(println)

    }

    /**
     * filter
     * 按照指定规则进行过滤
     */
    def filterDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8))
        val filterRdd = listRdd.filter(x => x % 2 == 0)
        filterRdd.collect().foreach(println)
    }

    /**
     * sample
     *
     */
    def sampleDemo(): Unit = {
        // TODO: sample transform
    }

    /**
     * distinct
     * 去除重复的数据
     */
    def distinctDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(List(1, 2, 3, 4, 2, 3, 3, 2, 7, 8, 9))
        val distinctRdd = listRdd.distinct()

        // distinct 算子的原理是执行了以下操作
        // val distinctRdd = listRdd.map(x => (x, null)).reduceByKey((x, y) => x, numPartitions).map(_._1)
        // val distinctRdd = listRdd.map((_,1)).reduceByKey(_+_).map(_._1)
        distinctRdd.collect().foreach(println)
    }

    /**
     * coalesce
     * 减少分区数量,实质是进行分区的合并, 不可以增加分区数量
     * 如果指定shuffle = true ,会对数据进行shuffle, 这个时候可以增加分区数量
     */
    def coalesceDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 20, 4)
        println("原分区数量: " + listRdd.partitions.size)
        // 缩减分区,会把分区的数据简单合并
        val coalesceRdd = listRdd.coalesce(2)
        // 指定shuffle = true ,数据会有shuffle, 可以增加分区
        // val coalesceRdd = listRdd.coalesce(5,shuffle = true)
        println("coalesce后分区数量: " + coalesceRdd.partitions.size)
    }

    /**
     * repartition
     * repartition 用于改变分区的数量,可以增加或减少分区
     * repartition 调用的就是coalesce, 默认是shuffle=true,会对数据进行重新分区
     */
    def repartitionDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(1 to 20, 4)
        println("原分区数量: " + listRdd.partitions.size)
        // 缩减分区,会把分区的数据简单合并
        val repartitionRdd = listRdd.repartition(2)
        // 指定shuffle = true ,数据会有shuffle, 可以增加分区
        // val coalesceRdd = listRdd.coalesce(5,shuffle = true)
        println("repartition后分区数量: " + repartitionRdd.partitions.size)
    }

    /**
     * sortBy
     * 按给定的函数进行排序
     */
    def sortByDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(List(1, 4, 6, 8, 23, 2, 5, 78))
        val sortByRdd = listRdd.sortBy(x => x) // x => x  匿名函数
        sortByRdd.collect().foreach(println)
    }

    /**
     * intersection
     * 对两个RDD求交集,并返回一个新的RDD
     */
    def intersectionDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(1 to 10)
        val rdd2 = sc.makeRDD(5 to 12)
        val intersectionRdd = rdd1.intersection(rdd2)
        intersectionRdd.collect().foreach(println)
    }

    /**
     * union
     * 对两个RDD求并集,并返回一个新的RDD
     */
    def unionDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(1 to 10)
        val rdd2 = sc.makeRDD(5 to 12)
        val unionRdd = rdd1.union(rdd2)
        unionRdd.collect().foreach(println)
    }

    /**
     * subtract
     * 对两个RDD求差集,并返回一个新的RDD
     */
    def subtractDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(1 to 10)
        val rdd2 = sc.makeRDD(5 to 12)
        val subtractRdd = rdd1.subtract(rdd2)
        subtractRdd.collect().foreach(println)
    }

    /**
     * cartesian
     * 对两个RDD求笛卡尔积,并返回一个新的RDD
     */
    def cartesianDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(1 to 4)
        val rdd2 = sc.makeRDD(5 to 9)
        val cartesianRdd = rdd1.cartesian(rdd2)
        cartesianRdd.collect().foreach(println)
    }

    /**
     * zip
     * 把两个RDD中的元素组合成一个k-v对,两个RDD 中的分区数量、元素数量要一样
     */
    def zipDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(List(1, 2, 3), 3)
        val rdd2 = sc.makeRDD(List("a", "b", "c"), 3)
        val zipRdd = rdd1.zip(rdd2)
        zipRdd.collect().foreach(println)
    }

    /**
     * partitionBy
     * 根据分区器对数据进行分区
     * 默认的分区器是HashPartitioner
     */
    def partitionByDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(List((1, "a"), (2, "b"), (3, "c"), (4, "d")))
        // 使用自定义分区器分区
        val partitionByRdd = rdd1.partitionBy(new MyPartitioner(4))
        partitionByRdd.glom().collect().foreach(
            array => {
                println(array.mkString(","))
            })
    }

    /**
     * groupByKey
     * 对具有相同key的value进行分组,只能对k-v对进行操作
     */
    def groupByKeyDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(List(("a", 1), ("b", 2), ("b", 3), ("a", 4), ("c", 1), ("b", 2), ("c", 3), ("d", 4)))
        val groupByRdd = listRdd.groupByKey() // 把相同key的value放到一起
        groupByRdd.collect().foreach(println)
        /*
        结果:
            (a,CompactBuffer(1, 4))
            (b,CompactBuffer(2, 3, 2))
            (c,CompactBuffer(1, 3))
            (d,CompactBuffer(4))
         */
    }

    /**
     * reduceByKey和groupByKey的区别
     *      1. groupByKey默认没有聚合函数,得到的返回值类型是RDD[ k,Iterable[V]]
     *      2. reduceByKey 必须传聚合函数 得到的返回值类型 RDD[(K,聚合后的V)]
     *      3. groupByKey().map() = reduceByKey
     *
     * reduceByKey 会进行预聚合.然后再进行网络传输,性能较好
     * groupByKey 不会进行预聚合
     */

    /**
     * reduceByKey
     * 使用 函数合并具有相同键的值
     */
    def reduceByKeyDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(List(("a", 1), ("b", 2), ("b", 3), ("a", 4), ("c", 1), ("b", 2), ("c", 3), ("d", 4)))
        val reduceByKeyRdd = listRdd.reduceByKey(_ + _) // _+_ 匿名函数 把具有相同key的value进行相加
        reduceByKeyRdd.collect().foreach(println)
        /*
        结果:
            (a,5)
            (b,7)
            (c,4)
            (d,4)
         */
    }

    /**
     * 需求：根据key取出各分区内的value最大值，分区间求和
     *
     * aggregateByKey
     * 根据key对value进行运算,分区内和分区间可以指定不同的运算规则,
     * 可以给定一个初始值,初始值只在第一次运算的时候生效
     */
    def aggregateByKeyDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)

        // 查看一下各分区的数量

        val glomRdd = listRdd.glom()
        print(glomRdd.collect().foreach {
            array =>
                println(array.mkString(","))
        })

        // 分区内相同key的value取最大值,分区间相同key的value相加
        // 第一个函数分区内进行的操作,第二个匿名函数分区间进行的操作
        // 分区内第一次进行操作会和初始值进行比较
        val aggregateByKeyRdd = listRdd.aggregateByKey(7)(math.max(_, _), _ + _)
        aggregateByKeyRdd.collect().foreach(println)
        /*
        分区数据:
            (a,3),(a,2),(c,4)
            (b,3),(c,6),(c,8)
        aggregateByKey之后
            (a,3),(a,2),(c,4)   分区内(a,7),(c,7)
            (b,3),(c,6),(c,8)   分区内(b,7),(c,8)
        然后分区间相加 (a,7),(b,7),(c,15)
        结果:
            (b,7)
            (a,7)
            (c,15)
         */
    }

    /**
     * foldByKey
     * foldByKey 是对aggregateByKey的一种简化
     * aggregateByKey 分区内和分区间可以有不同的运算规则
     * foldByKey 分区内和分区间的运算规则是一样的,只需要传一个函数就可以了
     *
     * 可以给定一个初始值,初始值只在第一次运算的时候生效
     */
    def foldByKeyDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
        val foldByKeyRdd = listRdd.foldByKey(1)(_ + _)
        foldByKeyRdd.collect() foreach (println)
    }

    def combineByKeyDemo(): Unit = {
        // TODO: combineByKey
    }

    /**
     * sortByKey
     * 按key进行排序
     * 与value的值无关
     */
    def sortByKeyDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
        //val sortByKeyRdd = listRdd.sortByKey()    // 正序
        val sortByKeyRdd = listRdd.sortByKey(false) // 反序

        sortByKeyRdd.collect().foreach(println)
    }

    /**
     * mapValue
     * 对k-v对的每一个value做处理
     */
    def mapValueDemo(): Unit = {
        val sc = createSC()
        val listRdd = sc.makeRDD(Array((1, "a"), (1, "d"), (2, "b"), (3, "c")))
        val mapValueRdd = listRdd.mapValues(_ + "b")
        mapValueRdd.collect().foreach(println)

    }

    /**
     * join
     * 把两个RDD中相同key进行关联,相同key的value会生成元组
     *
     */
    def joinDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(Array((1, "a"), (1, "d"), (2, "b"), (3, "c"), (4, "d")))
        val rdd2 = sc.makeRDD(Array((1, 10), (1, 20), (2, 30), (3, 40)))
        val joinRdd = rdd1.join(rdd2)

        joinRdd.collect().foreach(println)
    }

    def cogroupDemo(): Unit = {
        val sc = createSC()
        val rdd1 = sc.makeRDD(Array((1, "a"), (1, "d"), (2, "b"), (3, "c"), (4, "d")))
        val rdd2 = sc.makeRDD(Array((1, 10), (1, 20), (2, 30), (3, 40)))
        val cogroupRdd = rdd1.cogroup(rdd2)

        cogroupRdd.collect().foreach(println)
    }


    def main(args: Array[String]): Unit = {
        groupByKeyDemo()
    }
}


// 分区器
class MyPartitioner(partitions: Int) extends Partitioner {
    override def numPartitions: Int = {
        partitions
    }

    override def getPartition(key: Any): Int = {
        1
    }
}
