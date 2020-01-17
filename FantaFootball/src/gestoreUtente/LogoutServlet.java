package gestoreUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreSquadra.*;

/**
 * Questa classe è un control che si occupa di effettuare il logout di un utente.
 * @author Maria Natale
 *
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {  
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null 
	 * @postocondition request.getSession().getAttribute(“utente”)==null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();

		String tipoUtente=(String) session.getAttribute("tipoUtente");
		if (tipoUtente!=null && tipoUtente.equals("allenatore")) {
			Allenatore allenatore=(Allenatore) session.getAttribute("utente");
			try {
				List<Squadra> squadreAllenatore=new SquadraDAO().getSquadreByAllenatore(allenatore.getUsername());
				int giornata=Integer.parseInt(getServletContext().getInitParameter("giornata"));
				FormazioneDAO formazioneDAO=new FormazioneDAO();
				for (Squadra squadra: squadreAllenatore) {
					Formazione formazione=formazioneDAO.getFormazioneBySquadraGiornata(squadra, giornata);
					if (formazione!=null && !formazione.isSchierata()) {
						for (int i=0;i<formazione.getGiocatori().length;i++) {
							if (formazione.getGiocatori()[i]!=null) {
								formazioneDAO.deleteGiocatoreFormazione(formazione, formazione.getGiocatori()[i]);
							}
						}
						for (int i=0;i<formazione.getPanchina().length;i++) {
							if (formazione.getPanchina()[i]!=null) {
								formazioneDAO.deleteGiocatoreFormazione(formazione, formazione.getPanchina()[i]);
							}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		session.setAttribute("tipoUtente", null);
		session.setAttribute("utente", null);
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
