package bean;
import java.io.Serializable;

public class Data implements Serializable{

	public byte[] bytes;
	public Data(byte[] bytes){
		this.bytes = bytes;
	}
}