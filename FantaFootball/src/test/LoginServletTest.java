package test;
import java.io.IOException;

import javax.servlet.ServletException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreUtente.LoginServlet;

public class LoginServletTest extends Mockito{
	private LoginServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message ="Email e/o password non validi";
	final String successMessage="successo";
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet=new LoginServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	//TC_1.1.1 username errato
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("username", "Salve");
		request.addParameter("password", "Condor1234");

		servlet.doPost(request, response);
		
		assertEquals(message, response.getContentAsString());
	}

	//TC_1.1.2 password sbagliata
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Terra!");

		servlet.doPost(request, response);
		
		assertEquals(message, response.getContentAsString());
	}

	//TC_1.1.3 username e password corretti
	@Test
	public void testCase_3() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Condor1234");

		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
