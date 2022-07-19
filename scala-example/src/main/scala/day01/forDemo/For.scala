package day01.forDemo

/**
  * @Date 2022/7/4 15:59
  * @author Spring
  * @Describe :
  */

// scala for 循环
object For {
    def main(args: Array[String]): Unit = {
        var start = 1
        var end = 5
        // for 循环, 前后都包含
        for (i <- start to end) {
            println(i)
        }
        // until 包前不包后
        for (i <- start until end) {
            println(i)
        }


        // 对集合进行遍历
        var list = List("hello", "cat", 10)
        for (item <- list) {
            println(item)
        }
    }
}