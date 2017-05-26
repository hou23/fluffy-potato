package com.marshall.Java8InAction.stream;

import com.marshall.Java8InAction.domain.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.marshall.Java8InAction.domain.Dish.menu;

/**
 * Created by yaojie.hou on 2017/3/9.
 * <p>
 * 流特化
 */
public class SpecialStream {
    public static void main(String[] args) throws IOException {

        //mapToInt方法返回的是IntStream（数值流），IntStream接口中有sum方法，如果流为空，默认返回0
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        // 4300
        System.out.println(calories);

        //将数值流再转回一般流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        //找最大值
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        //OptionalInt[800]
        System.out.println(maxCalories);

        //如果没有最大值，定义一个默认值
        int max = maxCalories.orElse(1);
        //800
        System.out.println(max);

        //range方法不包含结束值，rangeClosed则包含结束值
        long evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).count();
        //50
        System.out.println(evenNumbers);

        //找出勾股数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                        mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        //3,4,5  5,12,13  6,8,10  7,24,25  8,15,17
        pythagoreanTriples.limit(5).forEach(n -> System.out.println(n[0] + "," + n[1] + "," + n[2]));

        //勾股数-第二种优化方案,先生成所有三元数，再排除非整数
        Stream<double[]> pythagoreanTriples2 = IntStream
				.rangeClosed(1, 100).boxed().flatMap(a ->
						IntStream.rangeClosed(a, 100).mapToObj(b ->
								new double[]{a, b, Math.sqrt(a * a + b * b)}).filter(m -> m[2] % 1 == 0));
        //3.0,4.0,5.0  5.0,12.0,13.0  6.0,8.0,10.0  7.0,24.0,25.0  8.0,15.0,17.0
        pythagoreanTriples2.limit(5).forEach(n -> System.out.println(n[0] + "," + n[1] + "," + n[2]));

        //文件流
        long uniqueWords = Files.lines(Paths.get("D:\\workspaces\\marshall\\fluffy-potato\\src\\main\\resources\\lambdasinaction\\chap5\\data.txt"), Charset.defaultCharset()).flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        //9
        System.out.println(uniqueWords);

        //无限流 0 1 2 3 4
        Stream.iterate(0, n -> n + 1).limit(5).forEach(System.out::println);

        //斐波那契元组序列
        List<Integer> fibonacci = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(5).map(t -> t[0]).collect(Collectors.toList());
        //[0, 1, 1, 2, 3]
        System.out.println(fibonacci);

        /*0.08614075233769314
        0.31059805893719716
        0.26124442127069725
        0.4614334812567453
        0.8625154640551357*/
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }
}
