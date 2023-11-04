package day05

/**
  * @Date 2022/8/11 10:20
  * @author Spring
  *
  */

// scala stream 流
object StreamDemo {
    def main(args: Array[String]): Unit = {
        // 创建一个Stream
        def stream(n: BigInt): Stream[BigInt] = n #:: stream(n + 1)
        var st1 = stream(1)

        println(st1)

        for (i<- 1 to 5){
            st1 = stream(i)
            println("st1.head = " +st1.head)
            println("st1.tail = " +st1.tail)
        }

    }
}