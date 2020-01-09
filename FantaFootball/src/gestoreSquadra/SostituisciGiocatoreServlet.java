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
 * Servlet implementation class SostituisciGiocatoreServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect="inserisciFormazione.jsp";
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
