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
 * Servlet implementation class rimuoviGiocatoreFormazioneServlet
 */
@WebServlet("/rimuoviGiocatoreFormazioneServlet")
public class RimuoviGiocatoreFormazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviGiocatoreFormazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect="inserisciFormazione.jsp";
		int idGiocatore=Integer.parseInt(request.getParameter("giocatore"));
		try {
			Formazione formazione=(Formazione) request.getSession().getAttribute("formazione");
			Giocatore giocatore=new GiocatoreDAO().getGiocatoreById(idGiocatore);
			
			boolean rimosso=false;
			for (int i=0;i<formazione.getGiocatori().length &&!rimosso;i++) {
				if (formazione.getGiocatori()[i].getId()==idGiocatore) {
					formazione.getGiocatori()[i]=null;
					rimosso=true;
				}
			}
			
			for (int i=0;i<formazione.getPanchina().length && !rimosso; i++) {
				if (formazione.getPanchina()[i].getId()==idGiocatore) {
					formazione.getPanchina()[i]=null;
					rimosso=true;
				}
			}
			
			new FormazioneDAO().deleteGiocatoreFormazione(formazione, giocatore);
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
