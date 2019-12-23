package gestoreLega;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreSquadra.Offerta;
import gestoreSquadra.OffertaDAO;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Asta.
 * @author Maria Natale
 *
 */
public class AstaDAO {
	Connection conn = null;
	
	/**
	 * 
	 * @param asta
	 * @return
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
	 * @param allenatore
	 * @return
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
		/*while(rs.next()) {
			Asta asta=null:
			LegaDAO legaDAO=new LegaDAO();
			Lega lega=legaDAO.getLegaByNome(rs.getString("nomeLega"));
			OffertaDAO offertaDAO=new OffertaDAO();
			ArrayList<Offerta> offerte=offertaDAO.getAllOfferteByAsta(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			asta=new Asta(Date.valueOf(rs.getString("dataInizio")), lega, Time.valueOf(rs.getString("ora")), Date.valueOf(rs.getString("dataFine")));
			asta.setOfferte(offerte);
			aste.add(asta);
		}*/
		conn.close();
		return aste;
	}
	
	/**
	 * 
	 * @param dataInizio
	 * @param nomeLega
	 * @return
	 * @throws SQLException
	 */
	public synchronized Asta getAstaByKey(Date dataInizio, String nomeLega) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Asta asta=null;
		String sql="select * from asta where asta.dataInizio=? and asta.nomeLega=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, dataInizio.toString());
		ps.setString(2, nomeLega);
		ResultSet rs=ps.executeQuery();
		/*while(rs.next()) {
			LegaDAO legaDAO=new LegaDAO();
			Lega lega=legaDAO.getLegaByNome(rs.getString("nomeLega"));
			OffertaDAO offertaDAO=new OffertaDAO();
			ArrayList<Offerta> offerte=offertaDAO.getAllOfferteByAsta(Date.valueOf(rs.getString("dataInizio")), rs.getString("nomeLega"));
			asta=new Asta(Date.valueOf(rs.getString("dataInizio")), lega, Time.valueOf(rs.getString("ora")), Date.valueOf(rs.getString("dataFine")));
			asta.setOfferte(offerte);
			aste.add(asta);
		}*/
		conn.close();
		return asta;
	}

}
