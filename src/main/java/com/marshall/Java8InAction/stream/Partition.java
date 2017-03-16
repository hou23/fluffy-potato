package com.marshall.Java8InAction.stream;

import com.marshall.Java8InAction.domain.Dish;

import java.util.*;
import java.util.Map;

import static com.marshall.Java8InAction.domain.Dish.menu;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by yaojie.hou on 2017/3/16.
 *
 * 分区（partition）,分区函数返回一个boolean值，最多分为两组
 */
public class Partition {
    public static void main(String[] args) {
        Map<Boolean, List<Dish>> partitionedMenu
                = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        //{false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}
        System.out.println(partitionedMenu);

        //partitioningBy方法也能传递第二个收集器
        Map<Boolean, Map<Dish.Type, List<Dish>>> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        //{false={MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}, true={OTHER=[french fries, rice, season fruit, pizza]}}
        System.out.println(mostCaloricPartitionedByVegetarian);
    }
}
