package bean;
import java.io.Serializable;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;



public class Msg implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4031248836296165070L;
	public static final String SUCCESS="1";
	public static final String FAIL="2";
	public static final String CHATMSG="3";
	public static final String GET_ONLINE_FRIENDS="4";
	public static final String RET_ONLINE_FRIENDS="5";
	public static final String LOGIN="7";
	public static final String ADD_FRIEND="8";
	public static final String REQUIRE_UK="9";
	public static final String SEND_UK="10";
	public static final String GEN_K="11";	
	public static final String REGISTER="0";
	String type;
	String sender;
	String receiver;
	String content;
	String sendTime;
	RSAPublicKey pk ;
	static{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	public RSAPublicKey getPk() {
		return pk;
	}
	public void setPk(RSAPublicKey pk) {
		this.pk = pk;
	}

	
	
}
