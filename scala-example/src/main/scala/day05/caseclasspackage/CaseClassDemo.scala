package day05.caseclasspackage

/**
  * @Date 2022/8/11 15:39
  * @author Spring
  *
  */

// scala 样例类
object CaseClassDemo {
    def main(args: Array[String]): Unit = {
        // 创建伴生对象并访问属性
        val p = Person("张三", 20)
        println(p.name)

        val c = Clz02("Alice")
        c.showName()
        c.copy()// 样例类默认实现的方法
    }
}

// 创建样例类
// 会自动创建伴生对象和其他方法,如 toString()、equals()、copy()、hashCode()，伴生对象中的apply()、unapply   可通过反编译查看
case class Person(name: String, age: Int)


class Clz01 {}

// 样例类也是一个类 其中可以定义方法
case class Clz02(name:String){
    val str:String = ""
    def showName(): Unit ={
        println(name)
    }
}

case object Clz03 {}           // 样例对象
