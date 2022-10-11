package com.star.interface_;


/**
 * @author Spring
 * @date 2022/9/22 10:32
 */

// 接口的多态
interface Computer {
    void connected();

    void disconnected();
}

class Usb implements Computer {
    String name;

    Usb(String name) {
        this.name = name;
    }

    @Override
    public void connected() {
        System.out.println(this.name + " 连接了电脑");
    }

    @Override
    public void disconnected() {
        System.out.println(this.name + " 与电脑断开了连接");
    }

    @Override
    public String toString() {
        return "Usb{" +
                "name='" + name + '\'' +
                '}';
    }
}

class CellPhone implements Computer {
    String name;

    CellPhone(String name) {
        this.name = name;
    }

    @Override
    public void connected() {
        System.out.println(this.name + " 连接了电脑");
    }

    @Override
    public void disconnected() {
        System.out.println(this.name + " 与电脑断开了连接");
    }

    @Override
    public String toString() {
        return "CellPhone{" +
                "name='" + name + '\'' +
                '}';
    }

}


public class InterfaceDemo02 {
    public static void main(String[] args) {
        InterfaceDemo02 if2 = new InterfaceDemo02();

        Usb usb = new Usb("usb 3.0");
        CellPhone cellPhone = new CellPhone("xiaomi");

        if2.work(usb);
        if2.work(cellPhone);
    }

    public void work(Computer computer) {
        computer.connected();
        computer.disconnected();
    }
}