package com.marshall.Java8InAction.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yaojie.hou on 2017/3/2.
 */
public class SortDemo {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        list.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(list);
    }
}
