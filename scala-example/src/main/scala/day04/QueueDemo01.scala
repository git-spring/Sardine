package day04

import scala.collection.mutable

/**
  * @Date 2022/8/9 15:24
  * @author Spring
  * @Describe : 
  */

// scala Queue  队列
// 队列是一个有序列表, 遵循先入先出规则
object QueueDemo01 {
    def main(args: Array[String]): Unit = {
        val queue: mutable.Queue[Any] = new mutable.Queue[Any]
        println(queue)
        queue += 1              // 追加元素
        queue ++= List(2,3,4)   // 把List中的元素追加到队列中
        queue += List("hello","queue")   // 把集合作为一个元素加到队列中
        println(queue)

        // 返回队列的元素, 对队列本身没有影响
        println("queue.head = " + queue.head)    // 返回第一个元素
        println("queue.last = " + queue.last)    // 返回最后一个元素
        println("queue.tail = " + queue.tail)    // 返回队尾元素 (除第一个元素的其他元素)
        println(queue.tail.tail.tail)  //

        // 加入和取出元素
        val queue01: mutable.Queue[Int] = new mutable.Queue[Int]
        queue01.enqueue(10)    // 入队  从尾部加入
        queue01.enqueue(20)
        queue01.enqueue(30,40,30)

        queue01.dequeue()     // 出队   从头部取出
        println(queue01)
        queue01.dequeueAll(_==30)   // 移除多个符合条件的元素
        println(queue01)

    }
}