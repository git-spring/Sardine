package day05

/**
  * @Date 2022/8/11 10:58
  * @author Spring
  *
  */

// scala parallel 并行集合
object ParallelDemo {
    def main(args: Array[String]): Unit = {
        val res1 = (0 to 100).map { case_ => Thread.currentThread().getName }.distinct
        val res2 = (0 to 100).par.map { case_ => Thread.currentThread().getName }.distinct

        println("res1 = " + res1)
        println("res2 = " + res2)
    }
}