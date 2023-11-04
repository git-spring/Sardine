package day06

/**
  * @Date 2022/8/18 10:18
  * @author Spring
  *
  */

// scala 函数柯里化
object CurryingDemo {
    def main(args: Array[String]): Unit = {
        println(add(1, 2))
        println(add1(1)(2))
        println(add2(1)(2))
        println(isEqual("sa")("SA"))
    }

    // 定义函数 调用时 add(1,2)
    def add(x: Int, y: Int) = x + y

    // 柯里化  调用时  add(1)(2)  和上面的函数结果一样
    def add1(x: Int)(y: Int) = x + y

    // 原理  add1(1)(2) 实际上是依次调用两个普通函数（非柯里化函数），第一次调用使用一个参数 x，返回一个函数类型的值，第二次使用参数y调用这个函数类型的值。
    // 这里调用时也可以使用 add2(1)(2)
    def add2(x: Int) = (y: Int) => x +  y

    // 比较两个字符串是否相等(忽略大小写)
    def isEqual(s1: String)(s2: String) = {
         s1.toLowerCase()==s2.toLowerCase()
    }

}