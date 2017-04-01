package com.marshall.Java8InAction.stream;

import com.marshall.Java8InAction.domain.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.marshall.Java8InAction.domain.Dish.menu;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

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
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        //{false={MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}, true={OTHER=[french fries, rice, season fruit, pizza]}}
        System.out.println(vegetarianDishesByType);

		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
				menu.stream().collect(partitioningBy(Dish::isVegetarian,
						collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
		//{false=pork, true=pizza}
		System.out.println(mostCaloricPartitionedByVegetarian);

		//多级分区
		Map<Boolean, Map<Boolean, List<Dish>>> vegetarianAndCaloricDishes =
				menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
		//{false={false=[chicken, prawns, salmon], true=[pork, beef]}, true={false=[rice, season fruit], true=[french fries, pizza]}}
		System.out.println(vegetarianAndCaloricDishes);

		Map<Boolean, Long> vegetarianDishCount =
				menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
		//{false=5, true=4}
		System.out.println(vegetarianDishCount);

		//区分质数和非质数
		Map<Boolean, List<Integer>> partitionPrimes =
				IntStream.rangeClosed(2, 20).boxed().collect(partitioningBy(Partition::isPrime));
		//{false=[4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20], true=[2, 3, 5, 7, 11, 13, 17, 19]}
		System.out.println(partitionPrimes);

		// 使用自定义的累加器实现区分质数和非质数
		Map<Boolean, List<Integer>> partitonPrimesByMyCollector = IntStream.rangeClosed(2, 10).boxed().collect(MyCollectors.isPrime());
		// {false=[4, 6, 8, 9, 10], true=[2, 3, 5, 7]}
		System.out.println(partitonPrimesByMyCollector);
	}

	public static boolean isPrime(int candidate){
    	int candidateRoot = (int) Math.sqrt(candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}
}
