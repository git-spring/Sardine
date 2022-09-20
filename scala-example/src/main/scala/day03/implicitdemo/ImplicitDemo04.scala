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

        // 创建一个MySQL的实例
        // 只要创建了一个隐式类中主构造的类型对象, 那么这个对象就可以调用隐式类中的方法和属性
        val mySQL = new MySQL04
        mySQL.sayOk()
        mySQL.addSuffix()   // 可以使用隐式类中的方法

    }
}

class DB04 {}

class MySQL04 {
    def sayOk(): Unit = {
        println("sayOk ~")
    }
}
