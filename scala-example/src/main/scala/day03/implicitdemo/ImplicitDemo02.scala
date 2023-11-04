package day03.implicitdemo

/**
  * @Date 2022/8/4 10:40
  * @author Spring
  * @Describe : 
  */

// 使用隐式转换增加功能

object ImplicitDemo02 {

    // 隐式函数丰富MySQL02的功能
    implicit def addDelete(mysql: MySQL02): DB02 = {
        new DB02
    }

    def main(args: Array[String]): Unit = {

        val m = new MySQL02
        m.insert()
        m.delete() // 没有隐式转换就不能调用delete函数  底层调用了隐式函数 addDelete(m),返回了一个DB02实例

    }
}

class MySQL02 {
    def insert(): Unit = {
        println("Insert...")
    }
}

class DB02 {
    def delete(): Unit = {
        println("delete...")
    }
}