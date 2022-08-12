package day05

/**
  * @Date 2022/8/12 10:35
  * @author Spring
  *
  */

// scala 密封类
object SealedClassDemo {

}

// 如果一个类申明为密封类,那么只能在本源码文件中被继承,其他地方都不能被继承,本包下也不能
sealed class SealedClass{}

// 在本文件下可以被继承,其他地方都不可以继承这个类
class Test extends SealedClass{}