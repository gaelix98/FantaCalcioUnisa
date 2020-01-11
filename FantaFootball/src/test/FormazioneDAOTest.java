package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import db.DriverManagerConnectionPool;
import gestoreSquadra.FormazioneDAO;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import gestoreSquadra.Formazione;


public class FormazioneDAOTest {
	public static FormazioneDAO formazioneDAO=new FormazioneDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void addFormazioneTest() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("FantaCola", "MemeroniX");
		assertNotNull(squadra);
		Formazione formazione=new Formazione(3, false, squadra);
		formazioneDAO.addFormazione(formazione);
		Giocatore giocatore=new GiocatoreDAO().getGiocatoreById(1);
		formazioneDAO.addGiocatoreFormazione(formazione, giocatore, 3);
		//la formazione deve avere almeno un giocatore
		assertNotNull(formazioneDAO.getFormazioneBySquadraGiornata(squadra, 3));
	}
	
	@Test
	public void addGiocatoreFormazioneTest() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("Zoe4Evah", "MemeroniX");
		assertNotNull(squadra);
		Formazione formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNotNull(formazione);
		Giocatore giocatore=new GiocatoreDAO().getGiocatoreById(1);
		formazioneDAO.addGiocatoreFormazione(formazione, giocatore, 3);
		formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNotNull(formazione.getGiocatori()[2].getId());
	}
	
	@Test
	public void deleteGiocatoreFormazioneTest() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("Zoe4Evah", "MemeroniX");
		assertNotNull(squadra);
		Formazione formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNotNull(formazione);
		Giocatore giocatore=new GiocatoreDAO().getGiocatoreById(3);
		formazioneDAO.deleteGiocatoreFormazione(formazione, giocatore);
		formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNull(formazione.getGiocatori()[1]);
	}
	
	@Test
	public void updateGiocatoreFormazioneTest() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("Zoe4Evah", "MemeroniX");
		assertNotNull(squadra);
		Formazione formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNotNull(formazione);
		Giocatore giocatore1=new GiocatoreDAO().getGiocatoreById(3);
		Giocatore giocatore2=new GiocatoreDAO().getGiocatoreById(1);
		formazioneDAO.updateGiocatoreFormazione(formazione, giocatore1, giocatore2);
		formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertEquals(1, formazione.getGiocatori()[0].getId());
	}
	
	@Test
	public void updateFormazioneTest() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("Zoe4Evah", "MemeroniX");
		assertNotNull(squadra);
		Formazione formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNotNull(formazione);
		assertFalse(formazione.isSchierata());
		formazione.setSchierata(true);
		formazioneDAO.updateFormazione(formazione);
		formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertTrue(formazione.isSchierata());
	}
	
	@Test
	public void getFormazioneBySquadraGiornataTest() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("Zoe4Evah", "MemeroniX");
		assertNotNull(squadra);
		Formazione formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,1);
		assertNotNull(formazione);
		
		formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra,2);
		assertNull(formazione);
	}
}
