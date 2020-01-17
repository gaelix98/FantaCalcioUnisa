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
 * 
 * Questa classe è un control che si occupa di passare a FormazioneDAO i dati di un giocatore da aggiungere ad una formazione
 *
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
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) 
	 * and request.getParameter(“modulo”)!=null and request.getParameter(“titolare”)!=null and request.getParameter(“giocatore”)!=null
	 * @postcondition  SquadraDAO.getSquadraById(squadra.getNome(), squadra.getLega().getNome()).getFormazione().contains(giocatore) == true
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect="NewFormazione.jsp";

		String modulo=(String) request.getSession().getAttribute("modulo");
		int difensori=Integer.parseInt(String.valueOf(modulo.charAt(0)));
		int centrocampisti=Integer.parseInt(String.valueOf(modulo.charAt(2)));
		int attaccanti=Integer.parseInt(String.valueOf(modulo.charAt(4)));
		String titolare=request.getParameter("titolare");
		System.out.println(titolare);
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
				case "Def":
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

				for (; i<formazione.getGiocatori().length && i<j; 
						i++) {
					if(formazione.getGiocatori()[i]==null) {
						formazione.getGiocatori()[i]=giocatore;
						break;
					}
				}

				new FormazioneDAO().addGiocatoreFormazione(formazione, giocatore, i);
			}
			else {
				int i=0, j=0;
				switch(giocatore.getRuolo()) {
				case "Por":
					formazione.getGiocatori()[0]=giocatore;
					break;
				case "Def":
					i=1;
					j=i+2;
					break;
				case "Cen":
					i=3;
					j=i+2;
					break;
				case "Att":
					i=5;
					j=i+2;
					break;
				}
				for (;i<formazione.getPanchina().length; i++) {
					if(formazione.getPanchina()[i]==null) {
						formazione.getPanchina()[i]=giocatore;
						break;
					}
				}
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
