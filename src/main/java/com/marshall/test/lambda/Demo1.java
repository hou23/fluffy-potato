package com.marshall.test.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by yaojie.hou on 2017/3/1.
 *
 */
public class Demo1 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        /**
         * [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
         * 两种方法都能得到结果，前两个选择那种依赖代码的可读性
         */
        System.out.println(filterApples(inventory, Demo1::isGreenApple));
        System.out.println(filterApples(inventory, (Apple a) -> "green".equals(a.getColor())));
        /**
         * 两种stream流的方式。第一种是顺序处理，第二种是并行处理
         */
        List<Apple> appleList = inventory.stream().filter((Apple a) -> "green".equals(a.getColor())).collect(Collectors.toList());
        inventory.parallelStream().filter((Apple a) -> "green".equals(a.getColor())).collect(Collectors.toList());
        System.out.println(appleList);
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
