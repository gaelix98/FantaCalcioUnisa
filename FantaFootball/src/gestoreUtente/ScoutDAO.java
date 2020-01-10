package gestoreUtente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import db.DriverManagerConnectionPool;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Scout.
 * @author Pasquale Caramante
 *
 */

public class ScoutDAO {
	Connection conn = null;
	
	public synchronized boolean addScout( Scout scout ) throws SQLException{
		conn= DriverManagerConnectionPool.getConnection();
		boolean inserito = false;
		int ris;
		
		String sql = "Insert into scout values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, scout.getNome());
		ps.setString(2, scout.getCognome());
		ps.setString(3, scout.getEmail());
		ps.setString(4, scout.getPassword());
		ps.setString(5, scout.getUsername());
		
		try {
			ris = ps.executeUpdate();
			if(ris ==  1) {
				inserito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return inserito;
	}
	
	public synchronized boolean checkLogin (String username ,String password) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		boolean login = false;
		String sql = "Select username,pass from scout where scout.username = ? and scout.pass = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		if(ps.executeQuery().next()) {
			login = true;
		}
		return login;
	}
	
	public synchronized boolean deleteScout(String username) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		boolean eliminato = false;
		int ris;
		String sql = "delete from scout where scout.username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,username);
		
		try {
			ris = ps.executeUpdate();
			if(ris == 1){
				eliminato = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminato;		
	}
	
	public synchronized boolean updateScout(Scout scout) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato = false;
		int ris;
		String sql = "update scout set scout.Nome = ?,scout.Cognome = ?, scout.email = ?, scout.pass= ? where scout.username = ?" ;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,scout.getNome());
		ps.setString(2, scout.getCognome());
		ps.setString(3, scout.getEmail());
		ps.setString(4, scout.getPassword());
		ps.setString(5, scout.getUsername());
		
		try {
			ris = ps.executeUpdate();
			if(ris == 1){
				modificato = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return modificato;		
	}
	public synchronized ArrayList<Scout> getAllScout() throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Scout> scoutList = new ArrayList<Scout>();
		String sql = "select * from scout";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Scout scout = null;
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			String email = rs.getString("email");
			String password = rs.getString("pass");
			String username = rs.getString("username");
			
			scout = new Scout(nome,cognome,email,username,password);
			scoutList.add(scout);
		}
		return scoutList;
		
	}
	public synchronized Scout getScoutByUsername(String username) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		Scout scout = null;
		String sql = "select * from scout where scout.username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			String email = rs.getString("email");
			String password = rs.getString("pass");
			String usernamet = rs.getString("username");
			scout = new Scout(nome,cognome,email,usernamet,password);
		}
		return scout;
	}

	public synchronized Scout getScoutByEmail(String email) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		Scout scout = null;
		String sql = "select * from scout where scout.email = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,email);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			String password = rs.getString("pass");
			String usernamet = rs.getString("username");
			scout = new Scout(nome,cognome,email,usernamet,password);
		}
		return scout;
	}
}
