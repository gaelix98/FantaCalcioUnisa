package gestoreLega;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreSquadra.Giocatore;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * Servlet implementation class VisualizzaPartitaServlet
 */
@WebServlet("/VisualizzaPartitaServlet")
public class VisualizzaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeLega = request.getParameter("l");
		String nomeSquadra1 = request.getParameter("s1");
		String nomeSquadra2 = request.getParameter("s2");
		String stringGiornata = request.getParameter("g");
		SquadraDAO squadraDAO = new SquadraDAO();
		PartitaDAO partitaDAO = new PartitaDAO();
		String redirect = "errorPage.jsp";
		if(nomeLega!= null && nomeSquadra1 != null && nomeSquadra2 != null && stringGiornata != null) {
			int giornata = Integer.parseInt(stringGiornata);
			try {
				Squadra squadra1 = squadraDAO.getSquadraById(nomeSquadra1, nomeLega);
				Squadra squadra2 = squadraDAO.getSquadraById(nomeSquadra2,nomeLega);
				if(squadra1!=null && squadra2 !=null) {
					Partita p = partitaDAO.getPartitaById(squadra1, squadra2, giornata);
					request.setAttribute("partita", p);
					redirect="partita.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		request.getRequestDispatcher(redirect).forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void ottieniVoti() {
		HashMap<Giocatore, Float> voti = new HashMap<Giocatore, Float>();
		
	}

}
