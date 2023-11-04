package day02

// scala 包对象
// 每个包都允许有一个包对象,在包对象中的任何定义都被认为是包自身的成员, 使用是可能需要先导入 import day02.scalapackage._
// 按照惯例,包对象的代码通常放在名为 package.scala 的源文件中


package object scalapackage {
    var name: String = "Alice" // 在包对象中可以定义变量和方法

    def test(): Unit = {
        println("包对象中定义的方法")
    }
}


//package scalapackage{    // 必须和包对象名称相同
//    object Demo{
//        def main(args: Array[String]): Unit = {
//            println(name)   // 可以直接使用包对象中的变量和方法
//            test
//        }
//    }
//}