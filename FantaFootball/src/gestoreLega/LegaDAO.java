package gestoreLega;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Offerta;
import gestoreSquadra.OffertaDAO;
import gestoreSquadra.Squadra;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

public class LegaDAO {
	public synchronized Lega getLegaByNome(String lega) throws SQLException{
		AllenatoreDAO dao = new AllenatoreDAO();
		Lega u=null;
		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM lega where NomeLega=?");
			ps.setString(1, lega);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				u=new Lega(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),dao.getAllenatoreByUsername(rs.getString(9)));
			}
		    conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public synchronized boolean addLega(Lega lega) throws SQLException {
		boolean ok=false;
		try(Connection con =  DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps =con.prepareStatement("INSERT INTO lega(NomeLega,Logo,MaxAllenatori,quotaMensile,budget,primoPosto,secondoPosto,terzoPosto,Presidente) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, lega.getNome());
			ps.setString(2, lega.getLogo());
			ps.setInt(3, lega.getMaxAllenatori());
			ps.setInt(4, lega.getQuotaMensile());
			ps.setInt(5,lega.getBudget());
			ps.setInt(6, lega.getPrimoPosto());
			ps.setInt(7, lega.getSecondoPosto());
			ps.setInt(8, lega.getTerzoPosto());
			ps.setString(9, lega.getPresidente().getUsername());
			ps.execute();
			con.close();

		}catch(SQLException e) {
			e.printStackTrace();
			return ok;
		}
		ok=true;
		return ok;
	}

	public synchronized ArrayList<Lega> getLegheByPresidente(Allenatore allenatore) throws SQLException{
		Connection conn =  DriverManagerConnectionPool.getConnection();
		Lega lega=null;
		ArrayList<Lega> leghe=new ArrayList<>();
		String sql="select * from lega where lega.presidente=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, allenatore.getUsername());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String nome=rs.getString("nomeLega");
			String logo=rs.getString("logo");
			int maxAllenatori=rs.getInt("maxAllenatori");
			int quotaMensile=rs.getInt("quotaMensile");
			int budget=rs.getInt("budget");
			int primoPosto=rs.getInt("primoPosto");
			int secondoPosto=rs.getInt("secondoPosto");
			int terzoPosto=rs.getInt("terzoPosto");
			lega=new Lega(nome, logo, maxAllenatori, quotaMensile, budget, primoPosto, secondoPosto, terzoPosto, allenatore);
			leghe.add(lega);
		}
		conn.close();
		return leghe;
	}
	
	public synchronized boolean deleteLega(Lega lega) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Delete from lega  where NomeLega=?");
			ps.setString(1, lega.getNome());
			ps.executeUpdate();
			con.close();}
		catch(SQLException x) {x.printStackTrace();
		return ok;

		}
		ok=true;

		return ok;
	}
	

}
