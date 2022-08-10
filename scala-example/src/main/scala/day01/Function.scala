package day01

/**
  * @Date 2022/7/8 20:48
  * @author Spring
  * @Describe :
  */

// Scala 函数
object Function {
    def main(args: Array[String]): Unit = {
        val res = getRes(1, 2, '-')
        println(res)
    }

    /**
     *  函数返回值
     *    1. 明确定义的类型， 返回值必须是此类型 (明确写了return ,返回值类型就不能省略)
     *    2. 只写 = 号 , 代码会自行推断
     *    3. 什么都不写 或者 :Unit=, 没有返回值,相当于void
     *
     */


    def getRes(n1: Int, n2: Int, oper: Char) = {
        if (oper == '+') {
            n1 + n2
        } else if (oper == '-') {
            n1 - n2
        } else {
            null
        }
    }
}