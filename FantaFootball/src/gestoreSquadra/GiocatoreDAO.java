package gestoreSquadra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreLega.Asta;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Giocatore.
 * @author Maria Natale
 *
 */
public class GiocatoreDAO {
	Connection conn = null;
	
	/**
	 * 
	 * @param giocatore
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean addGiocatore(Giocatore giocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserito=false;
		String sql="insert into asta values (?,?,?,?,?, ?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, giocatore.getNome());
		ps.setString(2, giocatore.getCognome());
		ps.setString(3, giocatore.getRuolo());
		ps.setString(4, giocatore.getSquadra());
		ps.setInt(5, giocatore.getPresenze());
		ps.setFloat(6, giocatore.getVotoMedio());
		ps.setInt(7, giocatore.getGoal());
		ps.setInt(8, giocatore.getAssist());
		ps.setInt(9, giocatore.getAmmonizioni());
		ps.setInt(10, giocatore.getEspulsioni());
		ps.setInt(11, giocatore.getRigoriSegnati());
		ps.setInt(12, giocatore.getRigoriSbagliati());
		ps.setInt(13, giocatore.getRigoriParati());
		try {
			ris=ps.executeUpdate();
			if (ris==1)
				inserito=true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return inserito;
	}
	
	/**
	 * 
	 * @param giocatore
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean aggiornaGiocatore (Giocatore giocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato=false;
		int ris;
		String sqlUp="update giocatore set presenze=?, votoMedio=?, goal=?, assist=?, ammonizioni=?, "
				+ "espulsioni=?, rigoriSegnati=?, rigoriSbagliati=?, rigoriParati=? where id=?;";
		PreparedStatement ps = conn.prepareStatement(sqlUp);
		ps.setInt(1, giocatore.getPresenze());
		ps.setFloat(2, giocatore.getVotoMedio());
		ps.setInt(3, giocatore.getGoal());
		ps.setInt(4, giocatore.getAssist());
		ps.setInt(5, giocatore.getAmmonizioni());
		ps.setInt(6, giocatore.getEspulsioni());
		ps.setInt(7, giocatore.getRigoriSegnati());
		ps.setInt(8, giocatore.getRigoriSbagliati());
		ps.setInt(9, giocatore.getRigoriParati());
		ps.setInt(10, giocatore.getId());
		try {
			ris=ps.executeUpdate();
			if (ris==1)
				modificato=true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return modificato;
	}
	
	
	public synchronized Giocatore getGiocatoreById(int id) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Giocatore giocatore=null;
		String sql="select * from giocatore where giocatore.id=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String nome=rs.getString("nome");
			String cognome=rs.getString("cognome");
			String ruolo=rs.getString("ruolo");
			String squadra=rs.getString("squadraReale");
			int presenze=rs.getInt("presenze");
			float votoMedio=rs.getFloat("votoMedio");
			int goal=rs.getInt("goal");
			int assist=rs.getInt("assist");
			int ammonizioni=rs.getInt("ammonizioni");
			int espulsioni=rs.getInt("espulsioni");
			int rigoriSegnati=rs.getInt("rigoriSegnati");
			int rigoriSbagliati=rs.getInt("rigoriSbagliati");
			int rigoriParati=rs.getInt("rigoriParati");
			giocatore=new Giocatore(id, nome, cognome, ruolo, squadra, presenze, votoMedio, goal, assist, ammonizioni, espulsioni, rigoriSegnati, rigoriSbagliati, rigoriParati);
		}
		conn.close();
		return giocatore;
	}
	
	public synchronized Giocatore[] getGiocatoriBySquadra(String nomeLega,String nomeSquadra) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Giocatore[] giocatori = new Giocatore[25];
		int i = 0;
		String sql = "select * from squadragiocatore where squadragiocatore.NomeLega = ? and squadragiocatore.NomeSquadra = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeLega);
		ps.setString(2, nomeSquadra);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String nome=rs.getString("nome");
			String cognome=rs.getString("cognome");
			String ruolo=rs.getString("ruolo");
			String squadra=rs.getString("squadraReale");
			int presenze=rs.getInt("presenze");
			float votoMedio=rs.getFloat("votoMedio");
			int goal=rs.getInt("goal");
			int assist=rs.getInt("assist");
			int ammonizioni=rs.getInt("ammonizioni");
			int espulsioni=rs.getInt("espulsioni");
			int rigoriSegnati=rs.getInt("rigoriSegnati");
			int rigoriSbagliati=rs.getInt("rigoriSbagliati");
			int rigoriParati=rs.getInt("rigoriParati");
			Giocatore giocatore=new Giocatore(id, nome, cognome, ruolo, squadra, presenze, votoMedio, goal, assist, ammonizioni, espulsioni, rigoriSegnati, rigoriSbagliati, rigoriParati);
			giocatori[i++] = giocatore;
		}
		return giocatori;
	}
	
}
