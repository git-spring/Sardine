package day01.forDemo

/**
  * @Date 2022/7/5 11:09
  * @author Spring
  * @Describe : 
  */

// scala yield
object ForYield {
    def main(args: Array[String]): Unit = {
        // 对 for 遍历
        // yield i 将每次循环得到的结果 i 放到 Vector 中,并返回给res
        // i 可以是代码块
        val res = for (i <- 1 to 5) yield i
        println(res)

        // eg.
        val res1 = for (i <- 1 to 5) yield (i % 2 == 1)
        println(res1)

    }

}