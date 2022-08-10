package day02

/**
  * @Date 2022/7/28 10:27
  * @author Spring
  * @Describe : 
  */

// 匿名子类的重点在于"子类",所以它是给构造方法用的,在相应的构造方法之后添加一个花括号并在其内书写子类的"类体",表示该构造方法创建了一个相应类的子类对象
// 匿名内部类重在"匿名"两字,给接口/抽象类使用；匿名子类重在"子类"两字,给构造方法使用


object Anonymous {


    def main(args: Array[String]): Unit = {
        // 抽象类的匿名子类对象
        val com: Computer = new Computer {
            override def func1(): Unit = {
                println("func1 invoked")
            }
        }
        com.func1()


        val key = new KeyBoard
        // 匿名内部类,直接作为方法参数传递
        key.write(new Animal {
            override var name: String = ""
            override var age: Int = _

            override def sing(): Unit = {

            }
        })
    }
}


class KeyBoard {
    def write(draw: Animal): Unit = {

    }
}


// 抽象类
abstract class Computer {
    def func1()

}



