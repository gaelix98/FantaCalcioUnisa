package test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreLega.Asta;
import gestoreLega.AstaDAO;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;

public class AstaDAOTest {
	private static AstaDAO astaDAO=new AstaDAO();
	private static LegaDAO legaDAO=new LegaDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@Test
	public void TestAddAsta() throws SQLException{
		Date data=Date.valueOf("2019-12-31");
		Time ora=Time.valueOf("12:00:00");
		Lega lega=legaDAO.getLegaByNome("NotMemeroni");
		Date dataFine=Date.valueOf("2020-01-01");
		Asta nuovaAsta=new Asta(data, legaDAO.getLegaByNome("NotMemeroni"), ora, dataFine);
		
		assertNotNull(nuovaAsta);
		//List<Asta> aste=astaDAO.getAsteByLega(lega);
		//assertEquals(aste.size(),0);
		astaDAO.addAsta(nuovaAsta);
		List<Asta> nuoveAste=astaDAO.getAsteByLega(lega);
		assertTrue(nuoveAste.contains(nuovaAsta));
	}
	
}
