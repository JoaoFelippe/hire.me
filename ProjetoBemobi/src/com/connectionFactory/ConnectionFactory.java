package com.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	Connection conn = null;

	private String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10233967";
	private String user = "sql10233967";
	private String password = "ndbsNGuknJ";

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mysql://JoaoDB:3306/joaodb", "root",
			// "redlab7");
			
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
