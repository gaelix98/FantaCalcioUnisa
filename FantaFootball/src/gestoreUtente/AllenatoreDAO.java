package gestoreUtente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DriverManagerConnectionPool;


public class AllenatoreDAO {


	public List<Allenatore> getAllAllenatori() throws SQLException{
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			PreparedStatement ps=conn.prepareStatement("SELECT Nome, Cognome, email, passowrd, username FROM allenatore ");
			ArrayList<Allenatore> utenti = new ArrayList<>();
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Allenatore u=new Allenatore(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
				utenti.add(u);
			}
			return utenti;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Allenatore getAllenatoreByUsername(String username) throws SQLException{
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			Allenatore u=null;
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM allenatore where username=?");
			ps.setString(1, username);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				u=new Allenatore(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
			}
			return u;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Allenatore getAllenatoreByEmail(String email) throws SQLException{

		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			Allenatore u=null;
			PreparedStatement ps=conn.prepareStatement("SELECT Nome, Cognome, email, password, username FROM allenatore where email=?");
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				u=new Allenatore(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
			}
			return u;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public boolean addAllenatore(Allenatore allenatore) throws SQLException {
		boolean ok=false;
		try(Connection con =  DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps =con.prepareStatement("INSERT INTO allenatore(Nome,Cognome,email, password, username) VALUES(?,?,?,?,?)");
			ps.setString(1, allenatore.getNome());
			ps.setString(2, allenatore.getCognome());
			ps.setString(3, allenatore.getEmail());
			ps.setString(4, allenatore.getPassword());
			ps.setString(5, allenatore.getUsername());
			ps.execute();
			con.close();

		}catch(SQLException e) {
			e.printStackTrace();
			return ok;
		}
		ok=true;
		return ok;
	}

	public boolean deleteAllenatore(String username) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Delete from allenatore where username=?");

			ps.setString(1,username);
			ps.executeUpdate();
			con.close();}
		catch(SQLException x) {x.printStackTrace();
		return ok;

		}
		ok=true;

		return ok;
	}


	//pass e email 
	public  boolean updateAllenatore(Allenatore allenatore) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Update allenatore SET password=? and email=? where username=?");
			ps.setString(1,allenatore.getPassword());
			ps.setString(2, allenatore.getEmail());
			ps.setString(3, allenatore.getUsername());
			ps.executeUpdate();
		}
		catch(SQLException x) {
			x.printStackTrace();
			return ok;

		}
		ok=true;
		return ok;
	}




	public boolean checkLogin (String username, String password) throws SQLException {
		Connection conn = DriverManagerConnectionPool.getConnection();
		boolean login = false;
		String sql = "Select username,password from allenatore where username = ? and password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		if(ps.executeQuery().next()) {
			login = true;
		}
		return login;
	}
}



