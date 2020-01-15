package gestoreLega;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Offerta;
import gestoreSquadra.OffertaDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import gestoreUtente.Allenatore;

/**
 * Questa classe è un control che si occupa di prendere i risultati di una certa Asta di un certo Allenatore.
 */
@WebServlet("/getRisultatiMiaAstaServlet")
public class getRisultatiMiaAstaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRisultatiMiaAstaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) 
	 * and request.getParameter(“q”)!=null and request.getSession().getAttribute(“lega”)!=null
	 * @post request.getAttribute(“giocatoreSquadra”)!=null and request.setAttribute(“asta”)!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lega lega= (Lega)request.getSession().getAttribute("lega");
		Allenatore allenatore=(Allenatore) request.getSession().getAttribute("utente");
		String dataInizio=request.getParameter("q");
		String redirect="risultatiMiaAsta.jsp";
		OffertaDAO offertaDAO=new OffertaDAO();
		List<Offerta> offerte=new ArrayList<>();
		HashMap giocatoreSquadra=new HashMap();
		SquadraDAO squadraDAO=new SquadraDAO();
		AstaDAO astaDAO=new AstaDAO();
		Asta asta=null;
		
		if (allenatore!=null && lega!=null && !dataInizio.equals("")) {
			try {
				asta=astaDAO.getAstaByKey(Date.valueOf(dataInizio), lega.getNome());
				offerte=offertaDAO.getAllOfferteByAstaAllenatore(Date.valueOf(dataInizio), lega.getNome(), allenatore.getUsername());
				List<Giocatore> giocatoriMieOfferte=new ArrayList<>();
				for (Offerta o: offerte) {
					giocatoriMieOfferte.add(o.getGiocatore());
				}
				
				for (Giocatore giocatore: giocatoriMieOfferte) {
					List<Squadra> squadre=squadraDAO.getSquadreGiocatore(giocatore);
					for (Squadra squadra: squadre) {
						if (squadra.getLega().getNome().equals(lega.getNome())) {
							giocatoreSquadra.put(giocatore, squadra);
						}
					}
					
				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		request.setAttribute("giocatoreSquadra", giocatoreSquadra);
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
