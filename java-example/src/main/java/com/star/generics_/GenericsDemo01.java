package com.star.generics_;

/**
 * @author Spring
 * @date 2022/10/20 10:59
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * java 泛型
 *    泛型的本质是参数化类型,即给类型指定一个参数,然后在使用时再指定此参数具体的值,那样这个类型就可以在使用时决定了
 *    这种参数类型可以用在类、接口和方法中,分别被称为泛型类、泛型接口、泛型方法
 *
 * 泛型的作用
 *     1 保证类型的安全性
 *     2 避免强制转换
 *     3 避免了不必要的装箱、拆箱操作，提高程序的性能
 *
 *
 * 泛型通配符
 *   1 <?> 无边界通配符    表示类型参数可以是任何类型
 *   2 <? extends E> 固定上边界的通配符   表示类型参数必须是E或者是E的子类
 *   3 <? super E> 固定下边界的通配符     表示类型参数必须是E或者是E的超类型
 */
public class GenericsDemo01 {
    public static void main(String[] args) {
        GenericsClass<Integer, String> gc = new GenericsClass(1, "hello");  // 使用的时候可以自定义类型
        System.out.println(gc.first);
        System.out.println(gc.second);

        int i = gc.func1(new LinkedList());
        int i1 = gc.func1(new ArrayList());
        System.out.println(i1);

        // 通配符的使用
        ArrayList<? extends List> list = new ArrayList<>();

        // 泛型避免强制转换
        ArrayList arrayList = new ArrayList();
        arrayList.add("Slice");
        arrayList.add("Bob");
        String o = (String)arrayList.get(0);  // 数据类型需要强制转换成String,否则为Object

        // 指定泛型
        ArrayList<String> arrayList1 = new ArrayList();
        arrayList1.add("Slice");
        arrayList1.add("Bob");
        //arrayList1.add(1);   // 指定了泛型为String ,则不能传入其他类型的数据,保证了类型安全
        String o1 = arrayList1.get(0);  // 数据类型不需要强制转换
    }
}


// 泛型类
// U,V 在使用是自定义类型
class GenericsClass<U, V> {
    U first;
    V second;

    public GenericsClass() {
    }

    public GenericsClass(U first, V second) {
        this.first = first;
        this.second = second;
    }

    // 泛型方法
    // 泛型方法的参数化类型与泛型类的参数化类型没有关系,可以自定义
    // 泛型方法的参数化类型写在方法返回值之前
    public <T extends List> int func1(T list) { // 调用方法时只要传入 List 或者 List 的子类就可以了

        return list.size();
    }
}

