package day04

/**
  * @Date 2022/8/4 15:49
  * @author Spring
  * @Describe : 
  */

// Scala Array 定长数组
object ArrayDemo01 {
    def main(args: Array[String]): Unit = {
        // 创建一个数组,泛型为Int,长度为5
        val arr01 = new Array[Int](5)
        arr01(1) = 10      // 赋值
        println("arr01.length = " + arr01.length)   // 5

        // 遍历
        for (item <- arr01) {
            println(item)  // 未赋值,则为默认值
        }


        // 使用apply初始化数组
        var arr02 = Array(1,2,"abc")
    }
}