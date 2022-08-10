package day03.traitdemo

/**
  * @Date 2022/8/2 15:48
  * @author Spring
  * @Describe : 
  */

// scala 自身类型
object SelfType {
    def main(args: Array[String]): Unit = {

    }
}

// Logger 就是自身类型特质
// 相当于 trait Logger extends Exception,要求混入该特质的类也是Exception 子类
trait Logger {
    // 明确告诉编译器, 这里就是Exception, 如果没有这句话, 下面的getMessage 不能调用
    this: Exception =>
    def log(): Unit = {
        println(getMessage)
    }
}

// class Console extends Logger{}
class Console extends Exception with Logger{}