package day01.function

/**
  * @Date 2022/8/10 9:54
  * @author Spring
  * @Describe :
  */

// scala 高阶函数
// 可以接受函数作为参数的函数称为高阶函数
object HigherOrderFunction {
    def main(args: Array[String]): Unit = {
        val res: Double = test(sum, 2)
        println(res)
    }

    def test(func: Double => Double, n: Double) = {
        println("test函数被调用~")
        func(n)
    }

    // 函数作为参数传递时,会被调用
    def sum(d: Double) = {
        println("sum函数被调用")
        d + d
    }

    // 高阶函数应用
    val ints: List[Int] = List(1, 2).map(_ * 2)

}