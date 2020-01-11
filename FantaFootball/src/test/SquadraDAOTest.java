package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

/**
 * 
 * @author Pasquale Caramante
 *
 */

public class SquadraDAOTest {
	private static SquadraDAO squadraDAO = new SquadraDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void testCreaSquadra() throws SQLException{
		LegaDAO legaDAO = new LegaDAO();
		Lega lega = legaDAO.getLegaByNome("NotMemeroni");
		assertNotNull(lega);
		AllenatoreDAO allenatoreDAO = new AllenatoreDAO();
		Allenatore allenatore = allenatoreDAO.getAllenatoreByUsername("Artattack");
		assertNotNull(allenatore);
		
		ArrayList<Squadra> squadre = squadraDAO.getSquadreByLega("NotMemeroni");
		assertEquals(2,squadre.size());
		Squadra squadra = new Squadra("Squadrone","okboomer.jpg",allenatore,lega,0,250);
		squadraDAO.creaSquadra(squadra);
		squadre = squadraDAO.getSquadreByLega("NotMemeroni");
		assertEquals(3,squadre.size());
	}
	
	@Test
	public void testUpdateSquadra() throws SQLException{
		Squadra squadra = squadraDAO.getSquadraById("FantaCola", "MemeroniX");
		assertNotNull(squadra);
		assertEquals(0,squadra.getBudgetRimanente());
		squadra.setBudgetRimanente(100);
		squadraDAO.updateSquadra(squadra);
		squadra=squadraDAO.getSquadraById("FantaCola", "MemeroniX");
		assertEquals(100,squadra.getBudgetRimanente());
	}
	
	@Test
	public void testAddGiocatoreSquadra() throws SQLException{
		GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
		Giocatore[] giocatori = giocatoreDAO.getGiocatoriBySquadra("MemeroniX", "FantaCola");
		int numGiocatori = 0;
		for(Giocatore g: giocatori) {
			if(g!=null)
				numGiocatori++;
		}
		assertEquals(1,numGiocatori);
		squadraDAO.addGiocatoreSquadra("FantaCola", "MemeroniX", 1);
		giocatori = giocatoreDAO.getGiocatoriBySquadra("MemeroniX", "FantaCola");
		numGiocatori = 0;
		for(Giocatore g: giocatori) {
			if(g!=null)
				numGiocatori++;
		}
		assertEquals(2,numGiocatori);
	}
	
	@Test
	public void testDeleteGiocatoreSquadra() throws SQLException{
		GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
		Giocatore[] giocatori = giocatoreDAO.getGiocatoriBySquadra("MemeroniX", "FantaCola");
		int numGiocatori = 0;
		for(Giocatore g: giocatori) {
			if(g!=null)
				numGiocatori++;
		}
		assertEquals(1,numGiocatori);
		squadraDAO.deleteGiocatoreSquadra("FantaCola","MemeroniX",2);
		giocatori = giocatoreDAO.getGiocatoriBySquadra("MemeroniX", "FantaCola");
		numGiocatori = 0;
		for(Giocatore g: giocatori) {
			if(g!=null)
				numGiocatori++;
		}
		assertEquals(0,numGiocatori);
	}
	
	@Test
	public void testGetSquadreByAllenatore() throws SQLException{
		String username = "Sc00S54";
		ArrayList<Squadra> squadre = squadraDAO.getSquadreByAllenatore(username);
		assertEquals(1, squadre.size());
	}
	@Test
	public void testGetSquadraById() throws SQLException{
		Squadra squadra = squadraDAO.getSquadraById("FantaCola", "MemeroniX");
		assertNotNull(squadra);
	}
	@Test
	public void testGetSquadreByLega() throws SQLException{
		String lega = "MemeroniX";
		ArrayList<Squadra> squadre = squadraDAO.getSquadreByLega(lega);
		assertEquals(2, squadre.size());
	}
	
	@Test
	public void testGetSquadreGiocatore() throws SQLException{
		GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
		Giocatore giocatore = giocatoreDAO.getGiocatoreById(2);
		assertNotNull(giocatore);
		ArrayList<Squadra> squadre = squadraDAO.getSquadreGiocatore(giocatore);
		assertEquals(1,squadre.size());
	}
	
	@Test
	public void testGetSquadraByUserELega() throws SQLException{
		String user = "pasquale98";
		String lega = "MemeroniX";
		Squadra squadra = squadraDAO.getSquadraByUserELega(user, lega);
		assertNotNull(squadra);
		assertEquals("FantaCola",squadra.getNome());
	}
}
