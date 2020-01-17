package gestoreSquadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DriverManagerConnectionPool;
import gestoreLega.Invito;
import gestoreLega.LegaDAO;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

/**
 * 
 * @author Gaetano Casillo
 *
 */
public class FormazioneDAO {

	/**
	 * 
	 * @param formazione formazione da aggiungere
	 * @return true if database.formazione->includes(select(f|formazione.nomeSquadra=formazione.getSquadra().getNome() and 
	 * formazione.nomeLega=formazione.getSquadra().getLega().getNome() and formazione.modulo=formazione.getModulo() and 
	 * formazione.giornata=formazione.getGiornata() and formazione.schierata=formazione.getSchierata())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addFormazione(Formazione formazione) throws SQLException {
		boolean ok=false;
		try(Connection con =  DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps =con.prepareStatement("INSERT INTO formazione(Giornata,Schierata,Squadra,NomeLega) VALUES(?,?,?,?)");
			ps.setInt(1, formazione.getGiornata());
			ps.setBoolean(2, formazione.isSchierata());
			ps.setString(3, formazione.getSquadra().getNome());
			ps.setString(4, formazione.getSquadra().getLega().getNome()); 
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
	 * @param formazione formazione a cui aggiungere il giocatore
	 * @param giocatore giocatore da aggiungere alla formazione
	 * @param posizione posizione del giocatore nella formazione
	 * @return true if database.giocatoreFormazione->includes(select(f|giocatoreFormazione.nomeSquadra=formazione.getSquadra().getNome() 
	 * and giocatoreFormazione.nomeLega= formazione.getSquadra().getLega().getNome() and 
	 * giocatoreFormazione.giornata= formazione.getGiornata() and giocatoreFormazione.giocatore=giocatore.getId())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addGiocatoreFormazione(Formazione formazione,Giocatore giocatore, int posizione) throws SQLException {
		boolean ok=false;
		try(Connection con =  DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps =con.prepareStatement("INSERT INTO giocatoreformazione(Giornata,NomeSquadra,NomeLega,Id,posizione) VALUES(?,?,?,?,?)");
			ps.setInt(1, formazione.getGiornata());
			ps.setString(2, formazione.getSquadra().getNome());
			ps.setString(3, formazione.getSquadra().getLega().getNome()); 
			ps.setInt(4, giocatore.getId());
			ps.setInt(5,posizione); 
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
	 * @param formazione formazione a cui rimuovere il giocatore
	 * @param giocatore giocatore da rimuovere nella formazione 
	 * @return true if database.giocatoreFormazione-> not includes(select(f|giocatoreFormazione.nomeSquadra=formazione.getSquadra().getNome()
	 *  and giocatoreFormazione.nomeLega= formazione.getSquadra().getLega().getNome() and 
	 *  giocatoreFormazione.giornata= formazione.getGiornata() and giocatoreFormazione.giocatore=giocatore.getId())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean deleteGiocatoreFormazione(Formazione formazione, Giocatore giocatore) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Delete from giocatoreformazione where Id=? AND Giornata=? AND NomeLega=? AND NomeSquadra=?");

			ps.setInt(1,giocatore.getId());
			ps.setInt(2, formazione.getGiornata());
			ps.setString(3, formazione.getSquadra().getLega().getNome());
			ps.setString(4, formazione.getSquadra().getNome());
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
	 * @param formazione formazione in cui effettuare lo scambio giocatori
	 * @param giocatore1 giocatore da sostituire
	 * @param giocatore2 giocatore sostituto
	 * @return true if database.giocatoreFormazione-> includes(select(f|giocatoreFormazione.nomeSquadra=formazione.getSquadra().getNome()
	 *  and giocatoreFormazione.nomeLega= formazione.getSquadra().getLega().getNome() and 
	 *  giocatoreFormazione.giornata= formazione.getGiornata() and giocatoreFormazione.giocatore=giocatore2.getId())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean updateGiocatoreFormazione(Formazione formazione, Giocatore giocatore1, Giocatore giocatore2) throws SQLException {
		boolean ok=false;
		int posizione;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("update giocatoreformazione set id=? where Id=? AND Giornata=? AND NomeLega=? AND NomeSquadra=?");
			ps.setInt(1, giocatore2.getId());
			ps.setInt(2, giocatore1.getId());
			ps.setInt(3, formazione.getGiornata());
			ps.setString(4, formazione.getSquadra().getLega().getNome());
			ps.setString(5, formazione.getSquadra().getNome());
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
	 * @param formazione formazione da aggiornare
	 * @return true se la formazione è stata aggiornata, false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean updateFormazione(Formazione formazione) throws SQLException {
		boolean ok=false;
		try(Connection con= DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("Update formazione SET schierata=? where Giornata=? and Squadra=? and NomeLega=?");
			ps.setBoolean(1, formazione.isSchierata());
			ps.setInt(2,formazione.getGiornata());
			ps.setString(3, formazione.getSquadra().getNome());
			ps.setString(4, formazione.getSquadra().getLega().getNome());

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
	 * @param squadra squadra di cui si vuole cercare la formazione
	 * @param giornata giornata di cui si vuole cercare la formazione della squadra
	 * @return formazione-> select(f|formazione.nomeSquadra=squadra.getNome() and formazione.nomeLega=squadra.getLega().getNome() 
	 * and formazione.giornata=giornata)
	 * @throws SQLException
	 */
	public synchronized Formazione getFormazioneBySquadraGiornata(Squadra squadra, int giornata) throws SQLException{
		AllenatoreDAO alld= new AllenatoreDAO();
		LegaDAO legD= new LegaDAO();
		GiocatoreDAO gd = new GiocatoreDAO();
		Giocatore[] giocatori =new Giocatore[11];
		Giocatore[] panchina  = new Giocatore[7];
		int i=0, j=0;
		try (Connection conn = DriverManagerConnectionPool.getConnection();) {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM formazione, giocatoreformazione where formazione.squadra=giocatoreformazione.nomeSquadra "
					+ "and formazione.nomeLega=giocatoreformazione.nomeLega and squadra=? and formazione.NomeLega=? and formazione.giornata=?"
					+ " ORDER BY posizione");
			ps.setString(1, squadra.getNome());
			ps.setString(2, squadra.getLega().getNome());
			ps.setInt(3, giornata);
			ResultSet rs= ps.executeQuery(); 
			Formazione u=null;
			boolean exists=false;
			boolean schierata=false;
			while(rs.next()){
				schierata=rs.getBoolean("schierata");
				if(rs.getInt("posizione")<11) {
					giocatori[i]=gd.getGiocatoreById(rs.getInt("Id"));
					i++;
				}else {
					panchina[j]=gd.getGiocatoreById(rs.getInt("Id"));
					j++;
				}
				exists=true;
				
			}
			if (exists) {
				u=new Formazione(giornata, squadra);
				u.setSchierata(schierata);
				u.setGiocatori(giocatori);
				u.setPanchina(panchina);
			}
			conn.close();
			return u;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}


