package day03.traitdemo

/**
  * @Date 2022/7/29 10:55
  * @author Spring
  * @Describe : 
  */

// scal 特质
object TraitDemo {
    def main(args: Array[String]): Unit = {
        val d1 = new Demo01
        val d2 = new Demo02
        d1.getConnect()
        d2.getConnect()
    }
}

// scala 特质包含java 接口和抽象类的特性，既可以定义普通方法，也可以定义抽象方法
trait Trait01 {
    def getConnect()

    def ordinary(): Unit ={
        println("普通方法")
    }
}


class Demo01 extends Trait01 {
    override def getConnect(): Unit = {
        println("连接mysql！")
    }
}

class Demo02 extends Trait01 {
    override def getConnect(): Unit = {
        println("连接oracle！")
    }
}

