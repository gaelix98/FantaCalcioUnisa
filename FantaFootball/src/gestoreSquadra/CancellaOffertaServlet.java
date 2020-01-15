package gestoreSquadra;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

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
 * Questa classe è un control che si occupa di passare a OffertaDAO i dati di un’offerta da cancellare.
 * @author Maria Natale
 *
 */
@WebServlet("/CancellaOffertaServlet")
public class CancellaOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CancellaOffertaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) and 
	 * request.getParameter(“data”)!= null and request.getParameter(“lega”)!=null and request.getParameter(“giocatore”) != null and
	 * OffertaDAO.getOffertaGiocatoreAllenatore(giocatore, data, lega, squadra)!=null
	 * @postcondition OffertaDAO.getOffertaGiocatoreAllenatore(giocatore, data, lega, squadra)=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date data=Date.valueOf(request.getParameter("data"));
		String lega=request.getParameter("lega");
		Allenatore user=(Allenatore) request.getSession().getAttribute("utente");
		String nomeSquadra=request.getParameter("squadra");
		String idGiocatoreS=request.getParameter("giocatore");
		int idGiocatore;
		OffertaDAO offertaDAO=new OffertaDAO();
		String redirect="errorPage.jsp";
		

		if (data!=null && lega!=null && nomeSquadra!=null && idGiocatoreS!=null) {
			idGiocatore=Integer.parseInt(idGiocatoreS);
			SquadraDAO squadraDAO=new SquadraDAO();
			AstaDAO astaDAO=new AstaDAO();
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			try {
				Squadra squadra=squadraDAO.getSquadraById(nomeSquadra, lega);
				Asta asta=astaDAO.getAstaByKey(data, lega);
				Giocatore giocatore=giocatoreDAO.getGiocatoreById(idGiocatore);
				Offerta offerta=offertaDAO.getOffertaByKey(idGiocatore, data, lega, nomeSquadra);
				offertaDAO.deleteOfferta(offerta);
				squadra.setBudgetRimanente(squadra.getBudgetRimanente()+offerta.getSomma());
				squadraDAO.updateSquadra(squadra);
				redirect="";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
         HttpSession sessione = request.getSession();
         try {
			sessione.setAttribute("offerte", offertaDAO.getAllOfferteByAstaAllenatore(data, lega,user.getUsername()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
