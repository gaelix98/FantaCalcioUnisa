package gestoreSquadra;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreLega.Asta;
import gestoreLega.AstaDAO;
import gestoreUtente.Allenatore;

/**
 * 
 * @author Maria Natale
 *
 */
@WebServlet("/FaiOffertaServlet")
public class FaiOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FaiOffertaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date data=Date.valueOf(request.getParameter("data"));
		String lega=request.getParameter("lega");
		String nomeSquadra=request.getParameter("squadra");
		Allenatore user=(Allenatore) request.getSession().getAttribute("utente");
		String idGiocatoreS=request.getParameter("giocatore");
		String somma=request.getParameter("sommaOfferta");
		int idGiocatore;
		int sommaOfferta;
		OffertaDAO offertaDAO=new OffertaDAO();
		String redirect="errorPage.jsp";

		if (data!=null && lega!=null && nomeSquadra!=null && idGiocatoreS!=null && somma!=null) {
			idGiocatore=Integer.parseInt(idGiocatoreS);
			sommaOfferta=Integer.parseInt(somma);

			//controllo il budget
			SquadraDAO squadraDAO=new SquadraDAO();
			AstaDAO astaDAO=new AstaDAO();
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			try {
				Squadra squadra=squadraDAO.getSquadraById(nomeSquadra, lega);
				Asta asta=astaDAO.getAstaByKey(data, lega);
				Giocatore giocatore=giocatoreDAO.getGiocatoreById(idGiocatore);
				if (squadra.getBudgetRimanente()>=sommaOfferta+25) {
					if (offertaDAO.getOffertaByKey(giocatore.getId(), data, lega, nomeSquadra)==null) {
						Offerta offerta=new Offerta(squadra, asta, giocatore, sommaOfferta);
						offertaDAO.addOfferta(offerta);
						//aggiorno il budget della squadra
						squadra.setBudgetRimanente(squadra.getBudgetRimanente()-sommaOfferta);
						squadraDAO.updateSquadra(squadra);
						redirect="";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 HttpSession sessione = request.getSession();
         try {
			sessione.setAttribute("offerte", offertaDAO.getAllOfferteByAstaAllenatore(data, lega,user.getUsername()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
