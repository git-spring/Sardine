package day01

/**
  * @Date 2022/7/20 9:59
  * @author Spring
  * @Describe :
  */

// scala 惰性函数
object LazyFunction {
    /**
      * lazy 不能修饰var类型的变量
      * lazy 修饰的变量值也会懒加载（在cmd窗口中演示）
      */

    def main(args: Array[String]): Unit = {
        lazy val res = sum(10, 20) // 被lazy关键字修饰后,不会立即执行
        println("--------------")
        println(res) // 被调用时才会执行
    }

    def sum(n1: Int, n2: Int): Int = {
        println("sum函数执行了 ~~~")
        return n1 + n2
    }
}