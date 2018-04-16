package test;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by yaojie.hou on 2018/3/14.
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(RandomStringUtils.randomAlphabetic(6));
		System.out.println(RandomStringUtils.randomAlphanumeric(6));
		System.out.println(RandomStringUtils.randomAscii(6));
	}
}
