package com.marshall.Java8InAction.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by yaojie.hou on 2017/4/24.
 */
public class AsyncShop {

	private final String name;
	private final Random random;

	public AsyncShop(String name) {
		this.name = name;
		random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
	}

	public static void main(String[] args) {
		AsyncShop shop = new AsyncShop("BestShop");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = (System.nanoTime() - start) / 1000000;
		System.out.println("Invocation returned after " + invocationTime + " msecs");

		//执行更多任务, 比如查询其他商店
		//doSomethingElse();
		//在计算商品价格的同时
		try {
			double price = futurePrice.get();
			System.out.println("Price is " + price);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		long retrievalTime = (System.nanoTime() - start) / 1000000;
		System.out.println("Price returned after " + retrievalTime + " msecs");
	}

	//异步方法实现价格查询
	public Future<Double> getPriceAsync(String product) {
		//创建CompletableFuture对象, 它会包含计算的结果
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		//在另一个线程中以异步方式执行计算
		new Thread(() -> {
			try {
				double price = calculatePrice(product);
				//需长时间计算的任务结束并得出结果时, 设置Future的返回值
				futurePrice.complete(price);
			} catch (Exception e) {
				futurePrice.completeExceptionally(e);
			}
		}).start();
		//无需等待还没结束的计算, 直接返回Future对象
		return futurePrice;
	}

	/**
	 * 使用工厂方法创建CompletableFuture对象, 和之前手动创建的CompletableFuture是完全等价的
	 * 生产者方法会交由ForkJoinPool池中的某个执行线程(Executor)运行,
	 * 也可以使用supplyAsync方法的重载版本, 传递第二个参数指定不同的执行线程执行方法
	 */
	public Future<Double> getPriceAsyncByFactory(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	private double calculatePrice(String product) {
		return 0;
	}
}
