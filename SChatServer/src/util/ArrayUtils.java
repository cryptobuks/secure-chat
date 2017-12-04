package util;

public class ArrayUtils {
	public static byte[] subArray(byte[] data,int i,int j){
		if(j>data.length){
			j=data.length;
		}
		byte[] result = new byte[j-i];
		for(int k = 0;k<j-i;k++){
			result[k]=data[i+k];
		}
		return result;
	}

}
