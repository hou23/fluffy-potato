package com.marshall.Java8InAction.stream;


import com.marshall.Java8InAction.domain.Dish;

import java.util.*;
import java.util.Map;

import static com.marshall.Java8InAction.domain.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * Created by yaojie.hou on 2017/3/9.
 *
 * 用流收集数据
 */
public class Collect {

    public static void main(String[] args) {

        Map<Currency, List<Transaction>> map = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        //{JPY=[JPY 7800.0, JPY 5700.0], CHF=[CHF 6700.0, CHF 3400.0], USD=[USD 2300.0, USD 4500.0, USD 4600.0], EUR=[EUR 1500.0, EUR 1100.0, EUR 5600.0, EUR 6800.0], GBP=[GBP 9900.0, GBP 3200.0]}
        System.out.println(map);

        Long howManyDishes = menu.stream().collect(counting());
        //9
        System.out.println(howManyDishes);

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        //Optional[pork]
        System.out.println(mostCaloriesDish);

        Integer totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        //4300
        System.out.println(totalCalories);

        Double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        //477.77777777777777
        System.out.println(avgCalories);

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        //IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
        System.out.println(menuStatistics);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        //pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
        System.out.println(shortMenu);

        /** 以上案列都可以用reducing实现 */
        Integer calories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        //4300
        System.out.println(calories);

        Optional<Dish> mostCalorieDish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        //Optional[pork]
        System.out.println(mostCalorieDish);
    }

    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) );
}
