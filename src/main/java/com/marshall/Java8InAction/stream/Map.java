package com.marshall.Java8InAction.stream;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yaojie.hou on 2017/3/7.
 * <p>
 * map和flatMap
 */
public class Map {
    public static void main(String[] args) {
        // 使用flatMap能将流中的每一个元素都转换成另一个流
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        // [H, e, l, o, W, r, d]
        System.out.println(uniqueCharacters);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        // [1, 4, 9, 16, 25]
        System.out.println(squares);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        // [[1,4],[1,5],[2,4],[2,5],[3,4],[3,5]]
        System.out.println(JSON.toJSONString(pairs));

        List<int[]> pairs2 = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        // [[1,5],[2,4]]
        System.out.println(JSON.toJSONString(pairs2));
    }
}
