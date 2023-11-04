package com.star.enum_;

/**
 * @author Spring
 * @date 2022/10/10 10:52
 */

// java enum 枚举
public class Enumeration01 {
    public static void main(String[] args) {
        System.out.println(Season.SPRING.toString());   // Season{name='春天', description='大地回春,百花齐放'}
        System.out.println(Season.SPRING.getName());    // 春天
        System.out.println(Season.SUMMER.name());       // SUMMER
        System.out.println(Season.SUMMER.ordinal());    // 1
        System.out.println(Season.valueOf("AUTUMN"));   // Season{name='秋天', description='晴空一鹤排云上，便引诗情到碧霄'}
        System.out.println(Season.WINTER.compareTo(Season.SPRING));  // 3
        Season[] values = Season.values();
        for (Season value:values) {
            System.out.println(value);
        }

        String name = Season.SPRING.getEnumName(1);
    }
}


// 创建一个枚举类
// 枚举类默认继承了Enum类,可以使用Enum类的方法
enum Season {
    // 枚举对象 必须放在枚举类的行首
    SPRING("春天","大地回春,百花齐放"),
    SUMMER("夏天","连雨不知春去，一晴方觉夏深"),
    AUTUMN("秋天","晴空一鹤排云上，便引诗情到碧霄"),
    WINTER("冬天","千山鸟飞绝，万径人踪灭");

   private String name;
   private String description;


   // 有参构造
   private Season(String name,String description){
       this.name = name;
       this.description = description;
   }

   // 无参构造
   private Season(){}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // 普通方法
    public  String getEnumName(int index){
        for (Season season : Season.values()){
            if (season.getName()==name){
                return season.getName();
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

