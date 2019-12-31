package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreUtente.RegistrazioneServlet;

public class RegistrazioneServletTest {
	private RegistrazioneServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Formato errato dati";
	final String successMessage="successo";

	@BeforeEach
	public void setUp() throws Exception {
		DriverManagerConnectionPool.setTest(true);
		servlet=new RegistrazioneServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	//TC_1.2.1 username errato
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("username", "Gae lix98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "Coralluzzo");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.2 password errata
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("username", "Gae lix98");
		request.addParameter("password", "Cond or1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "Coralluzzo");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.3 username già usato
	@Test
	public void testCase_3() throws ServletException, IOException {
		request.addParameter("username", "pasquale98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "Coralluzzo");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.4 email formato errato
	@Test
	public void testCase_4() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria@natale30@gmail@com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "Coralluzzo");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.5 email già usata
	@Test
	public void testCase_5() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "pasquale@gmail.com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "Coralluzzo");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}


	//TC_1.2.6 nome errato
	@Test
	public void testCase_6() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.addParameter("nome", "P");
		request.addParameter("cognome", "Coralluzzo");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.7 cognome errato
	@Test
	public void testCase_7() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "C");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.8 successo
	@Test
	public void testCase_8() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Condor1234");
		request.addParameter("email", "maria.natale30@gmail.com");
		request.addParameter("nome", "Pasquale");
		request.addParameter("cognome", "Coralluzzo");

		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
