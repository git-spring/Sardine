package day02

/**
  * @Date 2022/7/27 17:16
  * @author Spring
  * @Describe : 
  */

// scala 属性重写/覆写
// def只能重写另一个def(即：方法只能重写另一个方法）
// val只能重写另一个val属性或重写不带参数的def
// var 只能重写抽象的var

object FieldOverride {
    def main(args: Array[String]): Unit = {
        val o1: A0727 = new B0727
        val o2: B0727 = new B0727

        printf("o1.age = %d, o2.age = %d\n", o1.age, o2.age) // 20 20
    }
}

class A0727 {
    val age: Int = 10 // 编译器会生成 public age()
}

class B0727 extends A0727 {
    override val age: Int = 20 //  public age()   本质上是重写方法

}