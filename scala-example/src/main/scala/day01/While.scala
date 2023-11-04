package day01

/**
  * @Date 2022/7/5 11:22
  * @author Spring
  * @Describe : 
  */

// scala while
object While {
    def main(args: Array[String]): Unit = {
        var i = 0           // 循环变量
        while (i < 5) {     // 循环条件
            println("你好") // 循环体
            i += 1          // 循环变量迭代
        }


        // do while

        var count = 0
        do {
            println("你好呀")
            count += 1
        } while (count < 5)
    }
}