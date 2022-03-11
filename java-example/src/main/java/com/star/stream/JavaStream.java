package com.star.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// java 流操作
public class JavaStream {
    public static void streamDemo(){
        List<String> list = Arrays.asList("hello", "world", "la");
        Stream<String> stream = list.stream();


    }
}
