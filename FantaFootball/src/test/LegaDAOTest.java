package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

public class LegaDAOTest {
	public static LegaDAO legaDAO=new LegaDAO();

	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void getLegaByNomeTest() throws Exception{
		String nomeLega="MemeroniX";
		Lega lega=legaDAO.getLegaByNome(nomeLega);
		assertNotNull(lega);
		
		nomeLega="Memeroni";
		lega=legaDAO.getLegaByNome(nomeLega);
		assertNull(lega);
	}
	
	@Test
	public void addLegaTest() throws Exception{
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername("ArtAttack");
		assertNotNull(allenatore);
		Lega lega=new Lega("Memeroni", "", 5, 30, 500, 50, 30, 20, allenatore);
		assertNull(legaDAO.getLegaByNome("Memeroni"));
		legaDAO.addLega(lega);
		assertNotNull(legaDAO.getLegaByNome("Memeroni"));
	}
	
	@Test
	public void getLegheByPresidenteTest() throws Exception{
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername("pasquale98");
		assertNotNull(allenatore);
		assertEquals(2, legaDAO.getLegheByPresidente(allenatore).size());
		
		allenatore=new AllenatoreDAO().getAllenatoreByUsername("ArtAttack");
		assertNotNull(allenatore);
		assertEquals(0, legaDAO.getLegheByPresidente(allenatore).size());
	}
	
	@Test
	public void deleteLegaTest() throws Exception{
		Lega lega=legaDAO.getLegaByNome("MemeroniX");
		assertNotNull(lega);
		legaDAO.deleteLega(lega);
		assertNull(legaDAO.getLegaByNome("MemeroniX"));
	}
}
