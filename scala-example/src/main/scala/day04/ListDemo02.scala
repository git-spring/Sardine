package day04

import scala.collection.mutable.ListBuffer

/**
  * @Date 2022/8/9 15:14
  * @author Spring
  * @Describe : 
  */

// scala ListBuffer  可变列表
object ListDemo02 {
    def main(args: Array[String]): Unit = {
        // 创建一个可变列表
        val listBuffer = ListBuffer[Int](1,2)
        listBuffer(1)            // 访问元素
        listBuffer += 3          // 追加元素
        listBuffer.append(4)     // 追加元素
        listBuffer.remove(3)     // 删除指定下标的元素

        // 遍历
        for (item <- listBuffer){
            println(item)
        }
    }
}