package com.marshall.Java8InAction.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yaojie.hou on 2017/3/3.
 */
public class FunctionalInterface {

    /**
     * Predicate接口：定义一个test抽象方法，接受泛型T对象，返回一个boolean
     */
    static class PredicateDemo {

        public static void main(String[] args) {
//            List<String> result = filter(Arrays.asList("first", "", "third", "", "fifth"), (String s) -> !s.isEmpty());
            List<String> result = filter(Arrays.asList("first", "", "third", "", "fifth"), String::isEmpty);
            //[first, third, fifth]
            System.out.println(result);
        }

        public static <T> List<T> filter(List<T> list, Predicate<T> p) {
            List<T> result = new ArrayList<T>();
            for (T t : list) {
                if (!p.test(t)) {
                    result.add(t);
                }
            }
            return result;
        }
    }

    /**
     * Consumer接口：定义一个accept抽象方法，接受泛型T对象，没有返回
     */
    static class ConsumerDemo {
        public static void main(String[] args) {
            //1 2 3 4 5
//            forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
            forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);
        }

        public static <T> void forEach(List<T> list, Consumer<T> c) {
            for (T i : list) {
                c.accept(i);
            }
        }
    }

    /**
     * Function接口：定义一个apply抽象方法，接受泛型T对象，返回一个泛型R对象
     */
    static class FunctionDemo {
        public static void main(String[] args) {
//            List<Integer> result = apply(Arrays.asList("first", "second"), (String s) -> s.length());
            List<Integer> result = apply(Arrays.asList("first", "second"), String::length);
            //[5, 6]
            System.out.println(result);
        }

        public static <T, R> List<R> apply(List<T> list, Function<T, R> f) {
            List<R> l = new ArrayList<>();
            for (T t : list) {
                l.add(f.apply(t));
            }
            return l;
        }
    }

}
