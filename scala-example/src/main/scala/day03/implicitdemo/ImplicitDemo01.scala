package day03.implicitdemo

/**
  * @Date 2022/8/3 16:53
  * @author Spring
  * @Describe : 
  */

// Scala 隐式转换
// Scala 隐式转换函数的作用域

object ImplicitDemo01 {
    def main(args: Array[String]): Unit = {
        // 如果没有下面的隐式转换函数,这里会报错
        val num: Int = 3.5 // 底层编译 f1$1(3.5)
        println("num= " + num)
    }

    // 隐式转换函数, 把 Double-> Int
    implicit def f1(d: Double): Int = {
        d.toInt
    }

}