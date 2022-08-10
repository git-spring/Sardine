package day04



/**
  * @Date 2022/8/9 16:13
  * @author Spring
  * @Describe : 
  */

// scala Map
// Map 中的每一个 key-value 对都是一个tuple2元组
object MapDemo01 {
    def main(args: Array[String]): Unit = {
        // 不可变 Map
        val map01 = Map("name" -> "Alice","age" -> 10)
        println(map01("name"))

        // 可变的 Map
        import scala.collection.mutable
        val map02 = mutable.HashMap("name" -> "Alice","age" -> 10)
        // 创建一个空 Map
        val map03 = new scala.collection.mutable.HashMap[String,Int]
        // 使用对偶元组的方式创建Map
        val map04 = mutable.Map(("name" , "Alice"),("age" , 10))


        // 取值
        map01("name")    // 返回对应的value, 如果key不存在,则会报错
        map01.contains("name")  // 检查key是否存在
        map01.get("name")      // 返回一个Option 对象,如果key存在,则再使用get方法获取到值,如果key不存在,则返回None
        map01.getOrElse("name","default value")   // 如果key存在,则返回对应的值, key不存在,则返回默认值

        // 可变Map的操作
        val map05 = mutable.Map(("name","Tom"),("age",5))
        map05("hobby") = "catch rat"    // 增加元素
        map05("age") = 6                // key存在则修改,key不存在,则新增元素

        map05 + (""->"")
        map05 += ("gender" -> "male","success" -> 0)   // 添加元素
        map05 -= ("success")            // 删除, 只需要传入key, 如果key不存在,不会报错
        map05.remove("")

        println("①----------------------------")
        // 遍历
        for ((k, v) <- map05) println(k, v)
        println("②----------------------------")
        for (k <- map05.keys) println(k,map05(k))
        println("③----------------------------")
        for (v <- map05.values) println(v)
    }
}