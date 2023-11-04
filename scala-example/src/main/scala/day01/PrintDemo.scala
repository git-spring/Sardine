package day01

/**
  * @Date 2022/6/30 15:09
  * @author Spring
  * @Describe :
  */


// 字符串输出的方式
object PrintDemo {
    def main(args: Array[String]): Unit = {
        // 方式1
        var str1: String = "hello "
        var str2: String = " world"
        println(str1 + str2)

        // 方式2
        var name: String = "zhangsan"
        var age: Int = 10
        var salary: Double = 2000
        printf("名字:%s  年龄:%d \n", name, age)

        // 方式3
        println(s"名字:$name  年龄:$age  薪水:${salary * 12}")
    }
}