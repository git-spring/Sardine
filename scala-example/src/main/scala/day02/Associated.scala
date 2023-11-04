package day02

/**
  * @Date 2022/7/28 15:03
  * @author Spring
  * @Describe : 
  */

// scala 伴生类和伴生对象


object Associated {

}

// 伴生类
// 将非静态的内容写到该类中，编译后底层生成ScalaPerson类 ScalaPerson.class
class ScalaPerson {
    println(ScalaPerson.age) // 可以直接使用伴生对象调用伴生对象内的内容
}

// 伴生对象
// 将静态的内容写入到该对象中，编译后底层生成ScalaPerson$类 ScalaPerson$.class
object ScalaPerson {
    var age: Int = 10
}
