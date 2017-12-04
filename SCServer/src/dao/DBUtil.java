package dao;
import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;
public class DBUtil {
	private static Connection connection = null;
	private static Connection connectionMed = null;
	private static String user = "security";
	private static String password = "security";
	private static String con = "jdbc:oracle:thin:@localhost:1521:oracle";
	private static String db_driver="oracle.jdbc.driver.OracleDriver";
	private static DBUtil dbutil;
	private DBUtil(){
		
	}
	public synchronized static DBUtil getDBUtil(){
		if(dbutil==null){
			dbutil=new DBUtil();
		}
		
		return dbutil;
	}

	
		

	
	public Connection getConnection(){
		try {
			Class.forName(db_driver);
			Connection conn=DriverManager.getConnection(con,user,password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void closeConnection(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



