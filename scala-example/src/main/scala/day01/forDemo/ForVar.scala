package day01.forDemo

/**
  * @Date 2022/7/5 11:00
  * @author Spring
  * @Describe :
  */

// for 循环中引入变量
object ForVar {
    def main(args: Array[String]): Unit = {
        // 在for循环里引入变量
        for (i <- 1 to 3; j = 4 - i) {
            println(j)
        }

        // 等价于
        for (i <- 1 to 3) {
            var j = 4 - i
            println(j)
        }
    }
}