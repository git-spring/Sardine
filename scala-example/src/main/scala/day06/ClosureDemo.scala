package day06



/**
  * @Date 2022/8/12 15:45
  * @author Spring
  *
  */

// scala closure 闭包
object ClosureDemo {
    def main(args: Array[String]): Unit = {

        val out = outer()
        println(out)
        //println(outer()(3))

        val res = f1(1)(2)
        println(res)

    }

    // inner 函数和 变量 a 形成了一个闭包
    def outer() = {
        var a = 10

        def inner(n2: Int) = {
            a + n2
        }

        inner(1) // 直接计算好,把值返回
        // inner _  这种方式会返回一个函数,调用时需要传参
    }

    // 闭包的另一种形式
    // 匿名函数  (x: Int) => x + y  和 f1的形参 y 形成了闭包
    def f1(y: Int) = (x: Int) => x + y
}
