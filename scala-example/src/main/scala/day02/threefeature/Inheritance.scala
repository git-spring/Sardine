package day02.threefeature

/**
  * @Date 2022/7/26 17:00
  * @author Spring
  * @Describe :
  */

// scala 继承

object Inheritance {
    def main(args: Array[String]): Unit = {
        val s = new Student
        s.name = "Alice"  // 调用父类的属性
        s.age = 20
        s.showInfo()   // 调用父类的方法
        s.study()

        s.testOverride()

    }

}

class Person {
    var name: String = _
    var age: Int = _

    def showInfo(): Unit = {
        printf("人员信息 :  %s  %d\n", this.name, this.age)
    }

    def testOverride(): Unit ={
        println("调用父类中方法")
    }
}

class Student extends Person {  // Student 继承了 Person类

    def study(): Unit = {
        println("正在学习~~")
    }

    override def testOverride(): Unit ={  // 重写父类中方法和属性需要声明override关键字
        super.testOverride()              // 在重写的方法中调用父类的同名方法,需要使用super关键字
    }
}