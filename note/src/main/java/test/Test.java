package test;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaojie.hou on 2018/3/14.
 */
public class Test {

	//String str = "a" + "b";

	public static void main(String[] args) {
		//String a = "3";
		//String b = "1";
		//System.out.println(StringUtils.isNoneBlank(a, b));
		Map<String, String> map = new HashMap<>();
		System.out.println(StringUtils.isBlank(map.get("a")));
	}
}
