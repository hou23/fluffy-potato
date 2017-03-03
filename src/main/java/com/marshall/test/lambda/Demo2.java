package com.marshall.test.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yaojie.hou on 2017/3/1.
 */
public class Demo2 {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        prettyPrintApple(list, new AppleFancyFormatter());
    }

    public interface AppleFormatter {
        String accept(Apple a);
    }

    public static class AppleFancyFormatter implements AppleFormatter {

        @Override
        public String accept(Apple a) {
            String weight = a.getWeight() > 150 ? "heavy" : "light";
            return "A " + weight + " " + a.getColor() + " apple";
        }
    }

    public static void prettyPrintApple(List<Apple> list, AppleFormatter formatter) {
        for (Apple a : list) {
            String result = formatter.accept(a);
            System.out.println(result);
        }
    }

}
