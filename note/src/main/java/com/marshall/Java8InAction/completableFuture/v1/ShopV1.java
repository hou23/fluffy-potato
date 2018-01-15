package com.marshall.Java8InAction.completableFuture.v1;

import java.util.Random;

import static com.marshall.Java8InAction.completableFuture.Util.delay;
import static com.marshall.Java8InAction.completableFuture.Util.format;

/**
 * Created by yaojie.hou on 2017/4/27.
 */
public class ShopV1 {
	private final String name;
	private final Random random;

	public ShopV1(String name) {
		this.name = name;
		random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
	}

	public String getPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return name + ":" + price + ":" + code;
	}

	public double calculatePrice(String product) {
		delay();
		return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
	}

	public String getName() {
		return name;
	}
}
