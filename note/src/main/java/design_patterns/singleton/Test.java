package design_patterns.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by yaojie.hou on 2017/12/6.
 */
public class Test {


	public static void main(String[] args) throws Exception {

		//reflectTest();

		//enumTest();

		enumCall();
	}

	/**
	 * 利用反射打破单例
	 * @throws Exception
	 */
	public static void reflectTest() throws Exception {
		// 获得构造器
		Constructor<Singleton> con = Singleton.class.getDeclaredConstructor();
		// 设置为可以访问
		con.setAccessible(true);
		// 构造两个不同的对象
		Singleton singleton1 = con.newInstance();
		Singleton singleton2 = con.newInstance();
		// 验证是否是不同对象
		System.out.println(singleton1.equals(singleton2));
	}

	/**
	 * 使用枚举实现的单例不能用反射构造两个对象
	 * @throws Exception
	 */
	public static void enumTest() throws Exception {
		// 获得构造器
		Constructor<Singleton_enum> con = Singleton_enum.class.getDeclaredConstructor();
		// 设置为可以访问
		con.setAccessible(true);
		// 构造两个不同的对象
		Singleton_enum singleton1 = con.newInstance();
		Singleton_enum singleton2 = con.newInstance();
		// 验证是否是不同对象
		System.out.println(singleton1.equals(singleton2));
	}

	/**
	 * 调用enum实现的单例
	 */
	public static void enumCall() {
		Singleton_enum.INSTANCE.doSomething();
	}
}
