package day01

/**
  * @Date 2022/7/18 17:22
  * @author Spring
  * @Describe :
  */

// Scala 递归
// 递归时必须指定返回类型,因为在递归结束前编译器无法推断返回的类型
object Recursion {
    def main(args: Array[String]): Unit = {
        println(peach(1))
    }

    /**
      * 斐波拉契数 , 输入 n,返回第 n 个数
      * f(1) =1
      * f(2) =1
      * f(n)=f(n-1)+f(n-2)
      */

    def fibonacci(n: Int): Int = {
        // 第一个和第二个数都为1
        if (n == 1 || n == 2) {
            1
        } else {
            fibonacci(n - 1) + fibonacci(n - 2)
        }
    }

    /**
      * 猴子吃桃问题
      * 有一堆桃子,猴子第一天吃一半,并再多吃一个,以后每天都吃一半,并且再多吃一个,到第十天想再吃时(还没吃),只有一个桃子了。
      * 问： 最初有多少个桃子?
      * 分析:
      * f(10) = 1
      * f(9) = (1+1)*2   = 4
      * f(8) = ((1+1)*2+1)*2  =10
      * f(7) = (((1+1)*2+1)*2 +1) *2  = 22
      * f(n) = (f(n+1)+1)*2
      */
    def peach(day: Int): Int = {
        if (day == 10) {
            1
        } else {
            (peach(day + 1) + 1) * 2
        }
    }
}