package java_concurrency_in_practice.ch03;

/**
 * Created by yaojie.hou on 2018/3/12.
 *
 * 使用工厂方法防止this引用在构造期间逸出
 */
public class SafeListener {
	private final EventListener listener;

	/**
	 * 如果想在构造函数中注册监听器或启动线程, 可以使用一个私有的构造函数
	 * 和一个公共的工厂方法进行创建
	 */
	private SafeListener() {
		listener = new EventListener() {
			@Override
			public void onEvent(Event e) {
				doSomething(e);
			}
		};
	}

	public static SafeListener newInstance(EventSource source) {
		SafeListener safe = new SafeListener();
		source.registerListener(safe.listener);
		return safe;
	}

	void doSomething(Event e) {
	}


	interface EventSource {
		void registerListener(EventListener e);
	}

	interface EventListener {
		void onEvent(Event e);
	}

	interface Event {
	}
}
