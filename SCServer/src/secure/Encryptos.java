package secure;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public abstract class Encryptos {
	//KeyGenerator �ṩ�Գ���Կ�������Ĺ��ܣ�֧�ָ����㷨  
	private KeyGenerator keygen;  
	//SecretKey ���𱣴�Գ���Կ  
	private SecretKey deskey;  
	//Cipher������ɼ��ܻ���ܹ���  
	private Cipher c;  
	//���ֽ����鸺�𱣴���ܵĽ��  
	private byte[] cipherByte;  
	
//	Security.addProvider(new com.sun.crypto.provider.SunJCE());  
//	//ʵ����֧��DES�㷨����Կ������(�㷨���������谴�涨�������׳��쳣)  
//	keygen = KeyGenerator.getInstance("DES");//  
//	//������Կ  
//	deskey = keygen.generateKey();  
//	//����Cipher����,ָ����֧�ֵ�DES�㷨  
//	c = Cipher.getInstance("DES");  

}
