package day06

import scala.collection.JavaConverters._

/**
  * @Date 2022/8/12 11:19
  * @author Spring
  *
  */




/**
  *  一个集合 val list = List(1,2,3,4,"abc")
  *  需要将其中的数字+1 ,并返回给你个新集合 , 忽略其中的字符串
  *  新集合  List(2,3,4,5)
  *
  *  可以使用 filter + map 的方法解决,但是Scala提供了偏函数,可以很好的解决这个问题
  */
// scala 偏函数
object PartialFunctionDemo {
    def main(args: Array[String]): Unit = {
        val list = List(1,2,3,4,"abc")
        // 使用偏函数时,不能使用map,应该使用collect
        println(list.collect(partial))

        val ints: List[Int] = list.collect(partial1)
        print("list3 = " + list3)
    }

    /**
      *  new PartialFunction[Any, Int] 定义一个偏函数, Any,Int是自己根据需要定义的类型, 第一个为集合的类型,也就是形参,第二个为返回的类型
      *  isDefinedAt 每次传入一个参数调用一次,如果返回为true,则会去调用apply方法,返回为false,则不做处理
      *  apply       如果 isDefinedAt 的返回为true,则会调用一次
      *
      */

    // 偏函数的方式解决
    val partial = new PartialFunction[Any, Int] {
        override def isDefinedAt(x: Any): Boolean = {
            println("x = " + x)
            x.isInstanceOf[Int]
        }

        override def apply(v1: Any): Int = {
            println("v1 = " + v1)
            v1.asInstanceOf[Int] + 1
        }
    }


    // 偏函数简写 1
    def partial1:PartialFunction[Any,Int]={
        case i:Int =>i+1
        case x:Double =>(x*2).toInt
    }

    // 偏函数简写 2
    val list3 = List(1,2,3,4.5f,"ab").collect{
        case i:Int =>i+1
        case x:Float =>(x*2).toInt
    }



    // filter + map 的方式解决
    def handleList() = {
        val list = List(1, 2, 3, 4, "abc")

        // 过滤字符串,只需要数字
        def strFilter(v: Any): Boolean = {
            v.isInstanceOf[Int]
        }
        // 数字 + 1
        def numMap(num: Int): Int = {
            num + 1
        }
        // 把Any转车Int, 不然不能用 map(numMap) 类型不对
        def typeTransform(v :Any):Int={
            v.asInstanceOf[Int]
        }
        val ints: List[Int] = list.filter(strFilter).map(typeTransform).map(numMap)
        println(ints)  // List(2, 3, 4, 5)
    }

    // 模式匹配的方式
    def matchList() ={
        val list = List(1, 2, 3, 4, "abc")

        def numMap(num: Any): Any = {
            num match {
                case x :Int => x+1
                case _ =>
            }
        }
        val list2 = list.map(numMap)
        println(list2)   // List(2, 3, 4, 5, ())   最后会有一个空值,不符合要求
    }

}