package day03.implicitdemo

/**
  * @Date 2022/8/4 10:49
  * @author Spring
  * @Describe : 
  */


// 隐式值
//
object ImplicitDemo03 {
    def main(args: Array[String]): Unit = {
        // 隐式值
        implicit val str1: String = "Alice"
        //implicit val str2: String = "Alice"   //有多个符合条件的隐式值时,代码会报错
        // 用implicit 修饰的形参是隐式参数
        def hello(implicit name: String): Unit = {
            println("hello " + name)
        }

        // 调用时不需要可以不传递参数,会自动去找隐式值
        hello
    }
}

