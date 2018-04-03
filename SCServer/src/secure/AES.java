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
  
public class AES {  
	static{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
      
   
    private KeyGenerator keygen;  
   
    private SecretKey deskey;  
   
    private Cipher c;  
   
    private byte[] cipherByte;  
      
    public AES() throws NoSuchAlgorithmException, NoSuchPaddingException{  
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());  
       
        keygen = KeyGenerator.getInstance("AES");  
       
        deskey = keygen.generateKey();  
       
        c = Cipher.getInstance("AES");  
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
    public String Encryt(String msg) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
    	
        c.init(Cipher.ENCRYPT_MODE, deskey);  
        byte[] src = msg.getBytes();  
        
        cipherByte = c.doFinal(src);  
        return new String(cipherByte);  
    }  
  
    /** 
     * 
     *  
     * @param buff 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public String Decrypt(String msg) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        byte[] buff = msg.getBytes();
        c.init(Cipher.DECRYPT_MODE, deskey);  
        cipherByte = c.doFinal(buff);  
        return new String(cipherByte);  
    }  
  
    /** 
     * @param args 
     * @throws NoSuchPaddingException  
     * @throws NoSuchAlgorithmException  
     * @throws BadPaddingException  
     * @throws IllegalBlockSizeException  
     * @throws InvalidKeyException  
     */  
   /*public static void main(String[] args) throws Exception {  
        AES de1 = new AES();  
        String msg ="��XX-��Ц����ȫ��";  
        byte[] encontent = de1.Encrytor(msg);  
        byte[] decontent = de1.Decryptor(encontent);  
        System.out.println("������:" + msg);  
        System.out.println("���ܺ�:" + new String(encontent));  
        System.out.println("���ܺ�:" + new String(decontent));  
    } */ 
  
}  