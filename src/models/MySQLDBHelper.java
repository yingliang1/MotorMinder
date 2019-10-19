package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MySQLDBHelper {
	private static MySQLDBHelper factory;
	private MySQLDBHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading JDBC driver");
			e.printStackTrace();

		}
	}
	public static MySQLDBHelper getInstance() {
		if (factory == null) {
			factory = new MySQLDBHelper();
		}
		
		return factory;
	}
	public Connection databaseconnect() {
		
		String url=null;
		String user=null;
		String password=null;
		url="jdbc:mysql://den1.mysql1.gear.host:3306/motorminder";
		user="motorminder";
		password="To6?~q78zPZg";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.err.println("Unable to connect database");
			e.printStackTrace();
		}
		return connection;
	}
}
