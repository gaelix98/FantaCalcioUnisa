package test;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import db.DriverManagerConnectionPool;
import gestoreUtente.Scout;
import gestoreUtente.ScoutDAO;

/**
 * 
 * @author Pasquale Caramante
 *
 */ 
public class ScoutDAOTest {
	private static ScoutDAO scoutDAO=new ScoutDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void testAddScout(  ) throws SQLException {
			Scout scout = new Scout("Paolo","Fox","Jpaolucci","Paolo@fox.com","password1");
			ArrayList<Scout> scoutlist = scoutDAO.getAllScout();
			assertEquals(3, scoutlist.size());
			scoutDAO.addScout(scout);
			scoutlist = scoutDAO.getAllScout();
			assertEquals(4,scoutlist.size());
	}
	
	@Test
	public void testCheckLogin () throws SQLException {
			//login con credenziali corrette e sbagliate
			String username = "LoScarso2";
			String password = "imsohappyinthejungle1";
			assertTrue(scoutDAO.checkLogin(username, password));
			
			password ="passwordSbagliata";
			assertFalse(scoutDAO.checkLogin(username, password));
			
			username = "UsernameSbagliato";
			assertFalse(scoutDAO.checkLogin(username, password));
	}
	
	@Test	
	public void testDeleteScout() throws SQLException {
		String username = "Narducci2000";
		Scout scout=scoutDAO.getScoutByUsername(username);
		assertNotNull(scout);
		scoutDAO.deleteScout(username);
		scout=scoutDAO.getScoutByUsername(username);
		assertNull(scout);
	}
	
	@Test	
	public void testUpdateScout() throws SQLException {
		String username = "LoScarso";
		String password = "bingobangobongo1";
		String nuovaPassword = "nuovapassword";
		Scout scout = scoutDAO.getScoutByUsername(username);
		assertNotNull(scout);
		assertEquals(password,scout.getPassword());
		scout.setPassword(nuovaPassword);
		scoutDAO.updateScout(scout);
		scout = scoutDAO.getScoutByUsername(username);
		assertEquals(nuovaPassword,scout.getPassword());
	}
	
	@Test	
	public void testGetAllScout() throws SQLException {
		ArrayList<Scout> scout = scoutDAO.getAllScout();
		assertEquals(3,scout.size());
		
	}
	
	@Test	
	public void testGetScoutByUsername()  throws SQLException {
		Scout scout = scoutDAO.getScoutByUsername("LoScarso2");
		assertNotNull(scout);
	}
	
	@Test
	public void testGetScoutByEmail() throws SQLException  {
		Scout scout = scoutDAO.getScoutByEmail("mipiaceflammarti@tutto.it");
		assertNotNull(scout);
	}
}
