package design_patterns.singleton;

/**
 * Created by yaojie.hou on 2017/12/6.
 *
 * 使用静态内部类实现单例模式
 * INSTANCE对象初始化的时机并不是在单例类Singleton被加载的时候，
 * 而是在调用getInstance方法，使得静态内部类LazyHolder被加载的时候。
 * 因此这种实现方式是利用classloader的加载机制来实现懒加载，并保证构建单例的线程安全
 *
 * 但也存在可以利用反射来重复构建对象的问题
 */
public class Singleton_static {

	private Singleton_static() {}


	private static class LazyHolder {
		private static final Singleton_static instance = new Singleton_static();
	}

	public Singleton_static getInstance() {
		return LazyHolder.instance;
	}
}
