package java_8_in_action.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * Created by yaojie.hou on 2017/3/30.
 *
 * 自定义累加器
 */
public class MyCollectors {

	public static class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
		/** 创建一个空的累加器实例 */
		@Override
		public Supplier<List<T>> supplier() {
			return ArrayList::new;
		}

		/** 把当前数据添加到遍历过的列表中 */
		@Override
		public BiConsumer<List<T>, T> accumulator() {
			return List::add;
		}

		/** 各个子部分所得的累加器如何合并 */
		@Override
		public BinaryOperator<List<T>> combiner() {
			return (l1, l2) -> {
				l1.addAll(l2); // 将第一个累加器与第二个累加器合并
				return l1;
			};
		}

		/** 将累加器对象转换为最终结果，这里list正好符合预期结果 */
		@Override
		public Function<List<T>, List<T>> finisher() {
			return Function.identity(); // 恒等函数
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
		}
	}

	public static PrimeNumberCollector isPrime () {
		return new PrimeNumberCollector();
	}

	public static class PrimeNumberCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
		@Override
		public Supplier<Map<Boolean, List<Integer>>> supplier() {
			return () -> new HashMap<Boolean, List<Integer>>() {{
				put(true, new ArrayList<>());
				put(false, new ArrayList<>());
			}};
		}

		@Override
		public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
			return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
				acc.get(isPrime(acc.get(true), candidate))
						.add(candidate);
			};
		}

		@Override
		public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
			return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
				map1.get(true).addAll(map2.get(true));
				map1.get(false).addAll(map2.get(false));
				return map1;
			};
		}

		@Override
		public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
			return Function.identity();
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
		}
	}

	private static boolean isPrime (List<Integer> primes, Integer candidate) {
		int candidateRoot = (int) Math.sqrt(candidate);
		return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
	}

	// 以p条件分割list，取之前的返回
	private static <A> List<A> takeWhile (List<A> list, Predicate<A> p) {
		int i = 0;
		for (A a : list) {
			if (!p.test(a)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}
}
