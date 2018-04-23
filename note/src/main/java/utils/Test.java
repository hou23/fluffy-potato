package utils;

/**
 * @author yaojie.hou
 * @create 2018/4/18
 */
public class Test {

	static String privateKey = "";

	public static void main(String[] args) throws Exception {
		String data = "{\"openId\":\"jd-20180417110005833AXoQR\"}";
		byte[] bytes = data.getBytes();
		byte[] b = RSAUtils.encryptByPrivateKey(bytes, privateKey);
		System.out.println(new String(b));
	}


}
