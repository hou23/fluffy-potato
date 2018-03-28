package java_8_in_action.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yaojie.hou on 2017/3/7.
 *
 * 复合lambad表达式，调用的都是函数式接口本身的默认方法
 */
public class ComplexLambda {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        // 按重量递减排序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        // 谓词（predicate）复合
        Predicate<Apple> p1 = (Apple a) -> "red".equals(a.getColor());
        Predicate<Apple> p2 = p1.or((Apple a) -> a.getWeight() > 100);
        List<Apple> redApples = filterRedApples(inventory, p2);
        //[Apple{color='green', weight=155}, Apple{color='red', weight=120}]
        System.out.println(redApples);

        // 函数（function）复合
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;
        // 先给数字加1，再将结果乘2
        Function<Integer, Integer> function = f1.andThen(f2);
        Integer result = function.apply(1);
        // 4
        System.out.println(result);
    }

    public static <T> List<T> filterRedApples (List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
