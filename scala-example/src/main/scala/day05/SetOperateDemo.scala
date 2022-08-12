package day05

import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer


/**
  * @Date 2022/8/10 11:09
  * @author Spring
  * @Describe : 
  */

// scala 集合操作
// scala 中常见的数据处理方法
// 如果集合中有多个元素,传入的高阶函数也会执行多次
object SetOperateDemo {
    def main(args: Array[String]): Unit = {
        zipDemo()


    }


    // scala map 映射
    def mapDemo() = {
        val list = List(1, 2, 3, 4)
        val ints: List[Int] = list.map(_ * 2)
        println(ints)
    }

    // scala flatMap 扁平化
    def flatMapDemo() = {

        def upper(str: String): String = {
            str.toUpperCase
        }

        val list = List("a", "b","c","defg")
        val chars: List[Char] = list.flatMap(upper)
        println(chars)
    }

    // scala filter  过滤
    def filterDemo() = {
        val list = List(1, 2, 3, 4)
        list.filter(_ % 2 == 0)
    }

    // scala reduce 化简
    def reduceDemo() = {
        val list = List(1, 2, 3, 4, 5)
        // reduce 底层实现是 reduceLeft
        val res1: Int = list.reduce(_ + _)   // ((((1+2)+3)+4)+5) = 15
        val res2: Int = list.reduce(_ - _)   // ((((1-2)-3)-4)-5) = -13
        val res3: Int = list.reduceRight(_ - _)   // (1-(2-(3-(4-5)))) = 3
        println(res1)
        println(res2)
        println(res3)
    }

    // scala fold 折叠
    // scala 折叠与reduce相似,但是可以传一个初始值,如果初始值为0,则与reduce一样
    def foldDemo() = {
        val list = List(1, 2, 3, 4, 5)
        val res1: Int = list.fold(0)(_ - _)   // ((((((0-1)-2)-3)-4)-5) = -15
        val res2: Int = list.fold(10)(minus)  // ((((((10-1)-2)-3)-4)-5) = -5
        val res3: Int = list.foldRight(10)(_ - _)  // (1-(2-(3-(4-(5-10))))) = -7
        println(res1)
        println(res2)
        println(res3)

        // foldLeft和foldRight 缩写方法分别是：/:和:\
        // 这种写法等价于 val res1 = list.foldLeft(4)(_ - _)
        val res4 = (4 /: list) (_ - _)
        // 这种写法等价于 val res1 = list.foldRight(5)(_ - _)
        val res5 = (list :\ 5) (_ - _)
    }

    // scala scan 扫描
    // 对某个集合的所有元素做fold操作，但是会把产生的所有中间结果放置于一个集合中保存
    def scanDemo()={
        val res1 = (1 to 5).scanLeft(5)(minus)
        // res1 = Vector(5, 4, 2, -1, -5, -10)
        // 5
        // (5-1) = 4
        // ((5-1)-2) = 2
        // (((5-1)-2)-3) = -1
        // ((((5-1)-2)-3)-4) = -5
        // (((((5-1)-2)-3)-4)-5) = -10
        println("res1 = " + res1)

        val res2: immutable.IndexedSeq[Int] = (1 to 4).scanRight(5)(minus)
        // res2 = Vector(3, -2, 4, -1, 5)
        // 从右往左
        // 5
        // (4-5) = -1
        // (3-(4-5)) = 4
        // (2-(3-(4-5))) = -2
        // (1-(2-(3-(4-5)))) = 3
        println("res2 = " + res2)

    }

    // scala zip 拉链
    // 拉链操作后每个元素都是一个对偶元组
    // 如果两个集合的元素个数不一致,则以个数少的为准
    def zipDemo()={
        val list1 = List(1, 2, 3)
        val list2 = List(4, 5, 6, 8)
        val res = list1.zip(list2)
        println("res = " + res)

        println("-------------遍历---------------")
        for (item <- res) {
            println(item._1 + " -- " + item._2)
        }

        val arr1 = Array("a","b","c")
        val arr2 = Array(1,2,3)
        val res2: Array[(String, Int)] = arr1.zip(arr2)
    }


    def minus(v1: Int, v2: Int): Int = {
        v1 - v2
    }




    // 练习 1
    // fold 练习
    // 将字符串 "ABCDE" 拆成单个字符放入 ArrayBuffer 中
    def foldDemo02() = {
        // 这里内嵌套的方法需要放在前面,不然执行报错
        def putChar(arr: ArrayBuffer[Char], c: Char) = {
            arr.append(c)
            arr
        }

        val text = "ABCDE"
        import scala.collection.mutable.ArrayBuffer
        var ab = new ArrayBuffer[Char]()
        val chars: ArrayBuffer[Char] = text.foldLeft(ab)(putChar)
        println(chars)
    }

    // 练习 2
    // 统计字符串中各个字符出现的次数
    def foldDemo03() = {
        def countChar(map: Map[Char, Int], c: Char) = {
            map + (c -> (map.getOrElse(c, 0) + 1))
        }

        val text = "AABBBCCCDDESSSS"
        var map = Map[Char, Int]()
        val charTimes: Map[Char, Int] = text.foldLeft(map)(countChar)
        println(charTimes)
    }
}
