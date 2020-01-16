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
 * 
 * Questa classe è un control che si occupa di passare a FormazioneDAO i dati due giocatori da scambiare nella formazione
 *
 */

@WebServlet("/SostituisciGiocatoreServlet")
public class SostituisciGiocatoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SostituisciGiocatoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”)
	 * and request.getParameter(“giocatore1”)!=null and request.getParameter(“giocatore2”)!=null and 
	 * SquadraDAO.getSquadraById(squadra.getNome(), squadra.getLega().getNome()).getGiocatori().contains(giocatore1) == true
	 * @postcondition  SquadraDAO.getSquadraById(squadra.getNome(), squadra.getLega().getNome()).getFormazione().getGiocatori().contains(giocatore1) == false and
	 * SquadraDAO.getSquadraById(squadra.getNome(), squadra.getLega().getNome()).getFormazione().getGiocatori().contains(giocatore2) == true
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect="NewFormazione.jsp";
		int idGiocatore1=Integer.parseInt(request.getParameter("giocatore1"));
		int idGiocatore2=Integer.parseInt(request.getParameter("giocatore2"));
		
		try {
			Formazione formazione=(Formazione) request.getSession().getAttribute("formazione");
			Giocatore giocatoreDaSostituire=new GiocatoreDAO().getGiocatoreById(idGiocatore1);
			Giocatore giocatoreSostituto=new GiocatoreDAO().getGiocatoreById(idGiocatore2);
			
			boolean sostituito=false;
			for (int i=0;i<formazione.getGiocatori().length &&!sostituito;i++) {
				if (formazione.getGiocatori()[i].getId()==idGiocatore1) {
					formazione.getGiocatori()[i]=giocatoreSostituto;
					sostituito=true;
				}
			}
			
			for (int i=0;i<formazione.getPanchina().length && !sostituito; i++) {
				if (formazione.getPanchina()[i].getId()==idGiocatore1) {
					formazione.getPanchina()[i]=giocatoreSostituto;
					sostituito=true;
				}
			}
			
			new FormazioneDAO().updateGiocatoreFormazione(formazione, giocatoreDaSostituire, giocatoreSostituto);
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
