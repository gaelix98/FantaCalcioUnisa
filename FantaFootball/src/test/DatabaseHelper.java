package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;

import org.apache.ibatis.jdbc.ScriptRunner;


public class DatabaseHelper {
	static Connection conn;
	public static void initializeDatabase() throws SQLException, FileNotFoundException {
		DriverManagerConnectionPool.setTest(true);
		conn = DriverManagerConnectionPool.getConnection();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader reader = new BufferedReader(new FileReader("inserimentoDatiTest.sql"));
		sr.runScript(reader);
		
		//DriverManagerConnectionPool.releaseConnection(conn);
		conn.close();
	}
}
