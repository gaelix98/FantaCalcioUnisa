package gestoreSquadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;
/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Squadra.
 * @author Pasquale Caramante
 *
 */
public class SquadraDAO {
	Connection conn;
	
	/**
	 * 
	 * @param squadra squadra da aggiungere
	 * @return true if database.squadra->includes(select(s|squadra.nome=squadra.getNome() and squadra.nomeLega=squadra.getLega.getNome())),
	 * false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean creaSquadra(Squadra squadra) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserita = false;
		String sql = "Insert into squadra values (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, squadra.getNome());
		ps.setString(2, squadra.getLega().getNome());
		ps.setString(3, squadra.getLogo());
		ps.setString(4, squadra.getAllenatore().getUsername());
		ps.setInt(5, squadra.getPunti());
		ps.setInt(6, squadra.getBudgetRimanente());
		
		try {
			ris = ps.executeUpdate();
			if(ris==1) {
				inserita = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return inserita;	
	}
	
	/**
	 * 
	 * @param squadra squadra da aggiornare
	 * @return true se i dati della squadra sono stati aggiornati, false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean updateSquadra(Squadra squadra) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato=false;
		int ris;
		String sqlUp="Update Squadra set budgetRimanente=? where nomeSquadra=? and lega=? ";
		PreparedStatement ps = conn.prepareStatement(sqlUp);
		ps.setInt(1, squadra.getBudgetRimanente());
		ps.setString(2, squadra.getNome());
		ps.setString(3, squadra.getLega().getNome());
		try {
			ris=ps.executeUpdate();
			if (ris==1)
				modificato=true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		//conn.close();
		DriverManagerConnectionPool.releaseConnection(conn);
		ps.close();
		return modificato;
	}
	
	/**
	 * 
	 * @param nomeSquadra nome della squadra 
	 * @param nomeLega nome della lega di cui fa parte la squadra
	 * @param idGiocatore id del giocatore da aggiungere alla squadra
	 * @return true if database.squadraGiocatore->includes(select(x|squadraGiocatore.giocatore=giocatore and squadraGiocatore.squadra=nomeSquadra 
	 * and squadraGiocatore.nomeLega=nomeLega)), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addGiocatoreSquadra(String nomeSquadra, String nomeLega, int idGiocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris;
		boolean inserito = false;
		String sql = "Insert into squadragiocatore values (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeSquadra);
		ps.setString(2, nomeLega);
		ps.setInt(3,idGiocatore);
		try {
			ris = ps.executeUpdate();
			if( ris == 1) {
				inserito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DriverManagerConnectionPool.releaseConnection(conn);
		ps.close();
		//conn.close();
		return inserito;	
	}
	
	/**
	 * 
	 * @param nomeSquadra nome della squadra 
	 * @param nomeLega nome della lega di cui fa parte la squadra
	 * @param idGiocatore id del giocatore da rimuovere
	 * @throws SQLException
	 */
	public synchronized void deleteGiocatoreSquadra(String nomeSquadra,String nomeLega, int idGiocatore) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		
		String sql = "delete from squadragiocatore where squadragiocatore.NomeSquadra = ? and squadragiocatore.NomeLega = ? and squadragiocatore.Id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeSquadra);
		ps.setString(2, nomeLega);
		ps.setInt(3,idGiocatore);
		
		try {
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DriverManagerConnectionPool.releaseConnection(conn);
		ps.close();
	}
	
	/**
	 * 
	 * @param allenatore username dell'allenatore di cui si vogliono cercare le squadre
	 * @return squadre->select(s|squadra.allenatore=allenatore)
	 * @throws SQLException
	 */
	public  synchronized ArrayList<Squadra> getSquadreByAllenatore(String allenatore) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Squadra> squadre = new ArrayList<Squadra>();
		String sql = "select * from squadra where squadra.allenatore = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, allenatore);		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Squadra squadra = getSquadraById(rs.getString("NomeSquadra"),rs.getString("Lega"));
			if (squadra!=null)
				squadre.add(squadra);
		}
		DriverManagerConnectionPool.releaseConnection(conn);
		ps.close();
		return squadre;
	}
	
	/**
	 * 
	 * @param nomeSquadra nome della squadra da cercare
	 * @param nomeLega nome della lega di cui fa parte la squadra
	 * @return squadre->select(s|squadra.nome=nome and squadra.nomeLega=lega)
	 * @throws SQLException
	 */
	public synchronized Squadra getSquadraById(String nomeSquadra, String nomeLega) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		Squadra squadra = null;
		String sql = "select * from squadra where squadra.nomeSquadra = ? and squadra.lega = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeSquadra);
		ps.setString(2, nomeLega);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			LegaDAO legaDAO = new LegaDAO();
			AllenatoreDAO allenatoreDAO = new AllenatoreDAO();
			GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
			String nome = rs.getString("NomeSquadra");
			String logo = rs.getString("Logo");
			Lega lega = legaDAO.getLegaByNome(rs.getString("Lega"));
			Allenatore allenatoreobj = allenatoreDAO.getAllenatoreByUsername(rs.getString("Allenatore"));
			Giocatore[] giocatori = giocatoreDAO.getGiocatoriBySquadra(nomeSquadra,lega.getNome());
			int punti = rs.getInt("Punti");
			int budget = rs.getInt("BudgetRimanente");
			squadra = new Squadra(nome,logo,allenatoreobj,lega,punti,budget);
			squadra.setGiocatori(giocatori);
		}
		//conn.close();
		ps.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return squadra;
	}
	
	/**
	 * 
	 * @param nomeLega nome della lega di cui si vogliono cercare le squadre
	 * @return squadre->select(s|squadra.lega=lega)
	 * @throws SQLException
	 */
	public synchronized ArrayList<Squadra> getSquadreByLega(String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Squadra> squadre = new ArrayList<Squadra>();
		String sql = "select * from squadra where squadra.Lega = ? order by squadra.punti desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeLega);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Squadra squadra = getSquadraById(rs.getString("NomeSquadra"),rs.getString("Lega"));
			if (squadra!=null)
				squadre.add(squadra);
		}
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return squadre;
	}
	
	/**
	 * 
	 * @param giocatore giocatore di cui si vogliono cercare le squadre
	 * @return squadre->select(s|squadragiocatore.giocatore=giocatore.id)
	 * @throws SQLException
	 */
	public synchronized ArrayList<Squadra> getSquadreGiocatore(Giocatore giocatore) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Squadra> squadre = new ArrayList<Squadra>();
		String sql = "select * from squadra,squadragiocatore where squadragiocatore.Id = ? and squadragiocatore.NomeSquadra = squadra.NomeSquadra and squadragiocatore.NomeLega = squadra.Lega";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, giocatore.getId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Squadra squadra = getSquadraById(rs.getString("NomeSquadra"),rs.getString("Lega"));
			if (squadra!=null)
				squadre.add(squadra);
		}
		
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return squadre;
	}
	
	/**
	 * 
	 * @param User allenatore della squadra
	 * @param nomeLega nome della lega di cui fa parte la squadra 
	 * @return squadra->select(s|squadra.allenatore=user and squadra.lega=nomeLega) 
	 * @throws SQLException
	 */
	public synchronized Squadra getSquadraByUserELega(String User, String nomeLega) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		Squadra squadra = null;
		String sql = "select * from squadra where squadra.Allenatore = ? and squadra.lega = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, nomeLega);

		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			LegaDAO legaDAO = new LegaDAO();
			AllenatoreDAO allenatoreDAO = new AllenatoreDAO();
			GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
			String nome = rs.getString("NomeSquadra");
			String logo = rs.getString("Logo");
			Lega lega = legaDAO.getLegaByNome(rs.getString("Lega"));
			Allenatore allenatoreobj = allenatoreDAO.getAllenatoreByUsername(rs.getString("Allenatore"));
			Giocatore[] giocatori = giocatoreDAO.getGiocatoriBySquadra(lega.getNome(),rs.getString("nomeSquadra"));
			int punti = rs.getInt("Punti");
			int budget = rs.getInt("BudgetRimanente");
			squadra = new Squadra(rs.getString("NomeSquadra"),logo,allenatoreobj,lega,punti,budget);
			squadra.setGiocatori(giocatori);
			squadra = new Squadra(nome,logo,allenatoreobj,lega,punti,budget);
			squadra.setGiocatori(giocatori);
		}
		//conn.close();
				DriverManagerConnectionPool.releaseConnection(conn);
		return squadra;
	}
}
