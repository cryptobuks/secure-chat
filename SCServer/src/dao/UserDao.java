package dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.interfaces.RSAPublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import secure.RSA;

import util.IO;

import conn.SCServer;

import bean.Msg;
import bean.User;

public class UserDao {
	public boolean login(String loginMsg,String username) {
		try {
			File pkFile = new File("./pks/"+username+".pk");
			  
			if (!pkFile.exists()) {
			    System.out.println("not exist "+username);
			    return false;
			}
			
			RSAPublicKey pk = (RSAPublicKey) IO.readFromFile("./pks/"+username+".pk");
			
			RSA rsa = new RSA();
			System.out.println("msg:"+loginMsg+"u:"+username);
			/*String s = rsa.decrypt(pk, loginMsg);
			if(!s.equals(username)){
				return false;
			}*/
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(Msg msg) {
		try {
			/*String sql = "insert into users (username,public_key) values(?,?)";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, msg.getSender());
			ps.setString(2, msg.getPk().toString());
			
			int r = ps.executeUpdate();
			if (r > 0) {
				return true;
			}*/
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
	
	

	public String getUser(String string){
		String res="";
		try {
			String sql = "select * from users where username='"+string+"'";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=res+rs.getInt("id")+"_"+rs.getString("username")+"_"
						+rs.getString("public_key")+"_"+rs.getInt("state");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}