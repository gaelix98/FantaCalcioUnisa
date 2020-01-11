package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreLega.Invito;
import gestoreLega.InvitoDAO;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;

public class InvitoDAOTest {
	public static InvitoDAO invitoDAO=new InvitoDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void addInvitoTest() throws SQLException{
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername("ArtAttack");
		Lega lega=new LegaDAO().getLegaByNome("NotMemeroni");
		Invito invito=new Invito(allenatore, lega, false);
		invitoDAO.addInvito(invito);
		assertNotNull(invitoDAO.getInvitoById(allenatore, lega));
	}
	
	@Test
	public void updateInvitoTest() throws SQLException{
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername("ArtAttack");
		Lega lega=new LegaDAO().getLegaByNome("MemeroniX");
		Invito invito=invitoDAO.getInvitoById(allenatore, lega);
		assertEquals(true, invitoDAO.getInvitoById(allenatore, lega).isRisposta());
		invito.setRisposta(false);
		invitoDAO.updateInvito(invito);
		assertEquals(false, invitoDAO.getInvitoById(allenatore, lega).isRisposta());
	}
	
	@Test
	public void deleteInvitoTest() throws Exception{
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername("ArtAttack");
		Lega lega=new LegaDAO().getLegaByNome("MemeroniX");
		Invito invito=invitoDAO.getInvitoById(allenatore, lega);
		assertNotNull(invito);
		invitoDAO.deleteInvito(allenatore.getUsername(), lega.getNome());
		assertNull(invitoDAO.getInvitoById(allenatore, lega));
	}
	
	@Test
	public void getInvitoByAllenatoreTest() throws Exception{
		String username="ArtAttack";
		List<Invito> inviti=invitoDAO.getInvitoByAllenatore(username);
		assertEquals(1, inviti.size());
	}
	
	@Test
	public void getInvitoByIdTest() throws Exception{
		String username="ArtAttack";
		String lega="MemeroniX";
		assertNotNull(invitoDAO.getInvitoById(new AllenatoreDAO().getAllenatoreByUsername(username), new LegaDAO().getLegaByNome(lega)));
	}
}
