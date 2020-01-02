package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;

/**
 * 
 * @author Maria Natale
 *
 */
public class GiocatoreDAOTest {
	private static GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void testAddGiocatore() throws Exception{
		Giocatore giocatore=new Giocatore("Ciro", "Immobile", "Att", "Lazio", 17, 7.5f, 16, 5, 3, 0, 2, 0, 0, 20);
		List<Giocatore> giocatori=giocatoreDAO.getByPrezzoBase(0);
		assertEquals(4, giocatori.size());
		giocatoreDAO.addGiocatore(giocatore);
		giocatori=giocatoreDAO.getByPrezzoBase(0);
		assertEquals(5, giocatori.size());
	}
	
	@Test
	public void testAggiornaGiocatore() throws Exception{
		Giocatore giocatore=giocatoreDAO.getGiocatoreById(1);
		assertNotNull(giocatore);
		assertEquals(0, giocatore.getAmmonizioni());
		giocatore.setAmmonizioni(1);
		giocatoreDAO.aggiornaGiocatore(giocatore);
		giocatore=giocatoreDAO.getGiocatoreById(1);
		assertEquals(1, giocatore.getAmmonizioni());
	}
	
	@Test
	public void testGetGiocatoriBySquadra() throws Exception{
		Giocatore[] giocatori=giocatoreDAO.getGiocatoriBySquadra("MemeroniX", "Zoe4Evah");
		assertEquals(3, giocatori[0].getId());
		assertEquals(4, giocatori[1].getId());
		assertEquals(null, giocatori[2]);
	}
	
	@Test 
	public void testGetByPrezzoBase() throws Exception{
		int prezzoBase=0;
		List<Giocatore> giocatori=giocatoreDAO.getByPrezzoBase(prezzoBase);
		assertEquals(4, giocatori.size());
		
		prezzoBase=10;
		giocatori=giocatoreDAO.getByPrezzoBase(prezzoBase);
		assertEquals(1, giocatori.size());
		
		prezzoBase=20;
		giocatori=giocatoreDAO.getByPrezzoBase(prezzoBase);
		assertEquals(0, giocatori.size());
	}
}
