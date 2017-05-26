package com.marshall.Java8InAction.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.marshall.Java8InAction.domain.Dish.menu;


/**
 * Created by yaojie.hou on 2017/3/7.
 *
 * 流的迭代
 */
public class Iterator {
    public static void main(String[] args) {
        // filter和map虽然是两个独立的操作，但他们合并到同一次遍历中了（循环合并）
        List<String> collect = menu.stream().filter(d -> {
            System.out.println("filtering " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("mapping " + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toList());

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
        // [6, 7, 2, 6]
        System.out.println(wordLengths);
    }
}
