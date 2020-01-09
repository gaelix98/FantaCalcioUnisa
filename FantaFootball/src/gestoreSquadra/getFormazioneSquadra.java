package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreLega.Lega;
import gestoreUtente.Allenatore;

/**
 * Servlet implementation class getFormazioneSquadra
 */
@WebServlet("/getFormazioneSquadra")
public class getFormazioneSquadra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFormazioneSquadra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SquadraDAO squadraDAO = new SquadraDAO();
		Allenatore allenatore =(Allenatore) request.getSession().getAttribute("utente");
		Squadra squadra=null;
		String redirect="inserisciFormazione.jsp";
		Lega lega=(Lega) request.getSession().getAttribute("lega");

		try {
			squadra = squadraDAO.getSquadraByUserELega(allenatore.getUsername(), lega.getNome());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int giornata=Integer.parseInt(getServletContext().getInitParameter("giornata"));
		if (squadra!=null) {
			try {
				Formazione formazione=new FormazioneDAO().getFormazioneBySquadraGiornata(squadra, giornata);
				request.getSession().setAttribute("formazione", formazione);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			redirect="errorPage.jsp";
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