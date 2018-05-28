package utils.e_car;

public class TestRsa{
	public static void main(String[] args) throws Exception {  
        String filepath="E:/rsa";  
  
        //RSAEncrypt.genKeyPair(filepath);  
          
          
        System.out.println("--------------��Կ����˽Կ���ܹ���-------------------");  
        String plainText="ihep_��Կ����˽Կ����";  
        //��Կ���ܹ���  
        byte[] cipherData=MyRSAEncrypt.encrypt(MyRSAEncrypt.loadPublicKeyByStr(MyRSAEncrypt.loadPublicKeyByFile(filepath)),plainText.getBytes());  
        String cipher=MyBase64.encode(cipherData);  
        //˽Կ���ܹ���  
        byte[] res=MyRSAEncrypt.decrypt(MyRSAEncrypt.loadPrivateKeyByStr(MyRSAEncrypt.loadPrivateKeyByFile(filepath)), MyBase64.decode(cipher));  
        String restr=new String(res);  
        System.out.println("ԭ�ģ�"+plainText);  
        System.out.println("���ܣ�"+cipher);  
        System.out.println("���ܣ�"+restr);  
        System.out.println();  
          
        System.out.println("--------------˽Կ���ܹ�Կ���ܹ���-------------------");  
        plainText="ihep_˽Կ���ܹ�Կ����";  
        //˽Կ���ܹ���  
        cipherData=MyRSAEncrypt.encrypt(MyRSAEncrypt.loadPrivateKeyByStr(MyRSAEncrypt.loadPrivateKeyByFile(filepath)),plainText.getBytes());  
        cipher=MyBase64.encode(cipherData);  
        //��Կ���ܹ���  
        res=MyRSAEncrypt.decrypt(MyRSAEncrypt.loadPublicKeyByStr(MyRSAEncrypt.loadPublicKeyByFile(filepath)), MyBase64.decode(cipher));  
        restr=new String(res);  
        System.out.println("ԭ�ģ�"+plainText);  
        System.out.println("���ܣ�"+cipher);  
        System.out.println("���ܣ�"+restr);  
        System.out.println();  
          
        System.out.println("---------------˽Կǩ������------------------");  
        String content="ihep_��������ǩ����ԭʼ����";  
        String signstr=MyRSASignature.sign(content,MyRSAEncrypt.loadPrivateKeyByFile(filepath));  
        System.out.println("ǩ��ԭ����"+content);  
        System.out.println("ǩ������"+signstr);  
        System.out.println();  
          
        System.out.println("---------------��ԿУ��ǩ��------------------");  
        System.out.println("ǩ��ԭ����"+content);  
        System.out.println("ǩ������"+signstr);  
          
        System.out.println("��ǩ�����"+MyRSASignature.doCheck(content, signstr, MyRSAEncrypt.loadPublicKeyByFile(filepath)));  
        System.out.println();  
          
    }  
}
