package day01

import scala.io.StdIn

/**
  * @Date 2022/7/1 15:57
  * @author Spring
  * @Describe : 
  */

// 从控制台输入
object ConsoleInput {
    def main(args: Array[String]): Unit = {
        println("请输入 : ")
        while (true) {
            val input = StdIn.readLine() // 获取控制台的输入
            if (input.toLowerCase() == "exit") {
                println("byebye!")
                return
            }
            println("输入的内容是： " + input)
        }
    }
}