package utils;

import java.net.URLEncoder;

/**
 * Created by ming on 17-3-18.
 */
public class RSATest {

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUmEFuTLii3WrPlx+F6qu/+blV9FAYtAvnilydQuv07tftGImZkxqdWr00dtF6JR/v42F6Uz2wYMFSZQiebanGMsf/9c2pafVqpjZtKMHGX5eInex2pjFZ8iIu4TJ9jUoKlN2Xw50AoA9r3VLJVUX4YDVMnGbCfc6HEVFUdyvpAwIDAQAB";
    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANSYQW5MuKLdas+XH4Xqq7/5uVX0UBi0C+eKXJ1C6/Tu1+0YiZmTGp1avTR20XolH+/jYXpTPbBgwVJlCJ5tqcYyx//1zalp9WqmNm0owcZfl4id7HamMVnyIi7hMn2NSgqU3ZfDnQCgD2vdUslVRfhgNUycZsJ9zocRUVR3K+kDAgMBAAECgYBRN51/kuXSqn4csmJDmeRlYfTRBpX26gnGOhCpr6BPIVPyazZeKlm3lavEuEPMwxQRZC+gON7PXBde7+Q8Ci8aK4yShD6BF92mhDPyh4yROQ1HgqKmNzM+7/CFmu20+KFR/8dj/j8b5SLpSrglOOyC+0vbgtRlq6aleZHdDkfcgQJBAO8ZTSrTzAc9S/U88TAc6B9RzIMO7XK2LFifvyOqHtmxhqRHG+RVGVCmuYlsdNVgdEoVLgufsWkgAkJaL3Zq+0MCQQDjn1cJa0kpg4JQTc0e90CTyLMJTsHmR1jMPZjcNEA/mhVl0nm6iri7wO7NJuaU4XUo+1UROSvO35Dxc08WRx9BAkEAttHy1j9yL0roTJEXnoFL377NEJ0WZHL1P6KOJTgMNMpwOCaDJjkHjUqebXy3bPw+jvLY7Vxi7A3kjFWnGvW8PwJANAyZkHPR9QY3ZDmGMBCF2cVI+1XhDNE/wuRK8f+YuXJ4diIc58UU3QoPESZWD7FGYyqllxylodaJhEzDxupYgQJAWvKTTKaoFnxjWVsKQT94hmsEUiVgcYoLBxlRrm8C4eiUA75AWJg7Iy4thtrMoxJ4K/eDHp+IkL4wQrDCkDM5BA==";

    public static void main(String[] args) throws Exception {
        test();
//        testSign();
//        System.out.println(privateKey.equals(privateKey1));
    }

    static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        System.err.println("公钥:" + publicKey);
        System.err.println("私钥：" + privateKey);
        String source = "{\"openId\":\"jd-20180417110005833AXoQR\"}";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("加密后文字：\r\n" + URLEncoder.encode(new String(encodedData), "utf-8"));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        System.out.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
//        System.err.println("签名:\r" + sign);
        System.out.println("签名:" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
//        System.err.println("验证结果:\r" + status);
        System.out.println("验证结果:" + status);
    }
}

