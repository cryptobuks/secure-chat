package conn;
import java.io.ObjectOutputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

import util.IO;

import dao.UserDao;

import bean.Msg;
import bean.Msg;

public class MsgHandler {
	static UserDao udao=new UserDao();
	
	
	public static void sendMes(Msg m){
		try{
			ServerConClientThread scc=ManageServerConClient.getClientThread(m.getReceiver());
			ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
			oos.writeObject(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void sendUK(Msg m){
		try{
			ServerConClientThread scc=ManageServerConClient.getClientThread(m.getSender());
			ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
			Msg re = new Msg();
			String requiredUKUser = m.getContent();
			RSAPublicKey pk =(RSAPublicKey) new IO().readFromFile("pks/"+requiredUKUser+".pk");
			re.setPk(pk);
			re.setReceiver(m.getSender());
			re.setSender("server");
			re.setType(Msg.SEND_UK);
			oos.writeObject(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void sendAddf(Msg m) {
		// TODO Auto-generated method stub
		try{
			ServerConClientThread scc=ManageServerConClient.getClientThread(m.getReceiver());
			ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
			Msg re = new Msg();
			String requiredUKUser = m.getContent();
			RSAPublicKey pk =(RSAPublicKey) new IO().readFromFile("pks/"+requiredUKUser+".pk");
			re.setPk(pk);
			re.setReceiver(m.getReceiver());
			re.setSender(m.getSender());
			re.setType(Msg.ADD_FRIEND);
			re.setContent(m.getContent());
			oos.writeObject(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	


	
	
}
