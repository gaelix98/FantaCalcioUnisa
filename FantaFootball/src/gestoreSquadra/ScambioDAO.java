package gestoreSquadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreLega.Partita;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Scambio.
 * @author Pasquale Caramante
 *
 */

public class ScambioDAO {
	Connection conn = null;
	
	public synchronized boolean creaScambio(Scambio scambio) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserito = false;
		String sql = "insert into scambio values (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, scambio.getGiocatore1().getId());
		ps.setInt(2, scambio.getGiocatore2().getId());
		ps.setInt(3, scambio.getPrezzoOfferto());
		ps.setString(4, scambio.getSquadra1().getNome());
		ps.setString(5,scambio.getSquadra2().getNome());
		ps.setString(6, scambio.getSquadra1().getLega().getNome());
		try{
			ris = ps.executeUpdate();
			if(ris == 1) {
				inserito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return inserito;
	}
	
	
	public synchronized boolean accettaScambio(Scambio scambio) throws SQLException  {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean accettato = false;
		String sql = "delete from scambio where scambio.Giocatore1 = ? and scambio.Giocatore2 = ? and scambio.Squadra1 = ? and scambio.Squadra2 = ? and scambio.lega = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, scambio.getGiocatore1().getId());
		ps.setInt(2, scambio.getGiocatore2().getId());
		ps.setString(3, scambio.getSquadra1().getNome());
		ps.setString(4,scambio.getSquadra2().getNome());
		ps.setString(5, scambio.getSquadra1().getLega().getNome());
		
		try{
			ris = ps.executeUpdate();
			if(ris == 1) {
				accettato = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return accettato;
	}
	
	
	public synchronized void rifiutaScambio(Scambio scambio) throws SQLException  {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		
		String sql = "delete from scambio where scambio.Giocatore1 = ? and scambio.Giocatore2 = ? and scambio.Squadra1 = ? and scambio.Squadra2 = ? and scambio.lega = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, scambio.getGiocatore1().getId());
		ps.setInt(2, scambio.getGiocatore2().getId());
		ps.setString(3, scambio.getSquadra1().getNome());
		ps.setString(4,scambio.getSquadra2().getNome());
		ps.setString(5, scambio.getSquadra1().getLega().getNome());
		
		try{
			ris = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
	}
	
	public synchronized ArrayList<Scambio> getScambiNonAccettatiSquadra(Squadra squadra) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Scambio> scambi=new ArrayList<Scambio>();
		String sql="select * from scambio where scambio.squadra2=?;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, squadra.getNome());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Scambio scambio=null;
			SquadraDAO squadraDAO=new SquadraDAO();
			GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
			Squadra squadra1=squadraDAO.getSquadraById(rs.getString("squadra1"), rs.getString("nomeLega"));
			Squadra squadra2=squadraDAO.getSquadraById(rs.getString("squadra2"), rs.getString("nomeLega"));
			Giocatore giocatore1 = giocatoreDAO.getGiocatoreById(rs.getInt("giocatore1"));
			Giocatore giocatore2 = giocatoreDAO.getGiocatoreById(rs.getInt("giocatore2"));
			int prezzo = rs.getInt("PrezzoOfferto");
			scambio = new Scambio(giocatore1,giocatore2,prezzo,squadra1,squadra2);
			scambi.add(scambio);
		}
		conn.close();
		return scambi;
	}
	
	
	public synchronized Scambio getScambioById(Giocatore giocatore1,Giocatore giocatore2, Squadra squadra1,Squadra squadra2) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		Scambio scambio = null;
		String sql="select * from scambio where scambio.giocatore1 = ? and scambio.giocatore2 = ? and scambio.squadra1 = ? and scambio.squadra2=? and scambio.lega= ?";
		
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, giocatore1.getId());
		ps.setInt(2, giocatore2.getId());
		ps.setString(3,squadra1.getNome());
		ps.setString(4,squadra2.getNome());
		ps.setString(5,squadra1.getLega().getNome());
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			SquadraDAO squadraDAO=new SquadraDAO();
			GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
			Squadra s1=squadraDAO.getSquadraById(rs.getString("squadra1"), rs.getString("nomeLega"));
			Squadra s2=squadraDAO.getSquadraById(rs.getString("squadra2"), rs.getString("nomeLega"));
			Giocatore g1 = giocatoreDAO.getGiocatoreById(rs.getInt("giocatore1"));
			Giocatore g2 = giocatoreDAO.getGiocatoreById(rs.getInt("giocatore2"));
			int prezzo = rs.getInt("PrezzoOfferto");
			scambio = new Scambio(g1,g2,prezzo,s1,s2);
		}
		conn.close();
		return scambio;
		
	
	}
	
}
