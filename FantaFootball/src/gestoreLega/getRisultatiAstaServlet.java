package gestoreLega;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreSquadra.Giocatore;
import gestoreSquadra.Offerta;
import gestoreSquadra.OffertaDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * Servlet implementation class getRisultatiAstaServlet
 */
@WebServlet("/getRisultatiAstaServlet")
public class getRisultatiAstaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRisultatiAstaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lega lega= (Lega)request.getSession().getAttribute("lega");
		String dataInizio=request.getParameter("q");
		String redirect="risultatiAsta.jsp";
		OffertaDAO offertaDAO = new OffertaDAO();
		Asta asta=null;
		AstaDAO astaDAO=new AstaDAO();
		ArrayList<Offerta> offerte=new ArrayList<>();
		if (lega!=null && !dataInizio.equals("")) {
			Date data=Date.valueOf(dataInizio);
			try {
				asta=astaDAO.getAstaByKey(data, lega.getNome());
				offerte = offertaDAO.getOfferteVincentiByAsta(data, lega.getNome());
			} catch (SQLException e) {
				e.printStackTrace();
			}  
			
		}
		
		request.setAttribute("risultatiAsta", offerte); 
		request.setAttribute("asta", asta);
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
