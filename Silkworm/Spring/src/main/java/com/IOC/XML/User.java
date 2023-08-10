package com.IOC.XML;

/**
 * @author liudw
 * @date 2022/12/2 17:20
 */

public class User {

    String name ;
    int age ;
    Book book;

    public void info(){
        System.out.println("User 类中的 info 方法被调用.");
    }

    public User(){}

    public User(String name ,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", book=" + book +
                '}';
    }
}