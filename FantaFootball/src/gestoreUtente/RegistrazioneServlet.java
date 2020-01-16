package gestoreUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Questa classe è un control che si occupa di verificare se le credenziali inserite dall’utente sono valide per poi passarle al DAO per
 * registrare l'allenatore.
 * @author Maria Natale
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @precondition request.getParameter(“nome”)!=null and rispetta il formato “^[A-Za-z ]{2,50}$”AND Request.getParameter(“cognome”)!=null
     *  e rispetta il formato “^[A-Za-z ]{2,50}$” AND Request.getParameter(“username”)!=null e
     *   rispetta il formato ^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$”  AND 
     *   Request.getParameter(“password”)!=null and rispetta il formato “^([A-Za-z0-9]){5,}$” AND
     *   Request.getParameter(“email”)!=null e rispetta il formato “^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$” AND
     *   allenatoreDAO.getAllenatoreByUsername(username)==null AND allenatoreDAO.getAllenatoreByEmail(email)==null)
     * @postcondition AllenatoreDAO.getAllenatoreByUsername(username)!=null
     * @throws ServletException, IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
		boolean risultato=false;
		Allenatore allenatore=null;
		String redirect="";
		
		try {
			if (valida(nome, cognome, email, username, password)&& allenatoreDAO.getAllenatoreByUsername(username)==null && allenatoreDAO.getAllenatoreByEmail(email)==null) {
				allenatore=new Allenatore(nome, cognome, email, username, password);
				try {
					risultato=allenatoreDAO.addAllenatore(allenatore);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!risultato) {
			request.setAttribute("message", "Registrazione non effettuata");
			redirect="registrazione.jsp";
			response.getWriter().write("Formato errato dati");
		}
		else {
			redirect="login.jsp";
			request.setAttribute("result", "successo");
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valida(String nome, String cognome, String email, String username, String password) throws SQLException {
		boolean valido=true;
		String expNome="^[A-Za-z ]{2,50}$";
		String expCognome="^[A-Za-z ]{2,50}$";
		String expEmail="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
		String expUsername="^[a-zA-Z0-9]+([a-zA-Z0-9](_|-|)[a-zA-Z0-9])*[a-zA-Z0-9]+$";
		String expPassword="^([A-Za-z0-9]){5,}$";
		if (!Pattern.matches(expNome, nome)) {
			valido=false;
			System.out.print(nome);
		}
		if (!Pattern.matches(expCognome, cognome)) {
			valido=false;
			System.out.print(cognome);
		}
		if (!Pattern.matches(expUsername, username)) {
			valido=false;
			System.out.print(username);
		}	
		if (!Pattern.matches(expPassword, password)) {
			valido=false;
			System.out.print(password);
		}
		if (!Pattern.matches(expEmail, email)) {
			valido=false;
			System.out.print(email);
		}
		
		return valido;
	}

}
