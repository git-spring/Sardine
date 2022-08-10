package day03.innerclass

import day03.innerclass

/**
  * @Date 2022/8/2 16:16
  * @author Spring
  * @Describe : 
  */

// scala 内部类

object ICdemo01 {
    def main(args: Array[String]): Unit = {
        // 创建外部类实例
        val outer1: ScalaOuterClass = new ScalaOuterClass()
        val outer2: ScalaOuterClass = new ScalaOuterClass()
        // 创建成员内部类的实例
        val inner1: outer1.ScalaInnerClass = new outer1.ScalaInnerClass()
        val inner2: outer2.ScalaInnerClass = new outer2.ScalaInnerClass()
        // 创建静态内部类的实例
        val staticInner: ScalaOuterClass.ScalaStaticInnerClass = new ScalaOuterClass.ScalaStaticInnerClass()

        inner1.info()


    }
}

class ScalaOuterClass { // 伴生类
    alias => // 给这个类体起一个别名
    class ScalaInnerClass1 {
        // 通过别名访问外部类的属性
        def info(): Unit = {
            println("name = " + alias.name + "  salary = " + alias.salary + "~~~~")
        }
    }

    val name = "Alice"
    private val salary = 2000

    class ScalaInnerClass { // 成员内部类
        // 内部类中访问外部类的属性
        // ScalaOuterClass.this 相当于一个外部类的实例
        def info(): Unit = {
            println("name = " + ScalaOuterClass.this.name + "  salary = " + ScalaOuterClass.this.salary)
        }
    }
    
}

object ScalaOuterClass { // 伴生对象

    class ScalaStaticInnerClass { // 静态内部类

    }

}