package test;

/**
 * @author yaojie.hou
 * @create 2018/5/30
 */
public class StringTest {

	public static void main(String[] args) {
		substring();
	}

	private static void substring() {
		//String s = "alipay_wap";
		String s = "alipay";
		System.out.println(s.split("_")[0]);
	}
}
