package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreSquadra.FaiOffertaServlet;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;
import gestoreUtente.RegistrazioneServlet;


public class FaiOffertaServletTest {
	private FaiOffertaServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Offerta non effettuata";
	final String successMessage="successo";

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new FaiOffertaServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}


	//test case TC_1.5.1
	@Test
	public void testCase_1() throws ServletException, IOException, SQLException {
		String username="pasquale98";
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername(username);
		request.getSession().setAttribute("utente", allenatore);
		request.addParameter("lega", "MemeroniX");
		request.addParameter("data", "2019-12-25");
		request.addParameter("giocatore", "1");
		request.addParameter("sommaOfferta", "330");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//test case TC_1.5.2
	@Test
	public void testCase_2() throws ServletException, IOException, SQLException {
		String username="pasquale98";
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername(username);
		request.getSession().setAttribute("utente", allenatore);
		request.addParameter("lega", "MemeroniX");
		request.addParameter("data", "2019-12-25");
		request.addParameter("giocatore", "1");
		request.addParameter("sommaOfferta", "3");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//test case TC_1.5.3
	@Test
	public void testCase_3() throws ServletException, IOException, SQLException {
		String username="pasquale98";
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername(username);
		request.getSession().setAttribute("utente", allenatore);
		request.addParameter("lega", "MemeroniX");
		request.addParameter("data", "2019-12-25");
		request.addParameter("giocatore", "1");
		request.addParameter("sommaOfferta", "3h00");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}


	//test case TC_1.5.4
	@Test
	public void testCase_4() throws ServletException, IOException, SQLException {
		String username="pasquale98";
		Allenatore allenatore=new AllenatoreDAO().getAllenatoreByUsername(username);
		request.getSession().setAttribute("utente", allenatore);
		request.addParameter("lega", "MemeroniX");
		request.addParameter("data", "2019-12-25");
		request.addParameter("giocatore", "1");
		request.addParameter("sommaOfferta", "220");

		servlet.doPost(request, response);
		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
