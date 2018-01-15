package com.marshall.Java8InAction.stream;



import com.marshall.Java8InAction.domain.Dish;

import java.util.Optional;

import static com.marshall.Java8InAction.domain.Dish.menu;

/**
 * Created by yaojie.hou on 2017/3/8.
 */
public class MatchAndFind {
    public static void main(String[] args) {
        if (menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("the menu is vegetarian friendly");
        }

        boolean isHealthy1 = menu.stream().allMatch(d -> d.getCalories() < 1000);
        // true
        System.out.println(isHealthy1);

        boolean isHealthy2 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        // true
        System.out.println(isHealthy2);

        // -----------------------------------------------------------------------

        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        // Optional[french fries]
        System.out.println(dish);

        // french fries
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));

    }
}
