package day04


/**
  * @Date 2022/8/9 17:04
  * @author Spring
  * @Describe : 
  */

//scala Set 集
// Set 不能重复,没有顺序
object SetDemo01 {
    def main(args: Array[String]): Unit = {
        //创建Set

        val set = Set(1, 2, 3) //不可变
        println(set)
        import scala.collection.mutable
        val set01 = mutable.Set(1, 2, "hello") //可以变
        println("set2" + set01)

        //Set 中添加元素
        set01.add(4) //方式 1
        set01 += 6   //方式 2
        set01.+=(5)  //方式 3

        //Set 中删除元素
        val set02 = mutable.Set(1, 2, 4, "abc")
        set02 -= 2 // 操作符形式
        set02.remove("abc") // 方法的形式，scala 的 Set 可以直接删除值
        //说明：说明：如果删除的对象不存在，则不生效，也不会报错

        //Set 遍历
        val set03 = mutable.Set(1, 2, 4, "abc")
        for (x <- set03) {
            println(x)
        }
    }
}