package secure;
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import java.security.Security;  
  
import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.KeyGenerator;  
import javax.crypto.NoSuchPaddingException;  
import javax.crypto.SecretKey;  
  
public class DES3 { 
	static{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
  
   
    private KeyGenerator keygen;  
    
    private SecretKey deskey;  
  
    private Cipher c;  
   
    private byte[] cipherByte;  
  
    public DES3() throws NoSuchAlgorithmException, NoSuchPaddingException {  
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());  
        // ʵ��֧��DES�㷨����Կ�����(�㷨��������谴�涨�������׳��쳣)  
        keygen = KeyGenerator.getInstance("DESede");  
        // �����Կ  
        deskey = keygen.generateKey();  
        // ���Cipher����,ָ����֧�ֵ�DES�㷨  
        c = Cipher.getInstance("DESede");  
    }  
  
    /** 
     * ���ַ���� 
     *  
     * @param str 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public byte[] Encrytor(String str) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        // �����Կ����Cipher������г�ʼ����ENCRYPT_MODE��ʾ����ģʽ  
        c.init(Cipher.ENCRYPT_MODE, deskey);  
        byte[] src = str.getBytes();  
        // ���ܣ������cipherByte  
        cipherByte = c.doFinal(src);  
        return cipherByte;  
    }  
  
    /** 
     * ���ַ���� 
     *  
     * @param buff 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        // �����Կ����Cipher������г�ʼ����DECRYPT_MODE��ʾ����ģʽ  
        c.init(Cipher.DECRYPT_MODE, deskey);  
        cipherByte = c.doFinal(buff);  
        return cipherByte;  
    }  
  
    /** 
     * @param args 
     * @throws NoSuchPaddingException  
     * @throws NoSuchAlgorithmException  
     * @throws BadPaddingException  
     * @throws IllegalBlockSizeException  
     * @throws InvalidKeyException  
     */  
    public static void main(String[] args) throws Exception {  
        DES3 des = new DES3();  
        String msg ="hello world";  
        byte[] encontent = des.Encrytor(msg);  
        byte[] decontent = des.Decryptor(encontent);  
        System.out.println("orig:" + msg);  
        System.out.println("en:" + new String(encontent));  
        System.out.println("de:" + new String(decontent));  
  
    }  
  
}  