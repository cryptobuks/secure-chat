package secure;  
  
import java.io.ByteArrayOutputStream;  
import java.security.Key;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.Security;
import java.security.Signature;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.crypto.Cipher;  
  
/** *//** 
 * <p> 
 * RSA公钥/私钥/签名工具包 
 * </p> 
 * <p> 
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman） 
 * </p> 
 * <p> 
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/> 
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/> 
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全 
 * </p> 
 *  
 * @author IceWee 
 * @date 2012-4-26 
 * @version 1.0 
 */  
public class RSA {  
  
	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
      
    /** *//** 
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = 64;  
      
    /** *//** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 64;  
  
    public static KeyPair genKeys(){
    	KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);  

	        KeyPair keyPair = keyPairGen.generateKeyPair();
	        return keyPair;  
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;

        
		

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
    public static String decrypt( RSAPrivateKey privateKey,String data)  
             {  
    	byte[] cipherByte =null;                             //获得秘闻的byte数组;        
        byte[] plainByte   =null;                             //解密后的明文数组;
        String   plainStr    =null;                            //解密后的明文数组;
        Cipher   cipher      =null;                            //加密用;
    	
    	 try{
             cipherByte       =data.getBytes("UTF-8");    //统一使用该种编码方式;
             cipher =Cipher.getInstance("RSA/ECB/PKCS1Padding");
             cipher.init(Cipher.DECRYPT_MODE,privateKey);
             int inputLen = cipherByte.length;  
             ByteArrayOutputStream out = new ByteArrayOutputStream();  
             int offSet = 0;  
             byte[] cache;  
             int i = 0;  
             // 对数据分段解密  
             while (inputLen - offSet > 0) {  
                 if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                     cache = cipher.doFinal(cipherByte, offSet, MAX_DECRYPT_BLOCK);  
                 } else {  
                     cache = cipher.doFinal(cipherByte, offSet, inputLen - offSet);  
                 }  
                 out.write(cache, 0, cache.length);  
                 i++;  
                 offSet = i * MAX_DECRYPT_BLOCK;  
             }  
             plainByte = out.toByteArray();  
             out.close();  
             
             
             
             
             
             plainStr=new String(plainByte,"UTF-8"); 
             return plainStr;
             
         }catch(Exception err)
         {
             err.printStackTrace();
         }
    	 return null;
    	
    	
        
        
         
    }  
  
    /** *//** 
     * <p> 
     * 公钥解密 
     * </p> 
     *  
     * @param encryptedData 已加密数据 
     * @param publicKey 公钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static String decrypt( RSAPublicKey publicKey,String data)  
            { 
    	
    	
    	byte[] cipherByte =null;                             //获得秘闻的byte数组;        
        byte[] plainByte   =null;                             //解密后的明文数组;
        String   plainStr    =null;                            //解密后的明文数组;
        Cipher   cipher      =null;                            //加密用;
    	
    	 try{
             cipherByte       =data.getBytes("UTF-8");    //统一使用该种编码方式;
             cipher =Cipher.getInstance("RSA");
             cipher.init(Cipher.DECRYPT_MODE,publicKey);
             int inputLen = cipherByte.length;  
             ByteArrayOutputStream out = new ByteArrayOutputStream();  
             int offSet = 0;  
             byte[] cache;  
             int i = 0;  
             // 对数据分段解密  
             
             while (inputLen - offSet > 0) {  
                 if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                     cache = cipher.doFinal(cipherByte, offSet, MAX_DECRYPT_BLOCK);  
                 } else {  
                     cache = cipher.doFinal(cipherByte, offSet, inputLen - offSet);  
                 }  
                 out.write(cache, 0, cache.length);  
                 i++;  
                 offSet = i * MAX_DECRYPT_BLOCK;  
             }  
             plainByte = out.toByteArray();  
             out.close();  
             
             
             
             
             
             plainStr=new String(plainByte,"UTF-8"); 
             return plainStr;
             
         }catch(Exception err)
         {
             err.printStackTrace();
         }
    	 return null;
    }  
  
    /** *//** 
     * <p> 
     * 公钥加密 
     * </p> 
     *  
     * @param data 源数据 
     * @param publicKey 公钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static String encrypt( RSAPublicKey publicKey,String data)  
             {  
    	 
    	 String cipherStr=null;                              //返回的加密后的字符串;      
         byte[] plainByte=null;                              //获得明文的byte数组; 
         byte[] cipherByte;                                    //产生秘闻的byte数组;                          
         Cipher cipher =null;
         try{          
        	 plainByte=data.getBytes("UTF-8"); 
        	 cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        	 cipher.init(Cipher.ENCRYPT_MODE,publicKey); 
        	 int inputLen = plainByte.length;  
             ByteArrayOutputStream out = new ByteArrayOutputStream();  
             int offSet = 0;  
             byte[] cache;  
             int i = 0;  
             // 对数据分段加密  
             while (inputLen - offSet > 0) {  
                 if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                	 cache = cipher.doFinal(plainByte, offSet, MAX_ENCRYPT_BLOCK); 
                    
                 } else {  
                     cache = cipher.doFinal(plainByte, offSet, inputLen - offSet);  
                 }  
                 out.write(cache, 0, cache.length);  
                 i++;  
                 offSet = i * MAX_ENCRYPT_BLOCK;  
             }  
             cipherByte = out.toByteArray();  
             out.close();  
             cipherStr=new String(cipherByte,"UTF-8");
             return cipherStr; 
        	  
          }catch(Exception err){
             err.printStackTrace();
             System.out.println("error in en: "+err.toString());
         }
         return null;
       
    }  
  
    /** *//** 
     * <p> 
     * 私钥加密 
     * </p> 
     *  
     * @param data 源数据 
     * @param privateKey 私钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static String encrypt( RSAPrivateKey privateKey,String data)  
            {  
        
   	 String cipherStr=null;                              //返回的加密后的字符串;      
     byte[] plainByte=null;                              //获得明文的byte数组; 
     byte[] cipherByte;                                    //产生秘闻的byte数组;                          
     Cipher cipher =null;
     try{          
    	 plainByte=data.getBytes("UTF-8"); 
    	 cipher=Cipher.getInstance("RSA");
    	 cipher.init(Cipher.ENCRYPT_MODE,privateKey); 
    	 int inputLen = plainByte.length;  
         ByteArrayOutputStream out = new ByteArrayOutputStream();  
         int offSet = 0;  
         byte[] cache;  
         int i = 0;  
         // 对数据分段加密  
         while (inputLen - offSet > 0) {  
             if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
            	 cache = cipher.doFinal(plainByte, offSet, MAX_ENCRYPT_BLOCK); 
                
             } else {  
                 cache = cipher.doFinal(plainByte, offSet, inputLen - offSet);  
             }  
             out.write(cache, 0, cache.length);  
             i++;  
             offSet = i * MAX_ENCRYPT_BLOCK;  
         }  
         cipherByte = out.toByteArray();  
         out.close();  
         cipherStr=new String(cipherByte,"UTF-8");
         return cipherStr; 
    	  
      }catch(Exception err){
         err.printStackTrace();
         System.out.println("error in en: "+err.toString());
     }
     return null;
    }  
    public static void main(String [] args){
    	KeyPair ks = RSA.genKeys();
    	RSAPublicKey uk = (RSAPublicKey) ks.getPublic();
    	RSAPrivateKey rk = (RSAPrivateKey) ks.getPrivate();
    	String en = RSA.encrypt(rk, "Hello E");
    	
    	System.out.println(RSA.decrypt(uk, en));
    	
    }
  
    
  
}  