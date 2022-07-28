package day01

/**
  * @Date 2022/7/20 10:23
  * @author Spring
  * @Describe :
  */

// scala exception
object ExceptionDemo {
    def main(args: Array[String]): Unit = {
        try {
            val r = 10 / 0
            //throw new Exception("手动抛异常1111")  // 手动抛异常
        } catch {
            case ex: ArithmeticException => println("算术异常,请检查公式")
            case ex: Exception => {
                println("catch 里面可以用多个case 匹配多个异常")
                ex.getMessage
                ex.printStackTrace()
            }
        } finally {
            println("finally 中的代码一定会被执行, 和java中的一样")
        }

    }
}