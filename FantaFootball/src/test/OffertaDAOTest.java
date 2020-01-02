package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreLega.Asta;
import gestoreLega.AstaDAO;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Offerta;
import gestoreSquadra.OffertaDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

public class OffertaDAOTest {
	private static OffertaDAO offertaDAO=new OffertaDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void testAddOfferta() throws SQLException{
		Squadra squadra=new SquadraDAO().getSquadraById("Zoe4Evah", "MemeroniX");
		Asta asta=new AstaDAO().getAstaByKey(Date.valueOf("2019-12-25"), "MemeroniX");
		Giocatore giocatore=new GiocatoreDAO().getGiocatoreById(1);
		int prezzo=30;
		Offerta offerta=new Offerta(squadra, asta, giocatore, prezzo);
		offertaDAO.addOfferta(offerta);
		assertNotNull(offertaDAO.getOffertaByKey(giocatore.getId(), asta.getDataInizio(), squadra.getLega().getNome(), squadra.getNome()));
	}
	
	@Test
	public void testDeleteOfferta() throws SQLException{
		Offerta offerta=offertaDAO.getOffertaByKey(4, Date.valueOf("2019-12-25"), "MemeroniX", "Zoe4Evah");
		assertNotNull(offerta);
		offertaDAO.deleteOfferta(offerta);
		offerta=offertaDAO.getOffertaByKey(4, Date.valueOf("2019-12-25"), "MemeroniX", "Zoe4Evah");
		assertNull(offerta);
	}
	
	@Test
	public void testUpdateOfferta() throws SQLException{
		Offerta offerta=offertaDAO.getOffertaByKey(4, Date.valueOf("2019-12-25"), "MemeroniX", "Zoe4Evah");
		assertNotNull(offerta);
		offerta.setSomma(100);
		offertaDAO.updateOfferta(offerta);
		offerta=offertaDAO.getOffertaByKey(4, Date.valueOf("2019-12-25"), "MemeroniX", "Zoe4Evah");
		assertEquals(100, offerta.getSomma());
	}
	
	@Test
	public void testGetAllOfferteByAsta() throws SQLException {
		Date data=Date.valueOf("2019-12-25");
		String nomeLega="MemeroniX";
		List<Offerta> offerte=offertaDAO.getAllOfferteByAsta(data, nomeLega);
		assertEquals(3, offerte.size());
		
		data=Date.valueOf("2019-02-15");
		offerte=offertaDAO.getAllOfferteByAsta(data, nomeLega);
		assertEquals(0, offerte.size());
	}
	
	@Test
	public void testGetAllOfferteBySquadra() throws SQLException {
		String nomeLega="MemeroniX";
		String nomeSquadra="Zoe4Evah";
		List<Offerta> offerte=offertaDAO.getAllOfferteBySquadra(nomeSquadra, nomeLega);
		assertEquals(2, offerte.size());
		
		nomeLega="Fiorellina";
		nomeSquadra="NotMemeroni";
		offerte=offertaDAO.getAllOfferteBySquadra(nomeSquadra, nomeLega);
		assertEquals(0, offerte.size());
	}
	
	@Test
	public void testGetAllOfferteByAstaAllenatore() throws SQLException {
		Date data=Date.valueOf("2019-12-25");
		String nomeLega="MemeroniX";
		String allenatore="ArtAttack";
		List<Offerta> offerte=offertaDAO.getAllOfferteByAstaAllenatore(data, nomeLega, allenatore);
		assertEquals(2, offerte.size());
		
		data=Date.valueOf("2019-02-15");
		offerte=offertaDAO.getAllOfferteByAstaAllenatore(data, nomeLega, allenatore);
		assertEquals(0, offerte.size());
	}
	
	@Test
	public void testGetAllOfferteGiocatoreAsta() throws SQLException{
		Date data=Date.valueOf("2019-12-25");
		String nomeLega="MemeroniX";
		int giocatore=1;
		List<Offerta> offerte=offertaDAO.getAllOfferteGiocatoreAsta(giocatore, data, nomeLega);
		assertEquals(0, offerte.size());
		
		giocatore=4;
		offerte=offertaDAO.getAllOfferteGiocatoreAsta(giocatore, data, nomeLega);
		assertEquals(1, offerte.size());
	}
}
