package com.star.datastructure.enumeration;


// 向枚举中添加新方法
public class EnumDemo2 {
    public static void main(String[] args) {
        String name = Color.getName(2);
        System.out.println(name);
    }
}

enum Color {
    RED("红色", 1), GREEN("绿色", 2), WHITE("白色", 3), YELLO("黄色", 4);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Color(String name, int index) {
        this.name=name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index){
        for (Color color : Color.values()){
            if (color.getIndex()==index){
                return color.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        //super.toString();
        return "Color{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}
