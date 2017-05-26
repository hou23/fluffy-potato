package com.marshall.Java8InAction.stream;

import com.marshall.Java8InAction.domain.Dish;

import java.util.*;
import java.util.Map;

import static com.marshall.Java8InAction.domain.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by yaojie.hou on 2017/3/14.
 * <p>
 * 分组（group）
 */
public class Group {
    public static void main(String[] args) {
        //根据已有方法分组
        Map<Dish.Type, List<Dish>> dishedByType = menu.stream().collect(groupingBy(Dish::getType));
        //{OTHER=[french fries, rice, season fruit, pizza], MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}
        System.out.println(dishedByType);

        //根据自定义方法分组
        Map<String, List<Dish>> dishesByCaloriesLevel = menu.stream().collect(groupingBy(d -> {
            if (d.getCalories() <= 400) {
                return "diet";
            } else if (d.getCalories() <= 700) {
                return "normal";
            } else {
                return "fat";
            }
        }));
        //{normal=[beef, french fries, pizza, salmon], fat=[pork], diet=[chicken, rice, season fruit, prawns]}
        System.out.println(dishesByCaloriesLevel);

        //多级分组
        Map<Dish.Type, Map<String, List<Dish>>> dishedByTypeCaloricLevel = menu.stream().collect(groupingBy(Dish::getType, groupingBy(d -> {
            if (d.getCalories() <= 400) {
                return "diet";
            } else if (d.getCalories() <= 700) {
                return "normal";
            } else {
                return "fat";
            }
        })));
        //{OTHER={normal=[french fries, pizza], diet=[rice, season fruit]}, MEAT={normal=[beef], fat=[pork], diet=[chicken]}, FISH={normal=[salmon], diet=[prawns]}}
        System.out.println(dishedByTypeCaloricLevel);

        //groupingBy的第二个参数还可以传递其它收集器
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        //{OTHER=4, MEAT=3, FISH=2}
        System.out.println(typesCount);

        //把收集器返回的结果转换为另外一种类型
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(
                groupingBy(Dish::getType, collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        //{OTHER=pizza, MEAT=pork, FISH=salmon}
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(
                groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        //{OTHER=1550, MEAT=1900, FISH=850}
        System.out.println(totalCaloriesByType);

        Map<Dish.Type, Set<String>> caloricLevelByType = menu.stream().collect(groupingBy(Dish::getType, mapping(d -> {
            if (d.getCalories() <= 400) {
                return "diet";
            } else if (d.getCalories() <= 700) {
                return "normal";
            } else {
                return "fat";
            }
        }, toCollection(HashSet::new))));
        //{OTHER=[normal, diet], MEAT=[normal, fat, diet], FISH=[normal, diet]}
        System.out.println(caloricLevelByType);

		Map<Dish.Type, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));
		List<Dish> meat = collect.get(Dish.Type.MEAT);
		System.out.println(meat);

	}
}
