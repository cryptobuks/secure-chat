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
  
public class DES {  
	  
    public static SecretKey genKey(){
    	 try {
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			SecretKey deskey = keygen.generateKey(); 
			return deskey;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
    	 
    }
    
      
   
    public static byte[] encryt(byte[] src,SecretKey deskey) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {  
        Cipher c = Cipher.getInstance("DES");  
        c.init(Cipher.ENCRYPT_MODE, deskey);  
       
       
        byte[] cipherByte = c.doFinal(src);  
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
    public static byte[] decryt(byte[] src,SecretKey deskey) throws InvalidKeyException,  
    IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {  
    	Cipher c = Cipher.getInstance("DES");  
    	c.init(Cipher.DECRYPT_MODE, deskey);  


    	byte[] cipherByte = c.doFinal(src);  
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
        DES de1 = new DES();  
        String msg ="qqqqqqqq";  
        SecretKey sk = DES.genKey();
        
        //System.out.println(sk.);
        byte[] encontent = de1.encryt(msg.getBytes(),sk);  
        byte[] decontent = de1.decryt(encontent,sk);  
        System.out.println("������:" + msg);  
        System.out.println("���ܺ�:" + new String(encontent));  
        System.out.println("���ܺ�:" + new String(decontent));  
    } 
  
}  