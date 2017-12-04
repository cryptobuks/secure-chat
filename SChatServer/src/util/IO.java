package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IO {
	/**
	* ��������ļ�
	*
	* @param obj
	* @param fileName
	* @throws Exception
	*/
	public static void saveFile(Object obj, String fileName) throws Exception {
		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(fileName));
		output.writeObject(obj);
		output.close();
		
	}
	/**
	*
	*
	* @param fileName
	* @return
	* @throws Exception
	*/
	public static Object readFromFile(String fileName) throws Exception {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
		Object obj = input.readObject();
		input.close();
		return obj;
	}

}
