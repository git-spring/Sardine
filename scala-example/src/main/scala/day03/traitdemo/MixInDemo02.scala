package day03.traitdemo

/**
  * @Date 2022/8/1 16:32
  * @author Spring
  * @Describe : 
  */

// Scala 叠加特质
// 构建对象的同时如果混入多个特质,称之为叠加特质,特质声明顺序从左到右,方法执行顺序从右到左

object MixInDemo02 {
    def main(args: Array[String]): Unit = {

        val mysql = new MySQL1 with Operate3 with Operate4

        mysql.insert(10)
    }
}

trait Operate1 {
    println("Operate1")

    def insert(id: Int)
}

trait Operate2 extends Operate1 {
    println("Operate2")

    override def insert(id: Int): Unit = { // 实现Operate1抽象方法
        println("Operate2 插入数据 " + id)
    }
}


trait Operate3 extends Operate2 {
    println("Operate3")

    override def insert(id: Int): Unit = { // 重写Operate2的insert方法
        println("Operate3 插入数据 " + id)
        super.insert(id)
    }
}

trait Operate4 extends Operate2 {
    println("Operate4")

    override def insert(id: Int): Unit = { // 重写Operate2的insert方法
        println("Operate4 插入数据 " + id)
        super.insert(id)
    }
}

class MySQL1 {

}



