package java_concurrency_in_practice.ch03;

/**
 * Created by yaojie.hou on 2018/3/9.
 *
 * 隐式地允许this引用逸出(错误)
 * 正确示例见{@link SafeListener}
 */
public class ThisEscape {

	public ThisEscape(EventSource source) {
		// 将EventListener发布出去, 从而ThisEscape.this引用也被发布了出去,
		// 这种做法是错误的. 构造函数内不能同时出现初始化内部类和发布内部类的代码.
		source.registerListener(new EventListener() {
			@Override
			public void onEvent(Event e) {
				doSomething(e);
			}
		});
	}

	void doSomething(Event e) {
	}


	interface EventSource {
		void registerListener(EventListener e);
	}

	/**
	 * 内部类(EventListener), 会自动持有外部类(ThisEscape)的this引用
	 */
	interface EventListener {
		void onEvent(Event e);
	}

	interface Event {
	}

}
