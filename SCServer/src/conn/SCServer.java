package conn;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Date;

import util.IO;

import dao.UserDao;

import bean.User;
import bean.Msg;
import bean.Msg;

public class SCServer {
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Msg> msgToSend = new ArrayList<Msg>();
	public SCServer(){
		ServerSocket ss = null;
		try {
			ss=new ServerSocket(5470);
			System.out.println("server start on "+new Date());
			while(true){
				Socket s=ss.accept();
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				
				Msg msg=(Msg) ois.readObject();
				System.out.println("rec:"+msg.getContent());
				Msg m=new Msg();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
		        if(msg.getType().equals(Msg.LOGIN)){
		        	String username=msg.getSender();
		        	UserDao udao=new UserDao();
		        	boolean b=udao.login(msg.getContent(),username);
					if(b){
						//System.out.println("["+username+"] online");态
						User u  =new User();
						u.setUsername(username);
						RSAPublicKey pk = (RSAPublicKey) IO.readFromFile("./pks/"+username+".pk");
						u.setPublic_key(pk);
						this.users.add(u);
						
						m.setType(Msg.SUCCESS);
						m.setContent(u.getUsername());
						oos.writeObject(m);
						ServerConClientThread cct=new ServerConClientThread(s);
						ManageServerConClient.addClientThread(msg.getSender(),cct);
						cct.start();
					}else{
						m.setType(Msg.FAIL);
						oos.writeObject(m);
					}
		        }else if(msg.getType().equals(Msg.REGISTER)){
		        	System.out.println("register:"+msg.getSender());
		        	UserDao udao=new UserDao();
		        	if(udao.register(msg)){
						m.setType(Msg.SUCCESS);
						oos.writeObject(m);
		        	}
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) {
		new SCServer();

	}

}
