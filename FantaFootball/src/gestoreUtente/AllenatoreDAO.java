package gestoreUtente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DriverManagerConnectionPool;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Allenatore.
 * @author 
 *
 */
public class AllenatoreDAO {

	/**
	 * @return
	 * @throws SQLException
	 */
	public synchronized List<Allenatore> getAllAllenatori() throws SQLException{
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			PreparedStatement ps=conn.prepareStatement("SELECT Nome, Cognome, email, password, username FROM allenatore ");
			ArrayList<Allenatore> utenti = new ArrayList<>();
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Allenatore u=new Allenatore(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
				utenti.add(u);
			}
			conn.close();
			return utenti;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param username username di un allenatore che si vuole cercare
	 * @return allenatore->select(a|allenatore.username=username))
	 * @throws SQLException
	 */
	public synchronized Allenatore getAllenatoreByUsername(String username) throws SQLException{
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			Allenatore u=null;
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM allenatore where username=?");
			ps.setString(1, username);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				u=new Allenatore(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
			}
			conn.close();
			return u;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param email email allenatore da cercare
	 * @return allenatore->select(a|allenatore.email=email))
	 * @throws SQLException
	 */
	public synchronized Allenatore getAllenatoreByEmail(String email) throws SQLException{

		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			Allenatore u=null;
			PreparedStatement ps=conn.prepareStatement("SELECT Nome, Cognome, email, password, username FROM allenatore where email=?");
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				u=new Allenatore(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
			}
			conn.close();
			return u;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param allenatore allenatore da registrare
	 * @return true if database.allenatore->includes(select(a|allenatore.username=allenatore.getUsername())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addAllenatore(Allenatore allenatore) throws SQLException {
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

	/**
	 * 
	 * @param username username dell'allenatore da rimuovere
	 * @return true if database.allenatore-> not includes(select(a|allenatore.username=username)), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean deleteAllenatore(String username) throws SQLException {
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

	/**
	 * 
	 * @param allenatore allenatore da aggiornare
	 * @return true se i dati sono stati aggiornati, false altrimenti
	 * @throws SQLException
	 */
	//pass e email 
	public synchronized boolean updateAllenatore(Allenatore allenatore) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Update allenatore SET password=?, email=? where username=?");
			ps.setString(1,allenatore.getPassword());
			ps.setString(2, allenatore.getEmail());
			ps.setString(3, allenatore.getUsername());
			ps.executeUpdate();
			con.close();
		}
		catch(SQLException x) {
			x.printStackTrace();
			return ok;

		}
		ok=true;
		return ok;
	}



	/**
	 * 
	 * @param username username dell'allenatore che vuole accedere al sistema
	 * @param password password dell'allenatore che vuole accedere al sistema
	 * @return true if database.allenatore->includes (select(a|allenatore.username=username and allenatore.password=password)), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean checkLogin (String username, String password) throws SQLException {
		Connection conn = DriverManagerConnectionPool.getConnection();
		boolean login = false;
		String sql = "Select username,password from allenatore where username = ? and password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		if(ps.executeQuery().next()) {
			login = true;
		}
		conn.close();
		return login;
	}
}



