package bean;

import java.security.interfaces.RSAPublicKey;

public class User implements java.io.Serializable{


	private String username;
	private String public_key;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	
	
}
