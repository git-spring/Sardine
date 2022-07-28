package day02


/**
  * @Date 2022/7/26 17:42
  * @author Spring
  * @Describe : 
  */

// scala 类型检查和转换
// isInstanceOf
// asInstanceOf
// classOf
object TypeCheck {
    def main(args: Array[String]): Unit = {
        // 查看类型
        println(classOf[String])
        println(classOf[Person0727])

        var p: Person0727 = new Student0727   // 父类引用指向子类对象
        var s: Student0727 = new Student0727
        //p = s    // 父类可以接受子类的类型(向上转型)
        //s = p    // 子类不能接受父类的类型(需要强转)

        // 类型检查
        val isPer: Boolean = p.isInstanceOf[Person0727]
        val isStu: Boolean = p.isInstanceOf[Student0727]
        // 强转类型
        val s1: Student0727 = p.asInstanceOf[Student0727]
        s1.stu() // 强转之后类型变为子类的类型,可以调用子类的方法
        // val clazz: OtherClass = p.asInstanceOf[OtherClass]

    }
}

class Person0727 {
    def per(): Unit = {

    }
}

class Student0727 extends Person0727 {
    def stu(): Unit = {
         println("Student func")
    }
}

class OtherClass {

}