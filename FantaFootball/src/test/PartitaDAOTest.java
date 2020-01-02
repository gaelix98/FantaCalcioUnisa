package test;
import gestoreSquadra.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreLega.*;
/**
 * 
 * @author Maria Natale
 *
 */
public class PartitaDAOTest {
	private static PartitaDAO partitaDAO=new PartitaDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	

	@Test
	public void testAddOPartita() throws SQLException{
		SquadraDAO squadraDAO=new SquadraDAO();
		String lega="NotMemeroni";
		Squadra squadra1=squadraDAO.getSquadraById("PippoSquad", lega);
		Squadra squadra2=squadraDAO.getSquadraById("Fiorellina", lega);
		assertNotNull(squadra1);
		assertNotNull(squadra2);
		Partita partita=new Partita(squadra1, squadra2, 1);
		partitaDAO.addPartita(partita);
		assertNotNull(partitaDAO.getPartitaById(squadra1, squadra2,1));
	}
	
	@Test
	public void testUpdatePartita() throws SQLException{
		SquadraDAO squadraDAO=new SquadraDAO();
		String lega="MemeroniX";
		Squadra squadra1=squadraDAO.getSquadraById("FantaCola", lega);
		Squadra squadra2=squadraDAO.getSquadraById("Zoe4Evah", lega);
		int giornata=3;
		assertNotNull(squadra1);
		assertNotNull(squadra2);
		Partita partita=partitaDAO.getPartitaById(squadra1, squadra2, giornata);
		assertNotNull(partita);
		assertEquals(0, partita.getGoalSquadra1());
		partita.setGoalSquadra1(3);
		partitaDAO.updatePartita(partita);
		assertEquals(3, partitaDAO.getPartitaById(squadra1, squadra2, giornata).getGoalSquadra1());
	}
	
	@Test
	public void testGetAllPartiteLega() throws Exception{
		String lega="MemeroniX";
		List<Partita> partite=partitaDAO.getAllPartiteLega(lega);
		assertEquals(2, partite.size());
	}
	
	@Test
	public void testGetAllPartiteSquadra() throws Exception{
		SquadraDAO squadraDAO=new SquadraDAO();
		String lega="MemeroniX";
		Squadra squadra=squadraDAO.getSquadraById("FantaCola", lega);
		List<Partita> partite=partitaDAO.getAllPartiteSquadra(squadra);
		assertEquals(2, partite.size());
		
		lega="NotMemeroni";
		squadra=squadraDAO.getSquadraById("PippoSquad", lega);
		partite=partitaDAO.getAllPartiteSquadra(squadra);
		assertEquals(0, partite.size());
	}
	
	@Test
	public void testGetAllPartiteGiornataLega() throws Exception{
		String lega="MemeroniX";
		int giornata=2;
		List<Partita> partite=partitaDAO.getAllPartiteByGiornataLega(giornata, lega);
		assertEquals(1, partite.size());
	}
}
