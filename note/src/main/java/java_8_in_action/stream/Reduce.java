package java_8_in_action.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java_8_in_action.domain.Dish.menu;


/**
 * Created by yaojie.hou on 2017/3/8.
 *
 * 归约操作
 */
public class Reduce {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        // 15
        System.out.println(sum);

        Integer product = numbers.stream().reduce(1, (a, b) -> a * b);
        // 120
        System.out.println(product);

        //Java8中Integer类有一个静态sum方法可以简化求和代码
        Integer sum2 = numbers.stream().reduce(0, Integer::sum);
        // 15
        System.out.println(sum2);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        // Optional[5]
        System.out.println(max);

        //求流中有多少个菜
        Integer menus = menu.stream().map(d -> 1).reduce(0, Integer::sum);
        long menus2 = menu.stream().count();
        // 9
        System.out.println(menus);
        System.out.println(menus2);
    }
}
