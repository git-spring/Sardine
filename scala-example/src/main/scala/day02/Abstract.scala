package day02

/**
  * @Date 2022/7/28 10:10
  * @author Spring
  * @Describe : 
  */

// scala 抽象类
// 使用abstract 标记为抽象类
abstract class Animal {
    // 抽象类中可以包含抽象属性和抽象方法，都不用abstract修饰
    // 抽象属性不用赋默认值
    // 抽象方法省略方法体
    var name: String // 抽象属性
    var age: Int // 抽象属性
    var color: String = "red" // 普通属性属性

    def sing() // 抽象方法
}