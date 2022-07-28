package day02

// scala 包对象
package object scalapackage {
    var name : String ="Alice"   // 在包对象中可以定义变量和方法

    def test(): Unit ={
        println("包对象中定义的方法")
    }
}



package scalapackage{    // 必须和包对象名称相同
    object Demo{
        def main(args: Array[String]): Unit = {
            println(name)   // 可以直接使用包对象中的变量和方法
            test
        }
    }
}