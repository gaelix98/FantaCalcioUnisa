package gestoreLega;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;


/**
 * Questa classe � un manager che si occupa di interagire con il database. Gestisce le query riguardanti Asta.
 * @author Maria Natale
 *
 */
public class AstaDAO {
	Connection conn = null ;
	
	/**
	 *  
	 * @param asta asta da aggiungere
	 * @return true if database.asta->includes(select(a|asta.dataInizio= asta.getDataInizio()< and asta.ora=asta.getOra()
	 *  and asta.dataFine= asta.getDataFine() and asta.nomeLega=asta.getLega().getNomeLega())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addAsta(Asta asta) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		int ris; 
		boolean inserito=false;
		String sql="insert into asta values (?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, asta.getDataInizio().toString());
		ps.setString(2, asta.getLega().getNome());
		ps.setString(3, asta.getOra().toString());
		ps.setString(4, asta.getDataFine().toString());
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
	 * @param allenatore username dell'allenatore di cui si vogliono cercare le asta a cui ha partecipato
	 * @return aste->select(a|offerta.allenatore.getNome()=allenatore)
	 * @throws SQLException
	 */
	public synchronized ArrayList<Asta> getAsteByAllenatore(String allenatore) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Asta> aste=new ArrayList<>();
		String sql="select asta.* from asta, offerta, squadra where offerta.dataInizio=asta.dataInizio and asta.nomeLega=offerta.nomeLega\r\n" + 
				"and offerta.squadra=squadra.nomeSquadra and squadra.allenatore=?;";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, allenatore);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Asta asta=null;
			LegaDAO legaDAO=new LegaDAO();
			Lega lega=legaDAO.getLegaByNome(rs.getString("nomeLega"));
			asta=new Asta(Date.valueOf(rs.getString("dataInizio")), lega, Time.valueOf(rs.getString("ora")), Date.valueOf(rs.getString("dataFine")));
			aste.add(asta);
		}
		conn.close();
		return aste;
	}
	
	/**
	 * 
	 * @param dataInizio data inizio asta da cercare
	 * @param nomeLega nome della lega che ha organizzato l'asta
	 * @return asta->select(a|asta.dataInizio=dataInizio and asta.nomeLega=nomeLega)
	 * @throws SQLException
	 */
	public synchronized Asta getAstaByKey(Date dataInizio, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Asta asta=null;
		ArrayList<Asta> aste=new ArrayList<>();
		String sql="select * from asta where asta.dataInizio=? and asta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, dataInizio.toString());
		ps.setString(2, nomeLega);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			LegaDAO legaDAO=new LegaDAO();
			Lega lega=legaDAO.getLegaByNome(rs.getString("nomeLega"));
			asta=new Asta(Date.valueOf(rs.getString("dataInizio")), lega, Time.valueOf(rs.getString("ora")), Date.valueOf(rs.getString("dataFine")));
			aste.add(asta);
		}
		conn.close();
		return asta;
	}
	
	/**
	 * 
	 * @param lega lega di cui si vogliono cercare le aste
	 * @return aste->select(a|asta.nomeLega=lega.getNome())
	 * @throws SQLException
	 */
	public synchronized ArrayList<Asta> getAsteByLega(Lega lega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Asta> aste=new ArrayList<>();
		String sql="select asta.* from asta where asta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, lega.getNome());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Asta asta=null;
			asta=new Asta(Date.valueOf(rs.getString("dataInizio")), lega, Time.valueOf(rs.getString("ora")), Date.valueOf(rs.getString("dataFine")));
			aste.add(asta);
		}
		conn.close();
		return aste;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Asta> getAsteScaduteOggi() throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Asta> aste=new ArrayList<>();
		LegaDAO legaDAO = new LegaDAO();
		String sql="select asta.* from asta where asta.DataFine=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, LocalDate.now().toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Asta asta=null;
			asta=new Asta(Date.valueOf(rs.getString("dataInizio")), legaDAO.getLegaByNome(rs.getString("nomeLega")), Time.valueOf(rs.getString("ora")), Date.valueOf(rs.getString("dataFine")));
			aste.add(asta);
		}
		conn.close();
		return aste;
	}

}
