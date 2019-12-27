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
import javax.servlet.http.HttpSession;

/**
 * Questa classe è un control che si occupa di cambiare le credenziali dell’utente, prima verifica che queste rispettino gli standard
 * e poi chiama il dao addetto all’update delle credenziali.
 * @author Maria Natale
 *
 */
@WebServlet("/ModificaDatiPersonaliServlet")
public class ModificaDatiPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaDatiPersonaliServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String tipoUtente=(String) session.getAttribute("tipoUente");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String redirect="errorPage.jsp";

		if (valida(email, password)) {
			if (tipoUtente.equals("scout")) {
				Scout scout=(Scout) session.getAttribute("utente");
				ScoutDAO scoutDAO=new ScoutDAO();
				scout.setEmail(email);
				scout.setPassword(password);
				try {
					scoutDAO.updateScout(scout);
					session.setAttribute("utente", scout);
					redirect="areaPersonaleScout.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (tipoUtente.equals("allenatore")) {
				Allenatore allenatore=(Allenatore) session.getAttribute("utente");
				AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
				allenatore.setPassword(password);
				allenatore.setEmail(email);
				try {
					allenatoreDAO.updateAllenatore(allenatore);
					session.setAttribute("utente", allenatore);
					redirect="areaPersonaleAllenatore.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean valida(String email,String password) {
		boolean valido=true;
		String expEmail="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
		String expPassword="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$";
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
