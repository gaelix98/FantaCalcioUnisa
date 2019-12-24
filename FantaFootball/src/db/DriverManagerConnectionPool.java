package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
	private static List<Connection> freeDbConnections;
	private static boolean isTest = false;
	
	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String username = "root";
		String password = "root";
		String db;
		if(!isTest) {
			db = "fantacalciounisa";
		} else {
			db = "fantacalciounisaTest";
		}
		
		String url = "jdbc:mysql://localhost:3306/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		newConnection = DriverManager.getConnection(url, username, password);

		//newConnection.setAutoCommit(false);
		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
	
	public static boolean isTest() {
		return isTest;
	}


	public static void setTest(boolean isTest) {
		DriverManagerConnectionPool.isTest = isTest;
	}
}
