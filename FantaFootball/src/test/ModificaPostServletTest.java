package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import db.DriverManagerConnectionPool;
import gestoreBacheca.Post;
import gestoreBacheca.PostDAO;
import gestoreBacheca.modificaPostServlet;
import gestoreUtente.Scout;

public class ModificaPostServletTest {
	private modificaPostServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Post non modificato";
	final String successMessage="Post modificato";
	private Scout scout;

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new modificaPostServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		scout=new Scout("Paolo", "Conte", "nonhopiuidee@perlemail.com", "LoScarso","bingobangobongo1");
		DatabaseHelper.initializeDatabase();
	}

	// post non valido
	@Test
	public void testCase_1() throws ServletException, IOException, SQLException {
		request.addParameter("idpost", "2");
		request.addParameter("text", "2 punti");
		request.getSession().setAttribute("utente", scout);
		request.getSession().setAttribute("allPost", new ArrayList<Post>());
		request.getSession().setAttribute("post", new ArrayList<Post>());
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	// successo
	@Test
	public void testCase_8() throws ServletException, IOException, SQLException {
		request.addParameter("idpost", "2");
		request.addParameter("text", "shdsfgjk dhrfiuf hdfuhuf dhuihdi s");
		request.getSession().setAttribute("utente", scout);
		request.getSession().setAttribute("allPost", new PostDAO().getAllPost());
		request.getSession().setAttribute("post", new PostDAO().getPostByScout(scout.getUsername()));
		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
