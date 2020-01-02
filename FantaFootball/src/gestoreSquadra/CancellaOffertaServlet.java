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

import gestoreLega.Asta;
import gestoreLega.AstaDAO;

/**
 * 
 * @author Maria Natale
 *
 */
@WebServlet("/CancellaOffertaServlet")
public class CancellaOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancellaOffertaServlet() {
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
