import javax.crypto.Cipher;      
import javax.crypto.SecretKey;      
import javax.crypto.SecretKeyFactory;      
import javax.crypto.spec.DESKeySpec;      
import javax.crypto.spec.IvParameterSpec;     
  
/*  
   * DES + base64  
   */  
public class Encrypt {        
  
public static void main(String args[]) throws Exception{   
   //System.out.println(EncryptAsDoNet("1100603024816_2010-6-3_12","26451d8f"));   
     
   System.out.println(DecryptDoNet("tnluSzYe4WhPtYaabWeV8I06GOnANQFq1Mr/z9LIeEo=","26451d8f"));   
     
}   
public static String DecryptDoNet(String message, String key) throws Exception {      
    //base64 + des 解密.net 加密传来串   
      //byte[] bytesrc = convertHexString(message); //不用base64 的方式   
		BASE64Decoder base64Decoder = new BASE64Decoder();   
      byte[] bytesrc=base64Decoder.decodeBuffer(message);   
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");      
      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GB2312"));      
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
      IvParameterSpec iv = new IvParameterSpec(key.getBytes("GB2312"));      
      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);      
      byte[] retByte = cipher.doFinal(bytesrc);      
         
      return new String(retByte);      
}      
public static String EncryptAsDoNet(String message, String key) throws Exception {    
   //产生与.net 对应的加密des + base64 加密串   
      //message = java.net.URLEncoder.encode(message, "ascii");      
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");      
      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GB2312"));      
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
      IvParameterSpec iv = new IvParameterSpec(key.getBytes("GB2312"));      
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);      
      byte[] encryptbyte = cipher.doFinal(message.getBytes());      
      BASE64Encoder base64Encoder = new BASE64Encoder();   
      //base64Encoder.encode(encryptbyte);   
      return base64Encoder.encode(encryptbyte);   
      //toHexString(encryptbyte).toUpperCase();      
           
}     
} 