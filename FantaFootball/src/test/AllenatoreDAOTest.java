package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import db.DriverManagerConnectionPool;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

public class AllenatoreDAOTest {
	private static AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void getAllAllenatoriTest() throws Exception{
		assertEquals(3, allenatoreDAO.getAllAllenatori().size());
	}
	
	@Test
	public void getAllenatoreByUsernameTest() throws Exception{
		String username="pasquale98";
		assertNotNull(allenatoreDAO.getAllenatoreByUsername(username));
		username="pasquale";
		assertNull(allenatoreDAO.getAllenatoreByUsername(username));

	}
	
	@Test
	public void getAllenatoreByEmailTest() throws Exception{
		String email="pasquale@gmail.com";
		assertNotNull(allenatoreDAO.getAllenatoreByEmail(email));
		email="pasqualeeee@gmail.com";
		assertNull(allenatoreDAO.getAllenatoreByEmail(email));

	}
	
	@Test
	public void addAllenatoreTest() throws Exception{
		Allenatore allenatore=new Allenatore("Gaetano", "Casillo", "gaelix98@gmail.com", "gaelix98", "gaetano");
		assertNull(allenatoreDAO.getAllenatoreByUsername("gaelix98"));
		allenatoreDAO.addAllenatore(allenatore);
		assertNotNull(allenatoreDAO.getAllenatoreByUsername("gaelix98"));

	}
	
	@Test
	public void deleteAllenatoreTest() throws Exception{
		Allenatore allenatore=allenatoreDAO.getAllenatoreByUsername("pasquale98");
		assertNotNull(allenatore);
		allenatoreDAO.deleteAllenatore("pasquale98");
		assertNull(allenatoreDAO.getAllenatoreByUsername("pasquale98"));
	}
	
	@Test
	public void updateAllenatoreTest() throws Exception{
		Allenatore allenatore=allenatoreDAO.getAllenatoreByUsername("pasquale98");
		assertNotNull(allenatore);
		assertEquals("pasquale12", allenatore.getPassword());
		allenatore.setPassword("pasquale1234");
		allenatoreDAO.updateAllenatore(allenatore);
		assertEquals("pasquale1234", allenatoreDAO.getAllenatoreByUsername("pasquale98").getPassword());

	}
	
	@Test
	public void checkLoginTest() throws Exception{
		String username="pasquale98";
		String password="pasquale12";
		assertTrue(allenatoreDAO.checkLogin(username, password));
		
		password="pasquale1234";
		assertFalse(allenatoreDAO.checkLogin(username, password));
		
		username="pasquale988";
		password="pasquale12";
		assertFalse(allenatoreDAO.checkLogin(username, password));
		
	}
	
}
