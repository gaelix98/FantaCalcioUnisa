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

import gestoreLega.Lega;
import gestoreUtente.Allenatore;

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

		HttpSession session=request.getSession();
	

		switch(p) {
		case "0":
			GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
			if (prezzoBaseStr.equals("")) {
				prezzoBase=0;
			}
			else {
				prezzoBase=Integer.parseInt(prezzoBaseStr);
			}

			try {
				giocatori=giocatoreDAO.getByPrezzoBase(prezzoBase);
				if (!squadra.equals("")) {
					giocatori=getGiocatoriSquadra(giocatori, squadra);

				}
				if (!ruolo.equals("")) {
					giocatori=getGiocatoriRuolo(giocatori, ruolo);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}; break;
		case "1":
			SquadraDAO sD= new SquadraDAO();
			Allenatore a=(Allenatore) session.getAttribute("utente");
			Lega l=(Lega) session.getAttribute("lega");
			
			//request.getSession().setAttribute("modulo", request.getParameter("modulo"));
			try {
				Squadra sq = sD.getSquadraByUserELega(a.getUsername(),l.getNome() );
				System.out.println(sq.getNome());
				Giocatore[] x= sq.getGiocatori(); //uno mi da l'array e l'altro è una lista, faccio bordelli 
				for(int i=0;i<x.length && x[i]!=null;i++) {
					giocatori.add(x[i]); //ho riempito la lista, ma ho fatto na bestemmia;
					System.out.println(giocatori.get(i));
					//qua mi da null

				}
				if (!ruolo.equals("")) {
					giocatori=getGiocatoriRuolo(giocatori, ruolo);

				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			;
			break;
		}
		switch(p) {
		case "0": redirect="faiOfferta.jsp";break;
		case "1": redirect="NewFormazione.jsp"; break;
		}

		session.setAttribute("giocatori", giocatori);
		for(int i=0;i<giocatori.size();i++) {
			System.out.println(giocatori.get(i).getNome());
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
			if (giocatore.getRuolo().equals(ruolo)) {
				giocatoriFiltrati.add(giocatore);
			}
		}
		return giocatoriFiltrati;
	}

}
