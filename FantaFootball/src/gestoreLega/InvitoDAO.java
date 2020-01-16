package gestoreLega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DriverManagerConnectionPool;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Invito.
 * @author Gaetano Casillo
 *
 */
public class InvitoDAO {

	/**
	 * 
	 * @param invito invito da aggiungere
	 * @return true if database.invito->includes(select(i|invito.allenatore=invito.getAllenatore().getUsername()
	 *  and invito.nomeLega=invito.getLega().getNome())), else altrimenti
	 * @throws SQLException
	 */
	public boolean addInvito(Invito invito) throws SQLException {
		boolean ok=false;
		try(Connection con =  DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps =con.prepareStatement("INSERT INTO invito(Allenatore,NomeLega,risposta) VALUES(?,?,?)");
			ps.setString(1, invito.getAllenatore().getUsername());
			ps.setString(2, invito.getLega().getNome());
			ps.setBoolean(3, invito.isRisposta());
			ps.execute();
			con.close();

		}catch(SQLException e) {
			e.printStackTrace();
			return ok;
		}
		ok=true;
		return ok;
	}
	
	/**
	 * 
	 * @param invito invito da aggiornare
	 * @return true se i dati sono stati aggiornati correttamente, else altrimenti
	 * @throws SQLException
	 */
	public  boolean updateInvito(Invito invito) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Update invito SET risposta=? where allenatore=? AND NomeLega=?");
			ps.setBoolean(1, invito.isRisposta());
			ps.setString(2, invito.getAllenatore().getUsername());
			ps.setString(3, invito.getLega().getNome());
			
			ps.executeUpdate();
			con.close();
		}
		catch(SQLException x) {
			x.printStackTrace();
			return ok;

		}
		ok=true;
		return ok;
	}
	
	/**
	 * 
	 * @param username username dell'allenatore invitato
	 * @param NomeLega nome della lega dell'invito
	 * @return  true if database.invito-> not includes(select(i|invito.allenatore=invito.getAllenatore().getUsername()
	 *  and invito.nomeLega=invito.getLega().getNome())), false altrimenti
	 * @throws SQLException
	 */
	public boolean deleteInvito(String username,String NomeLega) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Delete from invito  where allenatore=? AND NomeLega=?");

			ps.setString(1,username);
			ps.setString(2, NomeLega);
			ps.executeUpdate();
			con.close();}
		catch(SQLException x) {x.printStackTrace();
		return ok;

		}
		
		ok=true;

		return ok;
	}
	
	/**
	 * 
	 * @param allenatore username dell'allenatore invitato
	 * @return inviti->select(i|invito.allenatore=allenatore)
	 * @throws SQLException
	 */
	public List<Invito> getInvitoByAllenatore(String allenatore) throws SQLException{

		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM invito where Allenatore=?");
			ps.setString(1, allenatore);
			ResultSet rs= ps.executeQuery();
			LegaDAO daol= new LegaDAO();
			AllenatoreDAO daoA = new AllenatoreDAO();
			ArrayList<Invito> inviti=new ArrayList<Invito>();
			Allenatore cavia = daoA.getAllenatoreByUsername(allenatore);
			while(rs.next()) {
				Invito u=new Invito(cavia,daol.getLegaByNome(rs.getString(2)),rs.getBoolean(3));
				inviti.add(u);
			}
			conn.close();
			return inviti;



		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param allenatore allenatore invitato
	 * @param lega lega a cui è invitato l'allenatore
	 * @return invito->select (i| invito.allenatore=allenatore and invito.lega=lega)
	 * @throws SQLException
	 */
	public Invito getInvitoById(Allenatore allenatore, Lega lega) throws SQLException {
		boolean ok=false;
		Invito invito=null;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("select * from invito where allenatore=? and nomeLega=?");
			ps.setString(1, allenatore.getUsername());
			ps.setString(2, lega.getNome());
			ResultSet rs= ps.executeQuery();			
			while(rs.next()) {
				invito=new Invito(allenatore, lega, rs.getBoolean(3));
			}
			con.close();
		}
		return invito;
	}


}
