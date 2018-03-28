package java_8_in_action.completableFuture.v1;

import static java_8_in_action.completableFuture.Util.delay;
import static java_8_in_action.completableFuture.Util.format;

/**
 * Created by yaojie.hou on 2017/4/27.
 */
public class Discount {
	public enum Code{
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}
	}

	public static String applyDiscount(Quote quote) {
		return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}
	private static double apply(double price, Code code) {
		delay();
		return format(price * (100 - code.percentage) / 100);
	}
}
