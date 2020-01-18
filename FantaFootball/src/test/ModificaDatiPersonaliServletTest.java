package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreUtente.Allenatore;
import gestoreUtente.ModificaDatiPersonaliServlet;
import gestoreUtente.RegistrazioneServlet;
import gestoreUtente.Scout;

public class ModificaDatiPersonaliServletTest {
	private ModificaDatiPersonaliServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Formato errato dati";
	final String successMessage="successo";
	private Scout scout;
	private Allenatore allenatore;

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new ModificaDatiPersonaliServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
		scout=new Scout("Paolo", "Conte", "nonhopiuidee@perlemail.com", "LoScarso","bingobangobongo1");
		allenatore=new Allenatore("Giovanni", "Mucciaccia", "artattack@hotmail.it", "Artattack", "Capoo22");
	}

	//email formato errato scout
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail@com");
		request.getSession().setAttribute("tipoUtente", "scout");
		request.getSession().setAttribute("utente", scout);
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}

	//email esistente scout
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "pasquale@gmail.com");
		request.getSession().setAttribute("tipoUtente", "scout");
		request.getSession().setAttribute("utente", scout);
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//password formato errato password scout
	@Test
	public void testCase_3() throws ServletException, IOException {
		request.addParameter("password", "Condo r1234");
		request.addParameter("email", "nonhopiuidee@perlemail.com");
		request.getSession().setAttribute("tipoUtente", "scout");
		request.getSession().setAttribute("utente", scout);
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}



	// successo scout
	@Test
	public void testCase_4() throws ServletException, IOException {
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.getSession().setAttribute("tipoUtente", "scout");
		request.getSession().setAttribute("utente", scout);
		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}
	
	
	
	//email formato errato allenatore
		@Test
		public void testCase_5() throws ServletException, IOException {
			request.addParameter("password", "Capoo22");
			request.addParameter("email", "maria.natale30@gmail@com");
			request.getSession().setAttribute("tipoUtente", "allenatore");
			request.getSession().setAttribute("utente", allenatore);
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}

		//email esistente allenatore
		@Test
		public void testCase_6() throws ServletException, IOException {
			request.addParameter("password", "Capoo22");
			request.addParameter("email", "pasquale@gmail.com");
			request.getSession().setAttribute("tipoUtente", "allenatore");
			request.getSession().setAttribute("utente", allenatore);
			servlet.doPost(request, response);

			assertEquals(message, response.getContentAsString());
		}

		//password formato errato password allenatore
		@Test
		public void testCase_7() throws ServletException, IOException {
			request.addParameter("password", "Condo r1234");
			request.addParameter("email", "nonhopiuidee@perlemail.com");
			request.getSession().setAttribute("tipoUtente", "allenatore");
			request.getSession().setAttribute("utente", allenatore);
			servlet.doPost(request, response);

			assertEquals(message, response.getContentAsString());
		}



		// successo allenatore
		@Test
		public void testCase_8() throws ServletException, IOException {
			request.addParameter("password", "Condor1234");
			request.addParameter("email", "maria.natale30@gmail.com");
			request.getSession().setAttribute("tipoUtente", "allenatore");
			request.getSession().setAttribute("utente", allenatore);
			servlet.doPost(request, response);

			assertEquals(successMessage, (String) request.getAttribute("result"));
		}


	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
