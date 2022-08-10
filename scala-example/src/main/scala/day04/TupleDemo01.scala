package day04

/**
  * @Date 2022/8/9 11:19
  * @author Spring
  * @Describe : 
  */

// scala Tuple 元组
object TupleDemo01 {
    def main(args: Array[String]): Unit = {
        // 创建一个tuple, 底层是tuple5, 因为有5个元素
        val tuple01 = (1,2,3,"hello",6.6)

        // 访问元素
        tuple01._1      // 访问第一个元素
        tuple01._2      // 访问第二个元素
        tuple01.productElement(0)   // 第一个元素,下标从0开始

        // 遍历元组
        // 使用迭代器
        for (item <- tuple01.productIterator){
            println(item)
        }


    }
}