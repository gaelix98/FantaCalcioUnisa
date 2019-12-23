package gestoreUtente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Allenatore.
 * @author Pasquale Caramante
 *
 */

import db.DriverManagerConnectionPool;

/**
 * Questa classe rappresenta l'entità Allenatore.

 * @author Pasquale Caramante
 */

public class AllenatoreDAO {
	Connection conn = null;
	
	public synchronized boolean addAllenatore( Allenatore allenatore ) throws SQLException{
		conn= DriverManagerConnectionPool.getConnection();
		boolean inserito = false;
		int ris;
		
		String sql = "Insert into allenatore values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, allenatore.getNome());
		ps.setString(2, allenatore.getCognome());
		ps.setString(3, allenatore.getEmail());
		ps.setString(4, allenatore.getPassword());
		ps.setString(5, allenatore.getUsername());
		
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
		String sql = "Select username,password from allenatore where allenatore.username = ? and allenatore.password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		if(ps.executeQuery().next()) {
			login = true;
		}
		return login;
	}
	
	public synchronized boolean deleteAllenatore(String username) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		boolean eliminato = false;
		int ris;
		String sql = "delete from allenatore where allenatore.username = ?";
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
	
	public synchronized boolean updateAllenatore(Allenatore allenatore) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato = false;
		int ris;
		String sql = "update allenatore set allenatore.Nome = ?,allenatore.Cognome = ?, allenatore.email = ?, allenatore.password= ?,allenatore.username = ? where allenatore.username = ?" ;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,allenatore.getNome());
		ps.setString(2, allenatore.getCognome());
		ps.setString(3, allenatore.getEmail());
		ps.setString(4, allenatore.getPassword());
		ps.setString(5, allenatore.getUsername());
		
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
	public synchronized ArrayList<Allenatore> getAllAllenatori() throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Allenatore> allenatori = new ArrayList<Allenatore>();
		String sql = "select * from allenatore";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Allenatore allenatore = null;
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String username = rs.getString("username");
			
			allenatore = new Allenatore(nome,cognome,email,username,password);
			allenatori.add(allenatore);
		}
		return allenatori;
		
	}
	public synchronized Allenatore getAllenatoreByUsername(String username) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		Allenatore allenatore = null;
		String sql = "select * from allenatore where allenatore.username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String usernamet = rs.getString("username");
			allenatore = new Allenatore(nome,cognome,email,usernamet,password);
		}
		return allenatore;
	}

}
