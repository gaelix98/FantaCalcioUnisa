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
	 * @precondition request.getSession().getAttribute(“utente”)!=null and Request.getParameter(“password”)!=null and 
	 * rispetta il formato “^([A-Za-z0-9]){5,}$” and Request.getParameter(“email”)!=null and
	 * rispetta il formato “^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$”. 
	 * @postcondition password e/o email sono stati aggiornati
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String tipoUtente=(String) session.getAttribute("tipoUtente");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String redirect="modificaDati.jsp";
		AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
		ScoutDAO scoutDAO=new ScoutDAO();

		if (valida(email, password)) {
			if (tipoUtente.equals("scout")) {
				Scout scout=(Scout) session.getAttribute("utente");

				try {
					if (email.equals(scout.getEmail()) ||( scoutDAO.getScoutByEmail(email)==null && allenatoreDAO.getAllenatoreByEmail(email)==null)) {
						scout.setEmail(email);
						scout.setPassword(password);
						scoutDAO.updateScout(scout);
						session.setAttribute("utente", scout);
						redirect="areaPersonaleScout.jsp";
						request.setAttribute("result", "successo");
					}
					else {
						redirect="modificaDati.jsp";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (tipoUtente.equals("allenatore")) {
				Allenatore allenatore=(Allenatore) session.getAttribute("utente");

				try {
					if (email.equals(allenatore.getEmail()) || (allenatoreDAO.getAllenatoreByEmail(email)==null && scoutDAO.getScoutByEmail(email)==null)) {
						allenatore.setPassword(password);
						allenatore.setEmail(email);
						allenatoreDAO.updateAllenatore(allenatore);
						session.setAttribute("utente", allenatore);
						redirect="areaPersonaleAllenatore.jsp";
						request.setAttribute("result", "successo");
					}
					else {
						redirect="modificaDati.jsp";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		}

		if (redirect.equals("modificaDati.jsp")) {
			request.setAttribute("message", "Dati non modificati");
			response.getWriter().write("Formato errato dati");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean valida(String email,String password) {
		boolean valido=true;
		String expEmail="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
		String expPassword="^([A-Za-z0-9]){5,}$";
		if (!Pattern.matches(expPassword, password)) {
			valido=false;
		}
		if (!Pattern.matches(expEmail, email)) {
			valido=false;
		}
		return valido;
	}

}
