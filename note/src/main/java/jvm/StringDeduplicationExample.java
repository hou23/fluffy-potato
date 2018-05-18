package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示G1 GC的字符串去重功能
 * @author yaojie.hou
 * @create 2018/5/14
 */
public class StringDeduplicationExample {

	public static List<String> myStrings = new ArrayList<>();

	/**
	 * -Xmx20M -XX:+UseG1GC -XX:+UseStringDeduplication
	 *
	 * -XX:+UseStringDeduplication 开启字符串去重功能
	 * @param args
	 */
	public static void main(String[] args) {
		for (int counter = 0; counter < 200; ++counter) {
			for (int secondCounter = 0; secondCounter < 1000; ++secondCounter) {
				// 添加1000次
				myStrings.add("Hello World-" + counter);
			}
			System.out.println("Hello World-" + counter + " 被添加了1000次");
		}
	}
}
