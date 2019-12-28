package test;
import java.io.IOException;

import javax.servlet.ServletException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestoreUtente.LoginServlet;

public class LoginServletTest extends Mockito{
	private LoginServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Username o password errati";
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet=new LoginServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	//TC_1.1.1 username errato
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("username", "Salve");
		request.addParameter("password", "Condor1234--");
		
		IllegalArgumentException exceptionThrown =  assertThrows(IllegalArgumentException.class,()-> {
			servlet.doPost(request, response);
		});
		
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_1.1.1 password sbagliata
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("username", "Gaelix98");
		request.addParameter("password", "Terra!");

		IllegalArgumentException exceptionThrown =  assertThrows(IllegalArgumentException.class,()-> {
			servlet.doPost(request, response);
		});
		
		assertEquals(message, exceptionThrown.getMessage());
	}
}
