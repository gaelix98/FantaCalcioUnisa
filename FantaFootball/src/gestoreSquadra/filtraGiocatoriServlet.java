package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Maria Natale
 *
 */
@WebServlet("/filtraGiocatoriServlet")
public class filtraGiocatoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public filtraGiocatoriServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prezzoBaseStr=request.getParameter("prezzoBase");
		String squadra=request.getParameter("squadra");
		String ruolo=request.getParameter("ruolo");
		String redirect="";
		String p=request.getParameter("p");
		int prezzoBase;
		List<Giocatore> giocatori=new ArrayList<>();
		GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
		HttpSession session=request.getSession();

		if (prezzoBaseStr.equals("")) {
			prezzoBase=0;
		}
		else {
			prezzoBase=Integer.parseInt(prezzoBaseStr);
		}

		try {
			giocatori=giocatoreDAO.getByPrezzoBase(prezzoBase);
			if (!squadra.equals("")) {
				getGiocatoriSquadra(giocatori, squadra);
			}
			if (!ruolo.equals("")) {
				getGiocatoriRuolo(giocatori, ruolo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		switch(p) {
			case "0": redirect="faiOfferta.jsp"; break;
			case "1": redirect="inserisciFormazione.jsp"; break;
		}

		session.setAttribute("giocatori", giocatori);
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

	private List<Giocatore> getGiocatoriSquadra(List<Giocatore> giocatori, String nomeSquadra){
		List<Giocatore> giocatoriFiltrati=new ArrayList<>();
		for(Giocatore giocatore: giocatori) {
			if (giocatore.getSquadra().equals(nomeSquadra)) {
				giocatoriFiltrati.add(giocatore);
			}
		}
		return giocatoriFiltrati;
	}

	private List<Giocatore> getGiocatoriRuolo(List<Giocatore> giocatori, String ruolo){
		List<Giocatore> giocatoriFiltrati=new ArrayList<>();
		for(Giocatore giocatore: giocatori) {
			if (giocatore.getSquadra().equals(ruolo)) {
				giocatoriFiltrati.add(giocatore);
			}
		}
		return giocatoriFiltrati;
	}

}
