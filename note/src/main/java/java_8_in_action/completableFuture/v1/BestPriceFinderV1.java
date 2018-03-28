package java_8_in_action.completableFuture.v1;

import java_8_in_action.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yaojie.hou on 2017/4/27.
 */
public class BestPriceFinderV1 {
	private final List<ShopV1> shops = Arrays.asList(new ShopV1("BestPrice"),
			new ShopV1("LetsSaveBig"),
			new ShopV1("MyFavoriteShop"),
			new ShopV1("BuyItAll"),
			new ShopV1("ShopEasy"));

	private static BestPriceFinderV1 bpfv1 = new BestPriceFinderV1();

	public static void main(String[] args) {
		Utils.getDuration("sequential", () -> bpfv1.findPricesSequential("sequential"));//10078

	}

	//顺序执行
	public List<String> findPricesSequential(String product) {
		return shops.stream()
				.map(s -> s.getPrice(product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());
	}


}
