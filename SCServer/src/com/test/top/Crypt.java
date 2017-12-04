package com.test.top;   
  
import javax.crypto.Cipher;   
import javax.crypto.SecretKey;   
import javax.crypto.SecretKeyFactory;   
import javax.crypto.spec.DESKeySpec;   

import secure.Base64Utils;

  
public class Crypt {   
    // --------------------------------------------------------------------------------------------   
    // 获得密钥   
    public SecretKey getKey(String s) throws Exception {   
        System.out.println("s==" + s);   
        char[] ss = s.toCharArray();   
        String sss = "";   
        for (int i = 0; i < ss.length; i = i + 2) {   
            sss = sss + ss[i];   
        }   
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");   
        DESKeySpec ks = new DESKeySpec(sss.substring(0, 8).getBytes());   
        SecretKey kd = kf.generateSecret(ks);   
        return kd;   
    }   
  
    // --------------------------------------------------------------------------------------------------   
    // 返回加密后的字符串   
    // key是用于生成密钥的字符串，input是要加密的字符串   
    public String getEncryptedString(String key, String input) {   
        String base64 = "";   
        try {   
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");   
            cipher.init(Cipher.ENCRYPT_MODE, getKey(key));   
            System.out.println("getKey(key)===" + getKey(key) + "key==" + key);   
            byte[] inputBytes = input.getBytes("UTF8");   
            byte[] outputBytes = cipher.doFinal(inputBytes);   
            
            base64 = Base64Utils.encode(outputBytes);   
        } catch (Exception e) {   
            base64 = e.getMessage();   
        }   
        return base64;   
    }   
  
    // --------------------------------------------------------------------------------------------------   
    // 返回解密后的字符串   
    // key是用于生成密钥的字符串，input是要解密的字符串   
    public String getDecryptedString(String key, String input) {   
        String result = null;   
        try {   
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");   
            cipher.init(Cipher.DECRYPT_MODE, getKey(key));   
          
            byte[] raw = Base64Utils.decode(input);   
            byte[] stringBytes = cipher.doFinal(raw);   
            result = new String(stringBytes, "UTF8");   
        } catch (Exception e) {   
            result = e.getMessage();   
        }   
        return result;   
    }   
  
    public static void main(String[] args) {   
        Crypt mycrypt = new Crypt();   
        try {   
        	long i = (long)(Math.random()*10000000000000000d);
        	
        	String k=(i+"");
        	System.out.println(k.length());
        	//.substring(0, 15);
            // SecretKey skey = mycrypt.getKey("g8TlgLEc6oqZxdwGe6pDiKB8Y");   
            String ss = mycrypt.getEncryptedString(k, "asdfeeEEEE1231");   
            System.out.println("ss==" + ss);   
            String ss2 = mycrypt.getDecryptedString(k, ss);   
            System.out.println("ss2==" + ss2);   
        } catch (Exception e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
}   