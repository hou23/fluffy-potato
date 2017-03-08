package com.marshall.Java8InAction.lambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yaojie.hou on 2017/3/6.
 *
 * 构造函数引用
 */
public class ConstructorReference {
    public static void main(String[] args) {
        Supplier<Apple> a1 = Apple::new;
        Apple apple1 = a1.get();
        //Apple{color='', weight=0}
        System.out.println(apple1);

        Function<Integer, Apple> a2 = Apple::new;
        Apple apple2 = a2.apply(100);
        //Apple{color='', weight=100}
        System.out.println(apple2);

        List<Integer> weights = Arrays.asList(5, 3, 6, 8);
        List<Apple> result = map(weights, Apple::new);
        //[Apple{color='', weight=5}, Apple{color='', weight=3}, Apple{color='', weight=6}, Apple{color='', weight=8}]
        System.out.println(result);

        BiFunction<Integer, String, Apple> a3 = Apple::new;
        Apple apple3 = a3.apply(10, "green");
        //Apple{color='green', weight=10}
        System.out.println(apple3);

        //Apple{color='', weight=100}
        System.out.println(giveMeFruit("apple1", 100));
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            Apple apple = f.apply(i);
            result.add(apple);
        }
        return result;
    }

    static Map<String, Function<Integer, Apple>> map = new HashMap<>();

    static {
        map.put("apple1", Apple::new);
        map.put("apple2", Apple::new);
    }

    public static Apple giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }
}
