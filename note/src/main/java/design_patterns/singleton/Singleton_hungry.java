package design_patterns.singleton;

/**
 * @author yaojie.hou
 * @create 2018/3/23
 *
 * 饿汉式单例实现.
 * 优点: 没有加锁, 执行效率很高
 * 缺点: 类加载时就初始化, 浪费内存
 * 它基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化
 * instance 显然没有达到 lazy loading 的效果。
 */
public class Singleton_hungry {

	private static Singleton_hungry instance = new Singleton_hungry();

	private Singleton_hungry() {}

	public static Singleton_hungry getInstance() {
		return instance;
	}
}
