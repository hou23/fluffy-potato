package com.marshall.Java8InAction.parallelStream;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by yaojie.hou on 2017/4/1.
 *
 * 并行流简介
 */
public class Intro {
	public static void main(String[] args) {
		//配置线程池，推荐不做修改
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");

		//测试求和并行和顺序的性能
		//System.out.println("Sequential sum done in: " + measureSumPerf(Intro::sequentialSum, 10000000) + " msecs");//368ms
		//System.out.println("Parallel sum done in: " + measureSumPerf(Intro::parallelSum, 10000000) + " msecs");//652ms
		//System.out.println("Parallel range sum done in: " + measureSumPerf(Intro::parallesRangeSum, 10000000) + " msecs");//1ms
		System.out.println("ForkJoin sum done in: " + measureSumPerf(Intro::forkJoinSum, 10000000) + " msecs");//109ms
	}

	/** 对1 - n所有数字求和 */
	public static long sequentialSum (long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.reduce(0L, Long::sum);
	}

	/**
	 * 性能并不高的并行求和操作
	 * 原因：iterate很难分成多个独立块来并行执行
	 * 		生成的数据是装箱过的，需要拆箱
	 */
	public static long parallelSum (long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
	}

	/**
	 * 高性能的并行求和操作
	 */
	public static long parallesRangeSum (long n) {
		return LongStream.rangeClosed(0, n)
				.parallel()
				.reduce(0L, Long::sum);
	}

	/** 测试性能 */
	public static long measureSumPerf (Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1000000;
			System.out.println("Result: " + sum);
			if (duration < fastest) fastest = duration;
		}
		return fastest;
	}

	/** 使用分支/合并框架并行求和 */
	public static long forkJoinSum (long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

}
