package Utils;

import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionBD {

	private static java.sql.Connection connection = null;

	 
	public static ConnectionBD connect = new ConnectionBD();
	
	private ConnectionBD(){
		super();
	}

	private void setConnection(String serveraddres, String database, String user,String pass) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + serveraddres + ":5432/"+ database;
		connection = DriverManager.getConnection(url, user, pass);
	}

	public java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection == null){
			setConnection("localhost","soleranew","postgres","postgres");
		}
		return connection;
	}

	
}
