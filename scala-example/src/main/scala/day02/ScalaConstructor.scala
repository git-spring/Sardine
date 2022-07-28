package day02

/**
  * @Date 2022/7/20 12:12
  * @author Spring
  * @Describe : 
  */

// scala 中的构造器
/**
  * scala 中的构造器分为主构造器和辅助构造器
  * 主构造器 ： 直接定义在类后面,只能有一个
  * 辅助构造器：使用this定义,可以有多个
  */
object ScalaConstructor {
    def main(args: Array[String]): Unit = {
        val p1 = new Person("张三", 20)    // 调用主构造器
        println(p1)

        val p2 = new Person("East")             // 调用辅助构造器,最终会调用主构造器,完成对象的初始化
        println(p2)
    }

}


// 主构造器,直接定义在类后面, 如果不定义,系统会默认定义一个无参主构造器
// 在类名后面加上private 修饰,可以把主构造器变成私有的,这样就不能直接使用主构造器了,需要通过辅助构造器来初始化对象
// 主构造器的形参列表不能和辅助构造器的形参列表相同
class Person ( cname: String, cage: Int) {
    var name: String = cname
    var age: Int = cage

    // 重写toString 方法,方便输出对象的信息
    override def toString = s"Person($name, $age)"

    // 辅助构造器,  在第一行需要显示调用主构造器
    // 直接或间接的方式(通过其他辅助构造器去调用),最终都必须要调用主构造器
    def this(cname: String) {
        this(cname, 10)
        this.name = cname
    }

    // 辅助构造器可以有多个
    def this(cage: Int) {
        this("Alice")      // 调用另一个辅助构造器,另一个辅助构造器第一行会调用主构造器
        this.age = cage
    }

    println("主构造器会把类里面的语句都执行一遍(函数除外)")
}
