package day01

/**
  * @Date 2022/7/8 16:28
  * @author Spring
  * @Describe : 
  */

// scala 中没有 break 和 continue 关键字, 但是可以用其他方式实现
object BreakAndContinue {
    def main(args: Array[String]): Unit = {
        continueDemo()
    }

    import util.control.Breaks._

    // break
    def breakDemo(): Unit = {

        var n = 1
        breakable( // breakable() 是一个高阶函数，是用来处理 抛出的异常
            while (n < 10) {
                println("n= " + n)
                n += 1
                if (n == 5) {
                    break()
                }
            })

        println("ok~~~~") // 如果上面使用breakable() , 那么会抛出异常,不悔执行这一行代码
    }

    // continue
    def continueDemo(): Unit = {
        // 使用循环守卫的方式
        breakable(
            for (i <- 1 to 5 if (i != 2 && i != 3)) {
                println("i= " + i)
            })

        // 使用if...else的方式
        for (i <- 1 to 5) {
            breakable(
                if (i == 2 || i == 3) {
                    break()
                } else {
                    println("i= " + i)
                })
        }
    }
}