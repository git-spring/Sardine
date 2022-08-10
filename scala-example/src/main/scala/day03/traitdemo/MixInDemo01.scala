package day03.traitdemo

/**
  * @Date 2022/8/1 16:13
  * @author Spring
  * @Describe : 
  */

// Scala 动态混入特质
// 动态混入可以在实例化对象时扩展目标类的功能,而不用修改类的定义,实现低耦合
// 简单理解,动态混入就是将特质中的方法混入到继承特质的类中
object MixInDemo01 {
    def main(args: Array[String]): Unit = {
        // 在不修改类的定义下混入了特质,可以使用insert方法
        val oracle = new OracleDB with Trait02
        oracle.insert(10)

        // 抽象类中有抽象方法,混入特质时需要实现抽象方法
        val mysql = new MySQLDB with Trait02 {
            override def deleteall() = {
                println("需要实现抽象类中的抽象方法")
            }
        }
        mysql.deleteall()      // 特质中的方法和抽象类中的方法都可以调用
        mysql.insert(20)
    }
}

trait Trait02 {
    def insert(id:Int) {
        println("插入数据 "+id)
    }
}


class OracleDB {
    //
}

abstract class MySQLDB {
    // 抽象方法
    def deleteall()

}