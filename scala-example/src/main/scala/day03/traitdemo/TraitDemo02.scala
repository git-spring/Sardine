package day03.traitdemo

/**
  * @Date 2022/8/2 11:04
  * @author Spring
  * @Describe : 
  */

// 在特质中重写抽象方法
object TraitDemo02 {
    def main(args: Array[String]): Unit = {

    }
}

trait Operate5 {
    def insert(id: Int)
}

trait File5 extends Operate5 {

    // 如果在一个子特质中重写/实现了一个父特质的抽象方法,但是同时调用super
    // 这时方法不是完全实现,所以需要声明为 abstract override
    abstract override def insert(id: Int): Unit = {
        println("File5...")
        super.insert(id)
    }
}