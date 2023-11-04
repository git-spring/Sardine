package com.star.IO_;

/**
 * @author liudw
 * @date 2022/11/7 16:14
 */


import java.io.*;

/**
 * java 对象流
 *     用于存储和读取基本数据类型和对象的处理流, 可以把对象保存到数据源中
 *     读取和存储过程需要序列化和反序列化,需要实现序列化接口
 * @see ObjectInputStream
 * @see ObjectOutputStream
 */
public class ObjectStreamDemo {

    // 读取顺序需要和保存数据的顺序一致
    static void inputDemo() throws IOException, ClassNotFoundException {
        String path = "java-example/file/obj_output.txt";
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        System.out.println(in.readInt());
        System.out.println(in.readBoolean());
        System.out.println(in.readChar());
        Object o = in.readObject();
        System.out.println(o.getClass());
        System.out.println("读取到的对象信息  " + o);

    }

    static void outputDemo() throws IOException {
        String path = "java-example/file/obj_output.txt";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeInt(100);
        out.writeBoolean(true);
        out.writeChar(97);
        out.writeObject(new Dog("大黄", 10));
    }

    // 被读取和写入的对象需要实现 Serializable 接口
    static class Dog implements Serializable {
        String name;
        int age;

        public Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}