package gestoreSquadra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
	 * @param giocatore giocatore da aggiungere
	 * @return true if database.giocatore->includes(select(g|g.id=giocatore.id)), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addGiocatore(Giocatore giocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserito=false;
		String sql="insert into giocatore (Nome,Cognome,Ruolo,SquadraReale,Presenze,VotoMedio,Goal,Assist,Ammonizioni,Espulsioni,"
				+ "RigoriSegnati,RigoriSbagliati,RigoriParati, prezzoBase) values (?,?,?,?,?, ?,?,?,?,?,?,?,?,?);";
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
		ps.setInt(14, giocatore.getPrezzoBase());
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
	 * @param giocatore da aggiornare
	 * @return true se il giocatore è stato aggiornato, false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean aggiornaGiocatore (Giocatore giocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato=false;
		int ris;
		String sqlUp="update giocatore set presenze=?, votoMedio=?, goal=?, assist=?, ammonizioni=?, "
				+ "espulsioni=?, rigoriSegnati=?, rigoriSbagliati=?, rigoriParati=?, squadraReale=? where id=?;";
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
		ps.setString(10, giocatore.getSquadra());
		ps.setInt(11, giocatore.getId());
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
	
	/**
	 * 
	 * @param id id del giocatore da cercare
	 * @return giocatore->select(g|giocatore.id=id)
	 * @throws SQLException
	 */
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
			int prezzoBase=rs.getInt("prezzoBase");
			giocatore=new Giocatore(id, nome, cognome, ruolo, squadra, presenze, votoMedio, goal, assist, ammonizioni, espulsioni, rigoriSegnati, rigoriSbagliati, rigoriParati, prezzoBase);
		}
		conn.close();
		return giocatore;
	}
	
	/**
	 * 
	 * @param nomeLega nome della lega di cui fa parte la squadra
	 * @param nomeSquadra nome della squadra di cui si vogliono cercare i giocatori
	 * @return
	 * @throws SQLException
	 */
	public synchronized Giocatore[] getGiocatoriBySquadra(String nomeLega,String nomeSquadra) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Giocatore[] giocatori = new Giocatore[25];
		int i = 0;
		String sql = "select * from squadragiocatore, giocatore where squadragiocatore.id=giocatore.id and"
				+ " squadragiocatore.NomeLega = ? and squadragiocatore.NomeSquadra = ?";
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
			int prezzoBase=rs.getInt("prezzoBase");
			Giocatore giocatore=new Giocatore(id, nome, cognome, ruolo, squadra, presenze, votoMedio, goal, assist, ammonizioni, espulsioni, rigoriSegnati, rigoriSbagliati, rigoriParati, prezzoBase);
			giocatori[i++] = giocatore;
		}
		return giocatori;
	}
	
	/**
	 * 
	 * @param prezzo prezzo minimo dei giocatori
	 * @return giocatori->select(g|giocatore.prezzoBase>prezzo)
	 * @throws SQLException
	 */
	public synchronized List<Giocatore> getByPrezzoBase(int prezzo)throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		List<Giocatore> giocatoriFiltrati = new ArrayList<>();
		String sql = "select * from giocatore where giocatore.prezzoBase>=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, prezzo);
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
			int prezzoBase=rs.getInt("prezzoBase");
			Giocatore giocatore=new Giocatore(id, nome, cognome, ruolo, squadra, presenze, votoMedio, goal, assist, ammonizioni, espulsioni, rigoriSegnati, rigoriSbagliati, rigoriParati, prezzoBase);
			giocatoriFiltrati.add(giocatore);
		}
		return giocatoriFiltrati;
	}
	
}
