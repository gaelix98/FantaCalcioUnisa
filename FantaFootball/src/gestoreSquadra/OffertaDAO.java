package gestoreSquadra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreLega.Asta;
import gestoreLega.AstaDAO;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Offerta.
 * @author Maria Natale
 *
 */
public class OffertaDAO {
	Connection conn = null;
	
	/**
	 * 
	 * @param offerta
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean addOfferta(Offerta offerta) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserito=false;
		String sql="insert into offerta values (?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, offerta.getSquadra().getNome());
		ps.setString(2, offerta.getAsta().getDataInizio().toString());
		ps.setString(3, offerta.getAsta().getLega().getNome());
		ps.setInt(4, offerta.getGiocatore().getId());
		ps.setInt(5, offerta.getSomma());
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
	
	/***
	 * 
	 * @param offerta
	 * @throws SQLException
	 */
	public synchronized void deleteOfferta(Offerta offerta) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		String sql="Delete from Offerta where squadra=? and dataInizio=? and nomeLega=? and giocatore=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, offerta.getSquadra().getNome());
		ps.setString(2, offerta.getAsta().getDataInizio().toString());
		ps.setString(3, offerta.getAsta().getLega().getNome());
		ps.setInt(4, offerta.getGiocatore().getId());
		ps.execute();
		conn.close();
	}
	
	/**
	 * 
	 * @param offerta
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean updateOfferta (Offerta offerta) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato=false;
		int ris;
		String sqlUp="Update Offerta set somma=? where squadra=? and dataInizio=? and nomeLega=? and giocatore=? ";
		PreparedStatement ps = conn.prepareStatement(sqlUp);
		ps.setInt(1, offerta.getSomma());
		ps.setString(2, offerta.getSquadra().getNome());
		ps.setString(3, offerta.getAsta().getDataInizio().toString());
		ps.setString(4, offerta.getAsta().getLega().getNome());
		ps.setInt(5, offerta.getGiocatore().getId());
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
	 * @param dataInizioAsta
	 * @param nomeLega
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Offerta> getAllOfferteByAsta(Date dataInizioAsta, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta where offerta.dataInizio=? and offerta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, dataInizioAsta.toString());
		ps.setString(2, nomeLega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}
		conn.close();
		return offerte;
	}
	
	/**
	 * 
	 * @param dataInizioAsta
	 * @param nomeLega
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Offerta> getOfferteVincentiByAsta(Date dataInizioAsta, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select * from offerta where offerta.dataInizio=? and offerta.nomeLega=? and (offerta.giocatore,offerta.somma) IN (select giocatore,max(somma) from offerta group by giocatore)";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, dataInizioAsta.toString());
		ps.setString(2, nomeLega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}
		conn.close();
		return offerte;
	}
	
	/**
	 * 
	 * @param nomeSquadra
	 * @param nomeLega
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Offerta> getAllOfferteBySquadra(String nomeSquadra, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta where offerta.squadra=? and offerta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, nomeSquadra);
		ps.setString(2, nomeLega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}
		conn.close();
		return offerte;
	}
	
	/**
	 * 
	 * @param dataInizioAsta
	 * @param nomeLega
	 * @param allenatore
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Offerta> getAllOfferteByAstaAllenatore(Date dataInizioAsta, String nomeLega, String allenatore) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta, squadra where offerta.squadra=squadra.nomeSquadra and squadra.allenatore=? and offerta.dataInizio=? and offerta.nomeLega=?;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, allenatore);
		ps.setString(2, dataInizioAsta.toString());
		ps.setString(3, nomeLega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}
		conn.close();
		return offerte;
	}
	
	/**
	 * 
	 * @param giocatore
	 * @param dataInizioAsta
	 * @param nomeLega
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Offerta> getAllOfferteGiocatoreAsta(int giocatoreId, Date dataInizioAsta, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta where offerta.giocatore=? and offerta.dataInizio=? and offerta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, giocatoreId);
		ps.setString(2, dataInizioAsta.toString());
		ps.setString(3, nomeLega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}
		conn.close();
		return offerte;
	}
	
	/**
	 * 
	 * @param giocatore
	 * @param dataInizioAsta
	 * @param nomeLega
	 * @param nomeSquadra
	 * @return
	 * @throws SQLException
	 */
	public synchronized Offerta getOffertaByKey(int giocatoreId, Date dataInizioAsta, String nomeLega, String nomeSquadra) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		String sql="select offerta.* from offerta where offerta.giocatore=? and offerta.dataInizio=? and offerta.nomeLega=? and offerta.squadra=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, giocatoreId);
		ps.setString(2, dataInizioAsta.toString());
		ps.setString(3, nomeLega);
		ps.setString(4, nomeSquadra);
		Offerta offerta=null;
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
		}
		conn.close();
		return offerta;
	}
}
