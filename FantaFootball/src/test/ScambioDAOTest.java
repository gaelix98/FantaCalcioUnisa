package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Scambio;
import gestoreSquadra.ScambioDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * 
 * @author Pasquale Caramante
 *
 */

public class ScambioDAOTest {
	private static ScambioDAO scambioDAO = new ScambioDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void testCreaScambio() throws SQLException{
		GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
		SquadraDAO squadraDAO = new SquadraDAO();
		Giocatore giocatore1 = giocatoreDAO.getGiocatoreById(1);
		Giocatore giocatore2 = giocatoreDAO.getGiocatoreById(2);
		Squadra squadra1 = squadraDAO.getSquadraById("PippoSquad", "NotMemeroni");
		Squadra squadra2 = squadraDAO.getSquadraById("Fiorellina", "NotMemeroni");
		assertNotNull(giocatore1);
		assertNotNull(giocatore2);
		assertNotNull(squadra1);
		assertNotNull(squadra2);
		
		Scambio scambio = scambioDAO.getScambioById(giocatore1,giocatore2,squadra1,squadra2);
		assertNull(scambio);
		scambioDAO.creaScambio(new Scambio(giocatore1,giocatore2,50,squadra1,squadra2));
		scambio = scambioDAO.getScambioById(giocatore1,giocatore2,squadra1,squadra2);
		assertNotNull(scambio);
	}
	
	@Test
	public void testAccettaScambio() throws SQLException{
		SquadraDAO squadraDAO = new SquadraDAO();
		Squadra squadra = squadraDAO.getSquadraById("FantaCola","MemeroniX");
		ArrayList<Scambio> scambiNonAccettati = scambioDAO.getScambiNonAccettatiSquadra(squadra);
		assertEquals(1,scambiNonAccettati.size()); 
		Scambio scambio = scambiNonAccettati.get(0);
		Giocatore giocatore1 = scambio.getGiocatore1();
		Giocatore giocatore2 = scambio.getGiocatore2();
		Squadra squadra1 = scambio.getSquadra1();
		Squadra squadra2 = scambio.getSquadra2();
		assertTrue(scambioDAO.accettaScambio(scambio));
		scambio = scambioDAO.getScambioById(giocatore1, giocatore2, squadra1, squadra2);
		assertNull(scambio);
	}
	@Test
	public void testRifiutaScambio() throws SQLException{
		SquadraDAO squadraDAO = new SquadraDAO();
		Squadra squadra = squadraDAO.getSquadraById("FantaCola","MemeroniX");
		ArrayList<Scambio> scambiNonAccettati = scambioDAO.getScambiNonAccettatiSquadra(squadra);
		assertEquals(1,scambiNonAccettati.size()); 
		Scambio scambio = scambiNonAccettati.get(0);
		Giocatore giocatore1 = scambio.getGiocatore1();
		Giocatore giocatore2 = scambio.getGiocatore2();
		Squadra squadra1 = scambio.getSquadra1();
		Squadra squadra2 = scambio.getSquadra2();
		scambioDAO.rifiutaScambio(scambio);
	
		scambio = scambioDAO.getScambioById(giocatore1, giocatore2, squadra1, squadra2);
		assertNull(scambio);
	}
	
	@Test
	public void testGetScambiNonAccettatiSquadra() throws SQLException{
		SquadraDAO squadraDAO = new SquadraDAO();
		Squadra squadra = squadraDAO.getSquadraById("FantaCola","MemeroniX");
		ArrayList<Scambio> scambiNonAccettati = scambioDAO.getScambiNonAccettatiSquadra(squadra);
		assertEquals(1,scambiNonAccettati.size()); 
	}
	@Test
	public void testGetScambioById() throws SQLException{
		GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
		SquadraDAO squadraDAO = new SquadraDAO();
		Giocatore giocatore1 = giocatoreDAO.getGiocatoreById(1);
		Giocatore giocatore2 = giocatoreDAO.getGiocatoreById(2);
		Squadra squadra1 =  squadraDAO.getSquadraById("Zoe4Evah", "MemeroniX");
		Squadra squadra2 = squadraDAO.getSquadraById("FantaCola", "MemeroniX");
		Scambio scambio = scambioDAO.getScambioById(giocatore1, giocatore2, squadra1, squadra2);
		assertNotNull(scambio);
	}
}
