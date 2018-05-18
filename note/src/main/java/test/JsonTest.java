package test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yaojie.hou
 * @create 2018/5/17
 */
public class JsonTest {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		JSONObject json = new JSONObject() {{
			put("a", 1);
			put("b", "2");
			put("c", false);
		}};

		System.out.println(json.getIntValue("b"));
	}
}
