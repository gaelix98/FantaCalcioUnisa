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
 * Questa classe � un manager che si occupa di interagire con il database. Gestisce le query riguardanti Squadra.
 * @author Pasquale Caramante
 *
 */
public class SquadraDAO {
	Connection conn;
	
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
		conn.close();
		return modificato;
	}
	
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
		conn.close();
		return inserito;	
	}
	
	
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
		conn.close();
	}
	
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
		return squadre;
	}
	
	
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
		conn.close();
		return squadra;
	}
	
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
		conn.close();
		return squadre;
	}
	
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
		
		conn.close();
		return squadre;
	}
	

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
			Giocatore[] giocatori = giocatoreDAO.getGiocatoriBySquadra(rs.getString("NomeSquadra"),lega.getNome());
			int punti = rs.getInt("Punti");
			int budget = rs.getInt("BudgetRimanente");
			squadra = new Squadra(rs.getString("NomeSquadra"),logo,allenatoreobj,lega,punti,budget);
			squadra.setGiocatori(giocatori);
			squadra = new Squadra(nome,logo,allenatoreobj,lega,punti,budget);
			squadra.setGiocatori(giocatori);
		}
		conn.close();
		return squadra;
	}
}
