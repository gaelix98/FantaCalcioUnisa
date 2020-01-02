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
	 * @param partita
	 * @return
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
		conn.close();
		return inserito; 
	}
	
	/**
	 * 
	 * @param squadra1
	 * @param squadra2
	 * @param giornata
	 * @return
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
		conn.close();
		return partita;
	}
	
	/**
	 * 
	 * @param partita
	 * @return
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
		conn.close();
		return modificato;
	}
	
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
		conn.close();
		return partite;
	}
	
	/**
	 * 
	 * @param squadra
	 * @return
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
		conn.close();
		return partite;
	}
	
	/**
	 * 
	 * @param giornata
	 * @param lega
	 * @return
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
		conn.close();
		return partite;
	}
}
