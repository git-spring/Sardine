package day03.implicitdemo

/**
  * @Date 2022/8/4 11:17
  * @author Spring
  * @Describe : 
  */

// 隐式类
object ImplicitClassDemo04 {
    def main(args: Array[String]): Unit = {
        // 隐式类
        implicit class DB04(val m: MySQL04) {
            def addSuffix(): Unit = {
                println(m + " scala")
            }
        }

        //创建一个MySQL1的实例
        val mySQL = new MySQL04
        mySQL.sayOk()
        mySQL.addSuffix()

    }
}

class DB04 {}

class MySQL04 {
    def sayOk(): Unit = {
        println("sayOk ~")
    }
}
