package day01.function

/**
  * @Date 2022/8/12 14:47
  * @author Spring
  *
  */

// scala 匿名函数
// 定义匿名函数 (variable1 : type1 [,...]) => {函数体}
//
object AnonymousFunction {
    def main(args: Array[String]): Unit = {
       // 定义一个匿名函数,并赋值给一个变量
        val res = (x:Int,y:Double) => x + y
        println(res)   // 不传参数不会报错, 会输出函数的类型 <function2>
        println("res = " + res(1,2))    // 调用匿名函数

        println(AnonymousReturn())
        val res1 = AnonymousReturn()             // 匿名函数作为返回值返回
        println(res1(5,6))
    }


    // 匿名函数作为其他函数的参数时,参数类型可以省略不写,参数高阶函数
    // 匿名函数可以作为其他函数的参数，也可作为其他函数的返回值

    def AnonymousReturn(): (Int, Double) => Double = {
        (x: Int, y: Double) => x + y
    }

}