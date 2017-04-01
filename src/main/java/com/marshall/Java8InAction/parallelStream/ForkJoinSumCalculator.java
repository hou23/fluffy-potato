package com.marshall.Java8InAction.parallelStream;

import java.util.concurrent.RecursiveTask;

/**
 * Created by yaojie.hou on 2017/4/1.
 *
 * 用分支/合并框架并行求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
	//要求和的数组
	private final long[] numbers;
	//子任务处理的数组的起始和终止位置
	private final int start;
	private final int end;
	//不再将任务分解为子任务的数组大小
	public static final long THRESHOLD = 10000;
	//公共构造函数用于创建主任务


	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		//该任务负责求和的部分的大小
		int length = end - start;
		if (length <= THRESHOLD) {
			return computeSequentially();
		}
		//创建一个任务为数组的前一半求和
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
		//利用另一个ForkJoinPool线程异步执行新创建的子任务
		leftTask.fork();
		//创建一个任务为数组的后一半求和
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
		//同步执行第二个子任务，有可能允许进一步递归划分
		Long rightResult = rightTask.compute();
		//读取第一个子任务的结果，如果尚未完成就等待
		Long leftResult = leftTask.join();
		//该任务的结果是两个子任务结果的组合
		return rightResult + leftResult;
	}

	private long computeSequentially () {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += numbers[i];
		}
		return sum;
	}
}
