package day04

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * @Date 2022/8/9 10:39
  * @author Spring
  * @Describe : 
  */


// scala ArrayBuffer 变长数组
object ArrayDemo02 {
    def main(args: Array[String]): Unit = {
        // 定义一个变长数组 ArrayBuffer
        val arr01 = ArrayBuffer[Any]()
        // val arr01 = ArrayBuffer[Any](1,4,7)  // 在定义时赋值

        arr01.append("1")         // 追加元素
        arr01(0) = 4
        arr01.append("2",3)       // 追加元素
        arr01.remove(1)           // 移除元素

        for (item <- arr01) {
            println(item)
        }

        // 定长数组与变长数组转换
        val array: Array[Any] = arr01.toArray        // 变长 -> 定长
        val buffer: mutable.Buffer[Any] = array.toBuffer   // 定长 -> 变长

        // ArrayBuffer 与 javaList转换
        // 将Scala的数组 转化为Java的List
        val arr = ArrayBuffer(1,2,3,4,5,6)
        import scala.collection.JavaConversions.bufferAsJavaList
        val javalist: util.List[Int] = bufferAsJavaList(arr)
        println(javalist)

        // 将Java的List 转化为Scala的数组
        import scala.collection.JavaConversions.asScalaBuffer
        val scalaBuffer: mutable.Buffer[Int] = asScalaBuffer(javalist)
        scalaBuffer.append(13)
        println(scalaBuffer)


    }
}