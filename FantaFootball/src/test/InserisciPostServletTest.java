package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreBacheca.InserisciPostServlet;
import gestoreBacheca.Post;
import gestoreUtente.RegistrazioneServlet;
import gestoreUtente.Scout;

public class InserisciPostServletTest {
	private InserisciPostServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Post non inserito";
	final String successMessage="Post inserito";
	private Scout scout;

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new InserisciPostServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		scout=new Scout("Angelo", "Coralluzzo", "vogliofarejungler@mannaggiagaetano.it", "LoScarso2","imsohappyinthejungle1");
		DatabaseHelper.initializeDatabase();
	}

	//TC_1.9.1 post non valido
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("titolo", "post di test");
		request.addParameter("testo", "2 punti");
		request.getSession().setAttribute("utente", scout);
		request.getSession().setAttribute("allPost", new ArrayList<Post>());
		request.getSession().setAttribute("post", new ArrayList<Post>());
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.2.8 successo
	@Test
	public void testCase_8() throws ServletException, IOException {
		request.addParameter("titolo", "post di test");
		request.addParameter("testo", "i 6 calciatori da piazzare in campo");
		request.getSession().setAttribute("utente", scout);
		request.getSession().setAttribute("allPost", new ArrayList<Post>());
		request.getSession().setAttribute("post", new ArrayList<Post>());
		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
