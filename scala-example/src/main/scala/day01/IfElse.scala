package day01

/**
  * @Date 2022/7/1 16:09
  * @author Spring
  * @Describe : 
  */

// scala 中 if ... else
object IfElse {
    def main(args: Array[String]): Unit = {
        val a = 10
        val b = 20
        val c = 30
        var res =
            if (a > b) 1
            else if (b > c) 2
            else "3"
        println(res)
    }
}
