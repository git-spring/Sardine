package day05

import scala.collection.mutable.ArrayBuffer

/**
  * @Date 2022/8/11 11:01
  * @author Spring
  *
  */

// scala match 模式匹配
object MatchDemo {
    def main(args: Array[String]): Unit = {
        matchDemo05()
    }


    // 模式匹配基础
    def matchDemo01()={
        val oper = "#"
        val n1 = 10
        val n2 = 20
        val n3 = 30
        var res = 0
        // 不需要像java 一样每个case 后面写break, Scala中会自动跳出
        oper match {
            case "-" => res = n1-n2
            case "+" => res = n1+n2
            case "/" => res = n1/n2
            case _ => println("未匹配上")  // _ 匹配所有
        }

        // case 后面的语法比较灵活,可以使用条件守卫
        // 如果有多个下划线,则会匹配第一个
        // 如果下划线放在第一行,则所有的都会匹配第一行
        // 说明 _ 是表示匹配所有
        for (ch <- "+-3!") { //对 "+-3!" 遍历
            var sign = 0
            var digit = 0
            ch match {
                case '+' => sign = 1
                case '-' => sign = -1
                case _ if ch.toString.equals("3") => digit = 3    // case 后面使用条件守卫
                case _ => sign = 2
            }
            println(ch + " "+sign+" "+digit)
        }
    }

    // 模式匹配 使用变量
    def matchDemo02()={
        val ch = 'U'
        ch match {
            case 'A' => println("is A")
            //case mychar 表示把ch 赋值给mychar  mychar = ch
            case mychar => println("这里有个 "+mychar)
            case _ => println("ok~~")
        }
    }

    // 模式匹配可以有返回值
    // 返回值就是匹配到的代码块的最后一句话的值
    def matchDemo03()={
        val ch = '+'
        // 把匹配的结果返回给变量
        val res = ch match {
            case '+' => ch + "hello"
            case _ => println("ok~~")
        }
        println("res= "+res)
    }

    // 类型匹配
    // 可以在case后面匹配对象的任意类型,这样做避免了使用 isInstanceOf 和 asInstanceOf 方法
    // case 变量: 类型 => 代码
    // 在匹配时会把类型赋给case后面的变量
    def matchDemo04()={
        val a = 4
        //obj的值 根据a的值来确定
        val obj = if (a == 1) 1
        else if (a == 2) "2"
        else if (a == 3) BigInt(3)
        else if (a == 4) Map(1 -> "aa")
        else if (a == 5) Map("aa" -> 1)
        else if (a == 6) Array(1, 2, 3)
        else if (a == 7) Array("aa", 1)
        else if (a == 8) Array("aa")
        // 根据obj的类型来匹配
        val result = obj match {
            case a: Int => a
            case b: Map[String, Int] => b + " 是一个字符串-数字的Map集合"
            case b: Map[Int, String] => b + " 是一个数字-字符串的Map集合"
            case d: Array[String] => "对象是一个字符串数组"
            case e :Array[Int] => "对象是一个数字数组"
            case f:BigInt => Int.MaxValue
            case _ => "啥也不是"
        }
        println(result)
    }


    // 对集合进行模式匹配

    // 匹配数组
    def matchDemo05()={
        val arrs = Array(Array(0),Array(0,0,0,0),Array(1,0),Array(0,1,0),List(0,1),0,(1,0))

        for (arr <- arrs){
            var res = arr match {
                case Array(0) => "0"
                case Array(x, y) => ArrayBuffer(y, x)       // 变换顺序
                case Array(x) => Array(x).length
                case Array(0, _*) => "数组第一个值为0"       // _ 表示任意值,*表示任意个,如果不加* ,则只能匹配到第一个为0且只有两个元素的数组
                case _ => "没有指定格式"
            }
            println(res)
        }
    }

    // 匹配列表
    def matchDemo06()={
        for (list <- Array(List(0), List(1, 0), List(88), List(0, 0, 1), List(1, 0, 0))) {
            val result = list match {
                case 0 :: Nil => "0"  //匹配只有0的
                case x :: y :: Nil => x + " " + y  //匹配有两个元素的
                case 0 :: tail => "0..."  //匹配第一个元素为0的
                case x :: Nil => x   // 匹配只有一个元素的
                case _ => "something else "
            }
            println(result)
        }
    }

    // 匹配元组
    def matchDemo07()={
        for (pair <- Array((0,1),(1,0),(10,30),(1,1),(1,0,2),(1,6,2))){
            val result = pair match{
                case (0,_) => "0 .."        // 匹配二元组且第一个元素为0
                case (y,0) => ".. 0"        // 匹配二元组且第二个元素为0
                case (x,y) => (y,x)         // 二元组两个元素交换
                case _ => "others"
            }
            println(result)
        }
    }

}