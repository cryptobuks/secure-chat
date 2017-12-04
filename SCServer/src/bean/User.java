package bean;

import java.security.interfaces.RSAPublicKey;

public class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String operation;
	private int ID;
	private String username;
	private RSAPublicKey public_key;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RSAPublicKey getPublic_key() {
		return public_key;
	}
	public void setPublic_key(RSAPublicKey public_key) {
		this.public_key = public_key;
	}

	
	
}
