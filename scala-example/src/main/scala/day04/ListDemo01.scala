package day04

/**
  * @Date 2022/8/9 11:42
  * @author Spring
  * @Describe : 
  */

//Scala List 列表 (不可变)
object ListDemo01 {
    def main(args: Array[String]): Unit = {
        // 创建一个List
        val list01 = List(1, 2, 3, "None")
        // val list = Nil // 空集合
        println(list01)
        println("list01(0) = " + list01(0)) // 通过下标访问元素

        // List 中追加元素 :+ , +:, 追加后会返回一个新的列表
        val list02 = Nil
        val list03 = list02 :+ 5  // 在列表后面追加数据
        val list04 = 6 +: list03  // 在列表前面追加数据
        println("list02 = " + list02)   // 原集合没有变化
        println("list03 = " + list03)
        println("list04 = " + list04)

        // ::   :::
        // :: 把集合追加到集合中
        val list05 = List(1, 2, 5, "hello")
        val list06 = 2 :: 4 :: 6 :: list05 :: Nil  // 在空集合里追加元素和集合
        println("list06 = " + list06)  // List(2, 4, 6, List(1, 2, 5, hello))

        //  :::   扁平化   , ::: 两边都要是集合
        val list07 = 2 :: 4 :: 6 :: list05 ::: Nil
        println("list07 = " + list07)     // List(2, 4, 6, 1, 2, 5, hello)
    }
}