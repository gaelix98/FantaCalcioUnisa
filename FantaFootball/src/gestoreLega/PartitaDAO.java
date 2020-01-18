package gestoreLega;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreSquadra.Giocatore;
import gestoreSquadra.Offerta;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Partita.
 * @author Maria Natale
 *
 */
public class PartitaDAO {
	Connection conn = null;
	
	/**
	 * 
	 * @param partita partita da aggiungere
	 * @return true if database.partita->includes(select(p|partita.nomeSquadra1=partita.getSquadra1.getNome() and 
	 * partita.nomSquadra2=partita.getSquadra2().getNome() and partita.giornata=partita.getGiornata() and 
	 * partita.nomeLega=partita.getSquadra1().getLega().getNome() and partita.nomeLega= partita.getSquadra2().getLega().getNome())),
	 * else altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addPartita(Partita partita) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris; 
		boolean inserito=false;
		String sql="insert into partita (squadra1, squadra2, nomeLega, giornata) values (?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, partita.getSquadra1().getNome());
		ps.setString(2, partita.getSquadra2().getNome());
		ps.setString(3, partita.getSquadra1().getLega().getNome());
		ps.setInt(4, partita.getGiornata());
		try {
			ris=ps.executeUpdate();
			if (ris==1)
				inserito=true;
		}
		catch(SQLException ex) { 
			ex.printStackTrace();
		}
		//conn.close();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(conn);
		return inserito; 
	}
	
	/**
	 * 
	 * @param squadra1 
	 * @param squadra2 
	 * @param giornata giornata in cui viene giocata la partita
	 * @return partita->select(p|partita.squadra1=squadra1.getNome() and partita.squadra2=squadra2.getNome() and 
	 * partita.giornata=giornata and partita.nomeLega=squadra1.getNomeLega() or partita.nomeLega=squadra2.getNome())
	 * @throws SQLException
	 */
	public synchronized Partita getPartitaById(Squadra squadra1, Squadra squadra2, int giornata) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Partita partita=null;
		String sql="select * from partita where partita.squadra1=? and partita.squadra2=? and partita.giornata=? and partita.nomeLega=?;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, squadra1.getNome());
		ps.setString(2, squadra2.getNome());
		ps.setInt(3, giornata);
		ps.setString(4, squadra1.getLega().getNome());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			int goal1=rs.getInt("goal1");
			int goal2=rs.getInt("goal2");
			partita=new Partita(squadra1, squadra2, giornata, goal1, goal2);
		}
		//conn.close();
		ps.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return partita;
	}
	
	/**
	 * 
	 * @param partita partita di cui aggiornare il risultato
	 * @return true se la partita è stata aggiornata, false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean updatePartita(Partita partita) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato=false;
		int ris;
		String sqlUp="Update Partita set goal1=?, goal2=? where partita.squadra1=? and partita.squadra2=? and partita.giornata=? and partita.nomeLega=?; ";
		PreparedStatement ps = conn.prepareStatement(sqlUp);
		ps.setInt(1, partita.getGoalSquadra1());
		ps.setInt(2, partita.getGoalSquadra2());
		ps.setString(3, partita.getSquadra1().getNome());
		ps.setString(4, partita.getSquadra2().getNome());
		ps.setString(6, partita.getSquadra1().getLega().getNome());
		ps.setInt(5, partita.getGiornata());
		try {
			ris=ps.executeUpdate();
			if (ris==1)
				modificato=true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		ps.close();
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return modificato;
	}
	
	/**
	 * 
	 * @param lega lega di cui cercare il calendario delle partite
	 * @return partite->select(p|partita.squadra1.nomeLega=lega)
	 * @throws SQLException
	 */
	public synchronized ArrayList<Partita> getAllPartiteLega(String lega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Partita> partite=new ArrayList<>();
		String sql="select * from partita where partita.nomeLega=?;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, lega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Partita partita=null;
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra1=squadraDAO.getSquadraById(rs.getString("squadra1"), rs.getString("nomeLega"));
			Squadra squadra2=squadraDAO.getSquadraById(rs.getString("squadra2"), rs.getString("nomeLega"));
			int giornata=rs.getInt("giornata");
			int goal1=rs.getInt("goal1");
			int goal2=rs.getInt("goal2");
			partita=new Partita(squadra1, squadra2, giornata, goal1, goal2);
			partite.add(partita);
		}
		ps.close();
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return partite;
	}
	
	/**
	 * 
	 * @param squadra squadra di cui cercare le partite
	 * @return partite-> select(p|partita.nomeSquadra1=squadra.getNome() and partita.nomeLega=squadra.getLega().getNome() or
	 *  partita.nomeSquadra2=squadra.getNome())
	 * @throws SQLException
	 */
	public synchronized ArrayList<Partita> getAllPartiteSquadra(Squadra squadra) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Partita> partite=new ArrayList<>();
		String sql="select * from partita where partita.squadra1=? or partita.squadra2=? and partita.nomeLega=?;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, squadra.getNome());
		ps.setString(2, squadra.getNome());
		ps.setString(3, squadra.getLega().getNome());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Partita partita=null;
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra1=squadraDAO.getSquadraById(rs.getString("squadra1"), rs.getString("nomeLega"));
			Squadra squadra2=squadraDAO.getSquadraById(rs.getString("squadra2"), rs.getString("nomeLega"));
			int giornata=rs.getInt("giornata");
			int goal1=rs.getInt("goal1");
			int goal2=rs.getInt("goal2");
			partita=new Partita(squadra1, squadra2, giornata, goal1, goal2);
			partite.add(partita);
		}
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return partite;
	}
	
	/**
	 * 
	 * @param giornata giornata di cui si vogliono cercare le partite
	 * @param lega nome della lega di cui cercare le partite
	 * @return partite->select(p|partita.giornata=giornata and partita.nomeLega=lega)
	 * @throws SQLException
	 */
	public synchronized ArrayList<Partita> getAllPartiteByGiornataLega(int giornata, String lega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Partita> partite=new ArrayList<>();
		String sql="select * from partita where partita.giornata=? and partita.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, giornata);
		ps.setString(2, lega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Partita partita=null;
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra1=squadraDAO.getSquadraById(rs.getString("squadra1"), rs.getString("nomeLega"));
			Squadra squadra2=squadraDAO.getSquadraById(rs.getString("squadra2"), rs.getString("nomeLega"));
			int goal1=rs.getInt("goal1");
			int goal2=rs.getInt("goal2");
			partita=new Partita(squadra1, squadra2, giornata, goal1, goal2);
			partite.add(partita);
		}
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return partite;
	}
}
