package com.marshall.Java8InAction.completableFuture;

import java.util.Random;

import static com.marshall.Java8InAction.completableFuture.Util.format;

/**
 * Created by yaojie.hou on 2017/4/24.
 *
 * 调用该方法时, 它依旧会被阻塞. 为等待同步时间完成而等待1秒钟
 */
public class Shop {

	private final String name;
	private final Random random;

	public Shop(String name) {
		this.name = name;
		this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));;
	}

	// 同步方法实现价格查询
	public double getPrice(String product) {
		return calculatePrice(product);
	}

	private double calculatePrice(String product) {
		delay();
		return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}

}
