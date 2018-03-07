package com.marshall.jcip.ch03;

/**
 * Created by yaojie.hou on 2018/3/7.
 */
public class CountingSheep {

	volatile boolean asleep;

	void tryToSleep() {
		while (!asleep) {
			countSomeSheep();
		}
	}

	void countSomeSheep() {
		// One, two, three...
	}

}
