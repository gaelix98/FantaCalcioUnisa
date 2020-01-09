package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class salvaFormazione
 */
@WebServlet("/salvaFormazione")
public class SalvaFormazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvaFormazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect="inserisciFormazione.jsp";
		Formazione formazione=(Formazione) request.getSession().getAttribute("formazione");
		formazione.setSchierata(true);
		boolean error=false;
		for (int i=0;i<formazione.getGiocatori().length && !error; i++) {
			if (formazione.getGiocatori()[i]==null) {
				error=true;
			}
		}
		
		for (int i=0;i<formazione.getPanchina().length && !error; i++) {
			if (formazione.getGiocatori()[i]==null) {
				error=true;
			}
		}
		
		if (!error) {
			try {
				new FormazioneDAO().updateFormazione(formazione);
				request.getSession().setAttribute("formazione", formazione);
			} catch (SQLException e) {
				e.printStackTrace();
				redirect="errorPage.jsp";
			}
		}
		else {
			request.setAttribute("message", "Impossibile confermare la formazione. Giocatori mancanti");
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

}
