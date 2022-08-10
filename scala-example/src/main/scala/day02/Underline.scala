package day02

/**
  * @Date 2022/8/10 10:18
  * @author Spring
  * @Describe : 
  */

// Scala _ 下划线的作用
object Underline {
    def main(args: Array[String]): Unit = {
        // 1. 初始化变量,表示赋默认值
        //var name: String = _
        // 2. 用于导包,_表示导入该包下的所有包
        import scala.collection.mutable._

        // 3. 用于将方法转变为函数
        // 在Scala中方法不是值,而函数是,所以一个方法不能赋值给一个val变量,而函数可以
        val t = test()      // 这里会直接执行方法
        val t1 = test _     // 这样是把方法转为函数 并赋值给变量
        t1()                // 这里执行变量的结果
        // 4. 用于模式匹配
        // 5. 用于访问tuple
        // 6. 用于简写函数
        val ints: List[Int] = List(1, 2).map(_ * 2)
        // 7. 定义偏函数

    }

    def test(): Unit = {
        println("~~~~")
    }

}

