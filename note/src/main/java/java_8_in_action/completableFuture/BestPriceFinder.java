package java_8_in_action.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * Created by yaojie.hou on 2017/4/27.
 *
 * 并行流(parallelStream)和CompletableFuture的效率在默认线程池时差不多,
 * 但是CompletableFuture能对Executor进行配置, 修改线程池的大小,
 * 这时并行流做不到的
 */
public class BestPriceFinder {

	private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"),
			new Shop("ShopEasy"),
			new Shop("BestPrice2"),
			new Shop("LetsSaveBig2"),
			new Shop("MyFavoriteShop2"),
			new Shop("BuyItAll2"));

	public static void main(String[] args) {
		BestPriceFinder bpf = new BestPriceFinder();
		long start =System.nanoTime();
		//bpf.findPrices("myPhone27s");//9132
		//bpf.findPricesParallel("myPhone27s");//2163
		//bpf.findPricesCompletableFuture("myPhone27s");//2179
		bpf.findPricesCompletableFutureAndExecutor("myPhone27s");//1141
		System.out.println((System.nanoTime() - start) / 1000000);
	}

	public List<String> findPrices(String product) {
		return shops.stream()
				.map(s -> String.format("%s price is %.2f",
						s.getName(), s.getPrice(product)))
				.collect(Collectors.toList());
	}

	//使用并行流避免顺序计算
	public List<String> findPricesParallel(String product) {
		return shops.parallelStream()
				.map(s -> String.format("%s price is %.2f",
						s.getName(), s.getPrice(product)))
				.collect(Collectors.toList());
	}

	//使用CompletableFuture发起异步请求
	public List<String> findPricesCompletableFuture(String product) {
		List<CompletableFuture<String>> pricesFuture =
				shops.stream()
						.map(s -> CompletableFuture.supplyAsync(() -> s.getName() + "price is " + s.getPrice(product)))
						.collect(Collectors.toList());
		return pricesFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	public List<String> findPricesCompletableFutureAndExecutor(String product) {
		List<CompletableFuture<String>> pricesFuture =
				shops.stream()
						.map(s -> CompletableFuture.supplyAsync(() -> s.getName() + "price is " + s.getPrice(product), executor))
						.collect(Collectors.toList());
		return pricesFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	//自定义最优价格查询执行器
	//创建一个线程池, 线程数目为100和商店数目二者中较小的一个值
	private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			//使用守护线程, 这种方法不会阻止程序的关停
			t.setDaemon(true);
			return t;
		}
	});
}
