package com.marshall.Java8InAction.utils;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by yaojie.hou on 2017/4/27.
 */
public class Utils {
	public static void getDuration(String msg, Supplier<List<String>> s) {
		long start = System.nanoTime();
		System.out.println(s.get());
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(msg + " done in " + duration + " msecs");
	}
}
