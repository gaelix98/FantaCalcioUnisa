package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreUtente.Allenatore;

/**
 * Servlet implementation class InserisciGiocatoreFormazioneServlet
 */
@WebServlet("/InserisciGiocatoreFormazioneServlet")
public class InserisciGiocatoreFormazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserisciGiocatoreFormazioneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect="inserisciFormazione.jsp";
		String modulo=request.getParameter("modulo");
		int difensori=modulo.charAt(0);
		int centrocampisti=modulo.charAt(2);
		int attaccanti=modulo.charAt(4);
		String titolare=request.getParameter("titolare");

		int idGiocatore=Integer.parseInt(request.getParameter("giocatore"));
		try {
			Formazione formazione=(Formazione) request.getSession().getAttribute("formazione");
			Giocatore giocatore=new GiocatoreDAO().getGiocatoreById(idGiocatore);
			if (titolare.equals("true")) {
				int i=0, j=0;
				switch(giocatore.getRuolo()) {
				case "Por":
					formazione.getGiocatori()[0]=giocatore;
					break;
				case "Dif":
					i=1;
					j=i+difensori;
					break;
				case "Cen":
					i=difensori+1;
					j=i+centrocampisti;
					break;
				case "Att":
					i=difensori+centrocampisti+1;
					j=i+attaccanti;
					break;
				}

				for (;i<j && formazione.getGiocatori()[i]!=null; i++) {
				}
				formazione.getGiocatori()[i]=giocatore;
				new FormazioneDAO().addGiocatoreFormazione(formazione, giocatore, i);
			}
			else {
				int i=0;
				for (;i<formazione.getPanchina().length && formazione.getPanchina()[i]!=null; i++) {
				}
				formazione.getPanchina()[i]=giocatore;
				new FormazioneDAO().addGiocatoreFormazione(formazione, giocatore, i+11);
			}

			request.getSession().setAttribute("formazione", formazione);

		} catch (SQLException e) {
			e.printStackTrace();
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