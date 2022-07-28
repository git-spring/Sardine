package day01.forDemo

/**
  * @Date 2022/7/5 11:04
  * @author Spring
  * @Describe :
  */

// 多层for循环
object MultiFor {
    def main(args: Array[String]): Unit = {
        // 多层循环嵌套
        for (i <- 1 to 3; j <- 1 to 3) {
            println("i=" + i + ";j=" + j)
        }

        println("---------------------------")
        // 等价于
        for (i <- 1 to 3) {
            for (j <- 1 to 3) {
                println("i=" + i + ";j=" + j)
            }
        }
    }

    // 99乘法表
    def multiplication(): Unit = {
        for (i <- 1 to 9; j <- 1 to i) {
            printf("%d * %d = %d\t", j, i, i * j)
            if (i == j) {
                println()
            }
        }
    }
}