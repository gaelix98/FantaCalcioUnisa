package gestoreSquadra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Offerta.
 * @author Maria Natale
 *
 */
public class OffertaDAO {
	Connection conn = null;
	
	/**
	 * 
	 * @param nomeSquadra
	 * @param dataAsta
	 * @param nomeLega
	 * @param giocatore
	 * @param somma
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean addOfferta(String nomeSquadra, Date dataAsta, String nomeLega, int giocatore, int somma) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserito=false;
		String sql="insert into offerta values (?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeSquadra);
		ps.setString(2, dataAsta.toString());
		ps.setString(3, nomeLega);
		ps.setInt(4, giocatore);
		ps.setInt(5, somma);
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
	 * @param nomeSquadra
	 * @param dataAsta
	 * @param nomeLega
	 * @param giocatore
	 * @throws SQLException
	 */
	public synchronized void deleteOfferta(String nomeSquadra, Date dataAsta, String nomeLega, int giocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		String sql="Delete from Offerta where squadra=? and dataInizio=? and nomeLega=? and giocatore=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeSquadra);
		ps.setString(2, dataAsta.toString());
		ps.setString(3, nomeLega);
		ps.setInt(4, giocatore);
		ps.execute();
		conn.close();
	}
	
	/**
	 * 
	 * @param nomeSquadra
	 * @param dataAsta
	 * @param nomeLega
	 * @param giocatore
	 * @param somma
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean updateOfferta (String nomeSquadra, Date dataAsta, String nomeLega, int giocatore, int somma) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato=false;
		int ris;
		String sqlUp="Update Offerta set somma=? where squadra=? and dataInizio=? and nomeLega=? and giocatore=? ";
		PreparedStatement ps = conn.prepareStatement(sqlUp);
		ps.setInt(1, somma);
		ps.setString(2, nomeSquadra);
		ps.setString(3, dataAsta.toString());
		ps.setString(4, nomeLega);
		ps.setInt(5, giocatore);
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
	 * @param allenatore
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Offerta> getAllOfferteByAllenatore(String allenatore) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta, squadra where offerta.squadra=squadra.nomeSquadra and squadra.allenatore=?; ";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, allenatore);
		ResultSet rs=ps.executeQuery();
		/*while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			Giocatore giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}*/
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
	public synchronized ArrayList<Offerta> getAllOfferteByAsta(Date dataInizioAsta, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta where offerta.dataInizio=? and offerta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, dataInizioAsta.toString());
		ps.setString(2, nomeLega);
		ResultSet rs=ps.executeQuery();
		/*while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			Giocatore giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}*/
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
		/*while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			Giocatore giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}*/
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
		String sql="select offerta.* from offerta, squadra where offerta.squadra=squadra.nomeSquadra and squadra.allenatore=? and offerta.dataInizio=dataInizioAsta and offerta.nomeLega=nomeLega;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, allenatore);
		ps.setString(2, dataInizioAsta.toString());
		ps.setString(3, nomeLega);
		ResultSet rs=ps.executeQuery();
		/*while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			Giocatore giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}*/
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
	public synchronized ArrayList<Offerta> getAllOfferteGiocatoreAsta(int giocatore, Date dataInizioAsta, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Offerta> offerte=new ArrayList<>();
		String sql="select offerta.* from offerta where offerta.giocatore=? and offerta.dataInizo=? and offerta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, giocatore);
		ps.setString(2, dataInizioAsta.toString());
		ps.setString(3, nomeLega);
		ResultSet rs=ps.executeQuery();
		/*while(rs.next()) {
			Offerta offerta=new Offerta();
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			Giocatore giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}*/
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
	public synchronized Offerta getOffertaGiocatoreSquadra(int giocatore, Date dataInizioAsta, String nomeLega, String nomeSquadra) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Offerta offerta=null;
		String sql="select offerta.* where offerta.giocatore=? and offerta.dataInizo=? and offerta.nomeLega=? and offerta.squadra=nomeSquadra";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, giocatore);
		ps.setString(2, dataInizioAsta.toString());
		ps.setString(3, nomeLega);
		ps.setString(4, nomeSquadra);
		ResultSet rs=ps.executeQuery();
		/*while(rs.next()) {
			AstaDAO astaDAO=new AstaDAO();
			Asta asta=astaDAO.getAstaByKey(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			SquadraDAO squadraDAO=new SquadraDAO();
			Squadra squadra=squadraDAO.getSquadraById(rs.getString("squadra"),  rs.getString("nomeLega"));
			Giocatore giocatoreDAO=new GiocatoreDAO();
			Giocatore giocatore=giocatoreDAO.getGiocatoreById(rs.getInt("giocatore"));
			offerta.setAsta(asta);
			offerta.setSquadra(squadra);
			offerta.setGiocatore(giocatore);
			offerta.setSomma(rs.getInt("somma"));
			offerte.add(offerta);
		}*/
		conn.close();
		return offerta;
	}
}
