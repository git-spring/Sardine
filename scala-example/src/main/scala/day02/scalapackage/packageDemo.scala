
// Scala的包  (普通包文件,不是包对象)
// 和实际创建文件一样,只是使用包对象后,可以把很对对象都写在一个文件里
// 在包对象中,子包可以向上访问

// 编译后代码会在对应路径下生成.class文件
package com.star { // com.star 包
    package demo { // com.star.demo 包

        class Emp1 { // 在 com.star.demo包下的类和方法
            val name: String = ""
            val position: String = ""

            def test() = {
                ""
            }
        }

    }

    class Emp2 { // com.star 包下的类

    }

    object Test001 {
        def main(args: Array[String]): Unit = {
            import com.star.demo.Emp1
            val e1 = new Emp1 // 使用Emp1 需要导入,因为不能向下访问,只能向上
            val e2 = new Emp2 // 使用Emp2 不需要导入,同级
        }
    }

}
