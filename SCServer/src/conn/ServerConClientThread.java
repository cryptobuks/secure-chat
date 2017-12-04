package conn;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.Socket;



import bean.Msg;


public class ServerConClientThread extends Thread {
	Socket s;
	public ServerConClientThread(Socket s){
		this.s=s;
	}

	public void run() {
		while(true){
			ObjectInputStream ois = null;
			Msg m = null;
			try {
				ois=new ObjectInputStream(s.getInputStream());
				m=(Msg) ois.readObject();
				if(m.getType().equals(Msg.CHATMSG)){
					MsgHandler.sendMes(m);
				}else if(m.getType().equals(Msg.REQUIRE_UK)){
					MsgHandler.sendUK(m);
				}else if(m.getType().equals(Msg.ADD_FRIEND)){
					MsgHandler.sendAddf(m);
				}else if(m.getType().equals(Msg.GEN_K)){
					MsgHandler.sendMes(m);
				}
			} catch (Exception e) {
				try {
					if(s!=null){
						s.close();
					}
					if(ois!=null){
						ois.close();
					}
				} catch (IOException e1) {	
				}
				e.printStackTrace();
			}
		}
	}
}
