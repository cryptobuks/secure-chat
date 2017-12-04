
package conn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ManageServerConClient {
	public static HashMap hm=new HashMap<Integer,ServerConClientThread>();
	

	public static void addClientThread(String string, ServerConClientThread cc){
		hm.put(string,cc);
	}

	public static ServerConClientThread getClientThread(String string){
		return (ServerConClientThread)hm.get(string);
	}

	public static List getAllOnLineUserid(){
		List list=new ArrayList();

		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			list.add((Integer) it.next());
		}
		return list;
	}
	
	public static boolean isOnline(int a){
		List list=new ArrayList();

		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			int account= (Integer) it.next();
			if(account==a){
				return true;
			}
		}
		return false;
	}
}
