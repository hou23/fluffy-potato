package com.marshall.jcip.ch03;

/**
 * Created by yaojie.hou on 2018/3/9.
 */
public class ThisEscape {

	public ThisEscape(EventSource source) {
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

	interface EventListener {
		void onEvent(Event e);
	}

	interface Event {
	}

}
