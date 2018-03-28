package java_8_in_action.test;

import java_8_in_action.stream.MyCollectors;
import java_8_in_action.stream.Partition;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * Created by yaojie.hou on 2017/3/30.
 * <p>
 * 自定义累加器和Java8提供的累加器性能比较
 * 自定义性能高30%左右
 */
public class CollectorHarness {
	public static void main(String[] args) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			//partitionPrimes(1000000);// 448ms
			partitionPrimesWithCustomerCollector(1000000);// 312ms
			long duration = (System.nanoTime() - start) / 1000000;
			if (duration < fastest) fastest = duration;
		}
		System.out.println("Fastest execution done in " + fastest + " msecs");
	}

	private static void partitionPrimes(int maxNum) {
		IntStream.rangeClosed(2, maxNum).boxed().collect(partitioningBy(Partition::isPrime));
	}

	private static void partitionPrimesWithCustomerCollector (int maxNum) {
		IntStream.rangeClosed(2, maxNum).boxed().collect(new MyCollectors.PrimeNumberCollector());
	}
}

