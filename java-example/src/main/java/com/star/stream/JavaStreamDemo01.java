package com.star.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *  java 流操作
 * 流是从支持数据处理操作的源生成的元素序列，源可以是数组、文件、集合、函数。流不是集合元素，它不是数据结构并不保存数据，它的主要目的在于计算。
 *
 * 中间操作:
 *      一个流可以后面跟随零个或多个中间操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的，仅仅调用到这类方法，并没有真正开始流的遍历，真正的遍历需等到终端操作时，常见的中间操作有下面即将介绍的 filter、map 等。
 * 终端操作:
 *      一个流有且只能有一个终端操作，当这个操作执行后，流就被关闭了，无法再被操作，因此一个流只能被遍历一次，若想在遍历需要通过源数据在生成流。终端操作的执行，才会真正开始流的遍历。如下面即将介绍的 count、collect 等。
 */

public class JavaStreamDemo01 {
    public static void main(String[] args) throws IOException {

        // 生成流的集中方式

        // 1. 通过集合生成
        List<String> list = Arrays.asList("hello", "world", "la");
        Stream<String> stream = list.stream();

        // 2. 通过数组生成
        int[] arr = {1,2,3,4,5};
        IntStream stream1 = Arrays.stream(arr);

        // 3. 通过值生成
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

        // 4. 通过文件生成
        Stream<String> stream3 = Files.lines(Paths.get("java-example/file/poem.txt"), Charset.defaultCharset());

        // 5. 通过函数生成
        Stream<Integer> stream4 = Stream.iterate(0, n -> n + 2).limit(5);   //
        Stream<Double> stream5 = Stream.generate(Math::random).limit(5);
    }
}
