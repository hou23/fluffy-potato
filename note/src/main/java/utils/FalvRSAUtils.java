package utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class FalvRSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 生成RSA密钥对
	 *
	 * @return
	 */
	public static KeyPair generateKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 密钥位数
		keyPairGen.initialize(1024);
		// 密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		return keyPair;
	}

	/**
	 * 获得base64加密的RSA密钥对
	 *
	 * @return Map
	 *         <p>
	 *         public: base64加密的公钥字符串 private: base64加密的私钥字符串
	 *         </p>
	 * @throws Exception
	 */
	public static Map<String, String> generateKeyPairMap() throws Exception {
		KeyPair keyPair = generateKeyPair();
		Map<String, String> map = new HashMap<String, String>();
		// 公钥
		PublicKey publicKey = keyPair.getPublic();
		String base64EncodedPublicKey = base64Encode(publicKey.getEncoded());
		map.put("public", base64EncodedPublicKey);
		// 私钥
		PrivateKey privateKey = keyPair.getPrivate();
		String base64EncodedPrivateKey = base64Encode(privateKey.getEncoded());
		map.put("private", base64EncodedPrivateKey);

		return map;
	}

	/**
	 * 私钥加密
	 *
	 * @param privateKey
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static byte[] privateEncrypt(PrivateKey privateKey, byte[] content) throws Exception {
		if (privateKey == null) {
			throw new IllegalArgumentException("加密私钥为空");
		}
		byte[] output;
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		output = cipher.doFinal(content);
		return output;
	}

	/**
	 * 私钥加密
	 *
	 * @param strPrivateKey
	 * @param content
	 * @return
	 */
	public static byte[] privateEncrypt(String strPrivateKey, byte[] content) throws Exception {
		byte[] keyBytes = base64Decode(strPrivateKey);
		return privateEncrypt(getPrivateKey(keyBytes), content);
	}

	/**
	 * 私钥加密
	 *
	 * @param base64EncodedPrivateKey
	 * @param content
	 * @return
	 */
	public static String privateEncrypt(String strPrivateKey, String content) throws Exception {
		byte[] keyBytes = base64Decode(strPrivateKey);
		byte[] encryptData = privateEncrypt(getPrivateKey(keyBytes), content.getBytes("UTF-8"));
		return base64Encode(encryptData);
	}

	/**
	 * 公钥加密
	 *
	 * @param publicKey
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static byte[] publicEncrypt(PublicKey publicKey, byte[] content) throws Exception {
		if (publicKey == null) {
			throw new IllegalArgumentException("加密公钥为空");
		}
		byte[] output;
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		output = cipher.doFinal(content);
		return output;
	}

	/**
	 * 公钥加密
	 *
	 * @param strPublicKey
	 * @param content
	 * @return
	 */
	public static byte[] publicEncrypt(String strPublicKey, byte[] content) throws Exception {
		byte[] keyBytes = base64Decode(strPublicKey);
		return publicEncrypt(getPublicKey(keyBytes), content);
	}

	/**
	 * 公钥加密
	 *
	 * @param strPublicKey
	 * @param content
	 * @return
	 */
	public static String publicEncrypt(String strPublicKey, String content) throws Exception {
		byte[] keyBytes = base64Decode(strPublicKey);
		byte[] encryptData = publicEncrypt(getPublicKey(keyBytes), content.getBytes("UTF-8"));
		return base64Encode(encryptData);
	}

	/**
	 * 私钥解密
	 *
	 * @param privateKey
	 * @param encryptData
	 * @return
	 */
	public static byte[] privateDecrypt(PrivateKey privateKey, byte[] encryptData) throws Exception {
		if (privateKey == null) {
			throw new IllegalArgumentException("解密私钥为空");
		}
		byte[] output;
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		output = cipher.doFinal(encryptData);
		return output;
	}

    /** *//**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = base64Decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

	/**
	 * 私钥解密
	 *
	 * @param strPrivateKey
	 * @param encryptData
	 * @return
	 */
	public static byte[] privateDecrypt(String strPrivateKey, byte[] encryptData) throws Exception {
		byte[] keyBytes = base64Decode(strPrivateKey);
		return privateDecrypt(getPrivateKey(keyBytes), encryptData);
	}

	/**
	 * 私钥解密
	 *
	 * @param strPrivateKey
	 * @param encryptData
	 * @return
	 */
	public static String privateDecrypt(String strPrivateKey, String encryptData) throws Exception {
		byte[] keyBytes = base64Decode(strPrivateKey);
		byte[] content = privateDecrypt(getPrivateKey(keyBytes), base64Decode(encryptData));
		return new String(content, "UTF-8");
	}

	public static String privateDecryptPKCS1(String strPrivateKey, String encryptData) throws Exception {
		byte[] keyBytes = base64Decode(strPrivateKey);

		byte[] data = base64Decode(encryptData);
		PrivateKey privateKey = getPrivateKey(keyBytes);
		if (privateKey == null) {
			throw new IllegalArgumentException("解密私钥为空");
		}
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding ");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] output = cipher.doFinal(data);

		return new String(output, "UTF-8");
	}

	/**
	 * 公钥加密
	 *
	 * @param publicKey
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static byte[] publicDecrypt(PublicKey publicKey, byte[] encryptData) throws Exception {
		if (publicKey == null) {
			throw new IllegalArgumentException("解密公钥为空");
		}
		byte[] output;
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		output = cipher.doFinal(encryptData);
		return output;
	}

	/**
	 * 公钥加密
	 *
	 * @param strPublicKey
	 * @param content
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] publicDecrypt(String strPublicKey, byte[] encryptData) throws Exception {
		byte[] keyBytes = base64Decode(strPublicKey);
		return publicDecrypt(getPublicKey(keyBytes), encryptData);
	}

	/**
	 * 公钥加密
	 *
	 * @param strPublicKey
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String publicDecrypt(String strPublicKey, String encryptData) throws Exception {
			byte[] keyBytes = base64Decode(strPublicKey);
			byte[] data = publicDecrypt(getPublicKey(keyBytes), base64Decode(encryptData));
			return new String(data, "UTF-8");
	}

	private static PublicKey getPublicKey(byte[] keyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	private static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}




	private static String base64Encode(byte[] data) {
		if (data == null) {
			return "";
		}
		return Base64.encodeBase64String(data);
	}

	private static byte[] base64Decode(String base64String) throws IOException {
		if (base64String == null) {
			return null;
		}
		return Base64.decodeBase64(base64String);
	}

	public static String sign(String content, String privateKey)
	{
		try
		{
			PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( utils.Base64.decode(privateKey) );
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			Signature signature = Signature.getInstance("RSA");
			signature.initSign(priKey);
			signature.update( content.getBytes());
			byte[] signed = signature.sign();
			return utils.Base64.encode(signed);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static boolean verify(String content, String sign, String publicKey)
	{
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = utils.Base64.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


			Signature signature = Signature
					.getInstance("SHA1WithRSA");

			signature.initVerify(pubKey);
			signature.update( content.getBytes() );

			boolean bverify = signature.verify( utils.Base64.decode(sign) );
			return bverify;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public static void main(String[] args) throws Exception {

		//jd();
		qixin();
	}

	/**
	 * 京东-叮咚音箱
	 * @throws Exception
	 */
	private static void jd() throws Exception {
		String content = "{\"openId\":\"jd-20180409174615861WIZsm\", \"command\":\"123123\", \"requestId\":\"20422\"}";
		String pri = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANSYQW5MuKLdas+XH4Xqq7/5uVX0UBi0C+eKXJ1C6/Tu1+0YiZmTGp1avTR20XolH+/jYXpTPbBgwVJlCJ5tqcYyx//1zalp9WqmNm0owcZfl4id7HamMVnyIi7hMn2NSgqU3ZfDnQCgD2vdUslVRfhgNUycZsJ9zocRUVR3K+kDAgMBAAECgYBRN51/kuXSqn4csmJDmeRlYfTRBpX26gnGOhCpr6BPIVPyazZeKlm3lavEuEPMwxQRZC+gON7PXBde7+Q8Ci8aK4yShD6BF92mhDPyh4yROQ1HgqKmNzM+7/CFmu20+KFR/8dj/j8b5SLpSrglOOyC+0vbgtRlq6aleZHdDkfcgQJBAO8ZTSrTzAc9S/U88TAc6B9RzIMO7XK2LFifvyOqHtmxhqRHG+RVGVCmuYlsdNVgdEoVLgufsWkgAkJaL3Zq+0MCQQDjn1cJa0kpg4JQTc0e90CTyLMJTsHmR1jMPZjcNEA/mhVl0nm6iri7wO7NJuaU4XUo+1UROSvO35Dxc08WRx9BAkEAttHy1j9yL0roTJEXnoFL377NEJ0WZHL1P6KOJTgMNMpwOCaDJjkHjUqebXy3bPw+jvLY7Vxi7A3kjFWnGvW8PwJANAyZkHPR9QY3ZDmGMBCF2cVI+1XhDNE/wuRK8f+YuXJ4diIc58UU3QoPESZWD7FGYyqllxylodaJhEzDxupYgQJAWvKTTKaoFnxjWVsKQT94hmsEUiVgcYoLBxlRrm8C4eiUA75AWJg7Iy4thtrMoxJ4K/eDHp+IkL4wQrDCkDM5BA==";
		String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUmEFuTLii3WrPlx+F6qu/+blV9FAYtAvnilydQuv07tftGImZkxqdWr00dtF6JR/v42F6Uz2wYMFSZQiebanGMsf/9c2pafVqpjZtKMHGX5eInex2pjFZ8iIu4TJ9jUoKlN2Xw50AoA9r3VLJVUX4YDVMnGbCfc6HEVFUdyvpAwIDAQAB";

		// 私钥加密
		String encrypt = privateEncrypt(pri, content);
		System.out.println(URLEncoder.encode(encrypt, "utf-8"));
	}

	/**
	 * 启信宝
	 * @throws Exception
	 */
	private static void qixin() throws Exception {
		String content = "{\"mch_id\":\"20180428101102783ZJNGP\"}";
		String encryptContent = "BLCMQ72bNeaNlV6sgUP37A47DxM0UAJ+Qz+tRpDI0FO+hj9YgmsKg2kHWTZnj+RZgOjgVajGG3Azw63B7Nu9+uJkMbOe+Fd7tmAd23XLc15VuDOPElVGpA8SJ2MXzx9bHbOzSEqvrauZtk3ZlEu/QGXXCUiyL4ibzO1dK1hk/XM=";
		String pri = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJh/NS8YQWNeIG/Gu/2bsILHJA+iZyRgo9DvoHgt5IBkSXgn2nuQsRXu7SFdYm7xIdtPjHoRpwt9uoDINCo9DpGdv0hpHW7LjolvcLnNjhdJ8n40w4H6kIbPLbTfDnACidi48dTuGIX/Ka0QDgmEniGDX92WrJZvydMWV/P9ZsaBAgMBAAECgYBiswqIFanUl4pk7fqkdQz0R9GmMNWS7N16EG4K+qEHHGGU3C6DhS2oZYCi9J8N8RGZsU3Zf+1e+ZwpV+qzX9s6DSEdXj+v5KFgvTNpH3VZfm4VcjNAcc5Sce/C832Qt4ThLIpcr/J34FPbEd14LwLxwpY6Dx3n0lO30/uDFJxHxQJBAPHLf34Q5mB0/+3JPmtbGKf0bJczhDe6gwFb2uyIL8yDlpgKZO9YEY6JeajjmRLaBvasRknZjoAne2loJkWC1p8CQQChdLPEqfzFc+0DZP62fdnZTwVwxrWmmSELJLFaMJ7ZrjYARJK25PUewiKN18Xxng1rarXu68tVhOOi5eAHWu7fAkEAosJ74I1ZtiYrvJGRncxlTXFfw2vfHXBEDR8av8tweXc+YLvmc6JRtyPdLA2AlcsDkvomGkIDBUsObMa5tGK9OwJAZ0iEh1ZGwPk7sWQ2HP+9EGqgjFhRc9WZcctzOMLJm276VImJYT5uH9MIz7wsgDgfVYvZ/pEQbhConlh3S707+QJAMR7Y5RpxlFDgRPcDf0yjgE9nJDxJsqBvcRkQ4DhCodayauLS4y7yD+dinNCPvfO6Vn1YI8EDD73tOXmptIthUg==";
		String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYfzUvGEFjXiBvxrv9m7CCxyQPomckYKPQ76B4LeSAZEl4J9p7kLEV7u0hXWJu8SHbT4x6EacLfbqAyDQqPQ6Rnb9IaR1uy46Jb3C5zY4XSfJ+NMOB+pCGzy203w5wAonYuPHU7hiF/ymtEA4JhJ4hg1/dlqyWb8nTFlfz/WbGgQIDAQAB";

		// 公钥加密
		byte[] encryptData = publicEncrypt(pub, content.getBytes("UTF-8"));
		System.out.println("加密串:" + base64Encode(encryptData));

		// 私钥解密
		//byte[] decryptData = privateDecrypt(pri, encryptData);
		//byte[] decryptData = privateDecrypt(pri, base64Decode(encryptContent));
		String s = privateDecrypt(pri, encryptContent);
		System.out.println(s);
		//System.out.println("解密串:" + new String(decryptData, "UTF-8"));
	}
}
