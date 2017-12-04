package com.test.top;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;




public class Main {
    
    private static String DES3_Cipher_Algorithm = "DESede/ECB/PKCS5Padding";
    
    private static String DES3_Key_Algorithm = "DESede";
    
    private static String AES_Cipher_Algorithm = "AES/ECB/PKCS5Padding";

    private static String AES_Key_Algorithm = "AES";
    
    private static String DES3_Key = "112233445566112233445566";
    
    private static String AES_Key = "1122334411223344";
    
    
    private static byte[] des3_encrypt(byte[] keyBytes, byte[] srcBytes){
        
        try {
            Cipher cipher = Cipher.getInstance(DES3_Cipher_Algorithm);
            Key secretKey = new SecretKeySpec(keyBytes,DES3_Key_Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(srcBytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private static  byte[] des3_decrypt(byte[] keyBytes, byte[] srcBytes){
        
        try {
            Cipher cipher = Cipher.getInstance(DES3_Cipher_Algorithm);
            Key secretKey = new SecretKeySpec(keyBytes,DES3_Key_Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(srcBytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private static byte[] aes_encrypt(byte[] keyBytes, byte[] srcBytes){
        
        try {
            Cipher cipher = Cipher.getInstance(AES_Cipher_Algorithm);
            Key secretKey = new SecretKeySpec(keyBytes,AES_Key_Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(srcBytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private static byte[] aes_decrypt(byte[] keyBytes, byte[] srcBytes){
        
        try {
            Cipher cipher = Cipher.getInstance(AES_Cipher_Algorithm);
            Key secretKey = new SecretKeySpec(keyBytes,AES_Key_Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(srcBytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private static void test_des3() throws UnsupportedEncodingException{
        String srcStr = "des3测试数据11";
        byte[] srcBytes = srcStr.getBytes("UTF-8");
        System.out.println("srcBytes len = " + srcBytes.length);
        byte[] des3_keyBytes = DES3_Key.getBytes("UTF-8");
        System.out.println("des3_keyBytes len = " + des3_keyBytes.length);
        byte[] encryptedBytes = des3_encrypt(des3_keyBytes, srcBytes) ;
        System.out.println("encryptedStr = " + new String(encryptedBytes, "UTF-8"));
        System.out.println("encryptedBytes len = " + encryptedBytes.length);
        byte[] decryptedBytes = des3_decrypt(des3_keyBytes, encryptedBytes) ;
        System.out.println("decryptedStr = " + new String(decryptedBytes, "UTF-8"));
    }
    
    private static void test_aes() throws UnsupportedEncodingException{
        String srcStr = "aes测试数据22";
        byte[] srcBytes = srcStr.getBytes("UTF-8");
        System.out.println("srcBytes len = " + srcBytes.length);
        byte[] aes_keyBytes = AES_Key.getBytes("UTF-8");
        System.out.println("aes_keyBytes len = " + aes_keyBytes.length);
        byte[] encryptedBytes = aes_encrypt(aes_keyBytes, srcBytes) ;
        System.out.println("encryptedStr = " + new String(encryptedBytes, "UTF-8"));
        System.out.println("encryptedBytes len = " + encryptedBytes.length);
        byte[] decryptedBytes = aes_decrypt(aes_keyBytes, encryptedBytes) ;
        System.out.println("decryptedStr = " + new String(decryptedBytes, "UTF-8"));
    }
    
    private static void test_md5() throws Exception{
        String srcStr = "md5测试数据33";
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(srcStr.getBytes("UTF-8"));
        byte[] destBytes = digest.digest();
        String destHexString = bytes2hex(destBytes);
        System.out.println("destHexString + " + destHexString);
    }
    
    private static String bytes2hex(byte[] bytes) {
        int len = bytes.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            String temp = Integer.toHexString(bytes[i]);
            switch (temp.length()) {
            case 0:
            case 1:
                temp = "0" + temp;
                break;
            default:
                temp = temp.substring(temp.length() - 2);
                break;
            }
            sb.append(temp);
        }
        return sb.toString().toUpperCase();

    }
    
    public static void main(String[] args) throws Exception{
        test_aes();
        System.out.println("---------------------------------------------------");
        test_des3();
        System.out.println("---------------------------------------------------");
        test_md5();
    }
}