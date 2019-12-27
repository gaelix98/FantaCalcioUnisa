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

public class InvitoDAO {

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
	
	public  boolean updateInvito(Invito invito) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Update invito SET risposta=?  where allenatore=? AND NomeLega=?");
			ps.setBoolean(1, invito.isRisposta());
			ps.setString(2, invito.getAllenatore().getUsername());
			ps.setString(3, invito.getLega().getNome());
			
			ps.executeUpdate();
		}
		catch(SQLException x) {
			x.printStackTrace();
			return ok;

		}
		ok=true;
		return ok;
	}
	
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
			return inviti;



		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
