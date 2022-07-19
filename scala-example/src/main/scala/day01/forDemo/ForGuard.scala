package day01.forDemo

/**
  * @Date 2022/7/4 16:38
  * @author Spring
  * @Describe :
  */

// scala 循环守卫
object ForGuard {
    def main(args: Array[String]): Unit = {
        // if 后面的就是守卫, 只有符合条件的才会进入for循环
        for (i <- 1 to 3 if i != 2) {
            println(i)
        }

        // 循环守卫等价于 for循环里嵌套if
        for (i <- 1 to 3) {
            if (i != 2) {
                println(i)
            }
        }
    }
}