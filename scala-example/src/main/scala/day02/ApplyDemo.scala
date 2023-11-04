package day02

/**
  * @Date 2022/7/29 9:55
  * @author Spring
  * @Describe : 
  */

// Scala apply 方法
// 伴生对象中定义apply方法,可以实现以 类名([参数])的方法来创建实列对象

object ApplyDemo {
    def main(args: Array[String]): Unit = {

        val p1 = new Person0729("Alice")
        val p2 = Person0729("Page")
        val p3 = Person0729()

        println("p1.name = " + p1.name) // p1.name = Alice
        println("p2.name = " + p2.name) // p2.name = Page
        println("p3.name = " + p3.name) // p3.name = 小明
    }
}

class Person0729(pName: String) {
    var name: String = pName
}

// 在伴生对象中定义apply方法
object Person0729 {
    def apply(pName: String): Person0729 = new Person0729(pName)

    def apply(): Person0729 = new Person0729("小明")
}