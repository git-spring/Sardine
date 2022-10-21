package com.star.datastructure;


import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Spring
 * @date 2022/10/18 15:23
 */


// java 自定义迭代器
// 实现倒叙迭代
public class IteratorDemo02 {

    public static void main(String[] args) {
        IteratorDemo02 id02 = new IteratorDemo02();
        Iterator<String> iterator = id02.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }


    private String[] array = {"A", "B", "C", "D", "E"};

    int length = array.length;

    // 使用内部类的方式
    private class MyIterator implements Iterator {
        private int cursor = -1;

        @Override
        public boolean hasNext() {
            return cursor + 1 < IteratorDemo02.this.length;
        }

        @Override
        public Object next() {
            this.cursor++;
            return array[length - 1 - cursor];    // 实现倒序迭代
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    // 返回自定义实现的迭代器对象
    public Iterator<String> iterator() {
        return new MyIterator();
    }

}
