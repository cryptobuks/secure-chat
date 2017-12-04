package dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.interfaces.RSAPublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.crypto.Cipher;


import secure.RSA;
import util.ArrayUtils;
import util.IO;

import conn.SCServer;

import bean.Msg;
import bean.User;

public class UserDao {
	public boolean login(byte[] loginMsg,String username) {
		try {
			File pkFile = new File("./pks/"+username+".pk");
			  
			if (!pkFile.exists()) {
			    System.out.println("not exist "+username);
			    return false;
			}
			
			String pk = (String) IO.readFromFile("./pks/"+username+".pk");
			
			
			System.out.println("msg:"+loginMsg+"u:"+username);
			String s = new String(RSA.decryptByPublicKey(loginMsg, pk));
			if(!s.equals(username)){
				System.out.println(s);
				return false;
			}
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(Msg msg) {
		try {
			
			
			FileOutputStream fos = new FileOutputStream("./pks/"+msg.getSender()+".pk");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(msg.getPk());
			oos.close();
			fos.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	


	
	
}