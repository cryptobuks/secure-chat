package secure;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public abstract class Encryptos {
	//KeyGenerator 提供对称密钥生成器的功能，支持各种算法  
	private KeyGenerator keygen;  
	//SecretKey 负责保存对称密钥  
	private SecretKey deskey;  
	//Cipher负责完成加密或解密工作  
	private Cipher c;  
	//该字节数组负责保存加密的结果  
	private byte[] cipherByte;  
	
//	Security.addProvider(new com.sun.crypto.provider.SunJCE());  
//	//实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
//	keygen = KeyGenerator.getInstance("DES");//  
//	//生成密钥  
//	deskey = keygen.generateKey();  
//	//生成Cipher对象,指定其支持的DES算法  
//	c = Cipher.getInstance("DES");  

}
