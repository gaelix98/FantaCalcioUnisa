package gestoreSquadra;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.regex.Pattern;

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
 * Questa classe è un control che si occupa di passare a OffertaDAO i dati di un’offerta da registrare
 * @author Maria Natale
 *
 */
@WebServlet("/FaiOffertaServlet")
public class FaiOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public FaiOffertaServlet() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) 
	 * and request.getParameter(“data”)!= null and request.getParameter(“lega”)!=null and request.getParameter(“giocatore”) != null 
	 * and request.getParameter(“sommaOfferta”) != null and SquadraDAO.getSquadraById(squadra, lega).getBudgetRimanente()>=sommaOfferta
	 * @postcondition OffertaDAO.getOffertaGiocatoreAllenatore(giocatore, data, lega, squadra)!=null 
	 * @throws ServletException, IOException 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SquadraDAO squad = new SquadraDAO();
		Allenatore allenatore =(Allenatore) request.getSession().getAttribute("utente");
		String nomeSquadra=null;
		try {
			
			nomeSquadra = squad.getSquadraByUserELega(allenatore.getUsername(),request.getParameter("lega")).getNome();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Date data=Date.valueOf(request.getParameter("data"));
		String lega=request.getParameter("lega");
		
		String idGiocatoreS=request.getParameter("giocatore");
		String somma=request.getParameter("sommaOfferta");
		int idGiocatore;
		int sommaOfferta;
		OffertaDAO offertaDAO=new OffertaDAO();
		String redirect="errorPage.jsp";
		String regex="^[0-9]{1,3}$";
		

		if (data!=null && lega!=null && nomeSquadra!=null && idGiocatoreS!=null && somma!=null && Pattern.matches(regex,somma)) {
			idGiocatore=Integer.parseInt(idGiocatoreS);
			
			sommaOfferta=Integer.parseInt(somma);

			//controllo il budget
			SquadraDAO squadraDAO=new SquadraDAO();
			AstaDAO astaDAO=new AstaDAO();
			GiocatoreDAO giocatoreDAO=new GiocatoreDAO();
			try {
				Squadra squadra=squadraDAO.getSquadraById(nomeSquadra, lega);
				Asta asta=astaDAO.getAstaByKey(data, lega);
				
				int numGiocatori=0;
				for (int i=0;squadra.getGiocatori()[i]!=null;i++) {
					numGiocatori++;
				}
	
				Giocatore giocatore=giocatoreDAO.getGiocatoreById(idGiocatore);
				if (squadra.getBudgetRimanente()>=sommaOfferta+(25-numGiocatori) && sommaOfferta>=giocatore.getPrezzoBase()) {
					if (offertaDAO.getOffertaByKey(giocatore.getId(), data, lega, nomeSquadra)==null) {
						Offerta offerta=new Offerta(squadra, asta, giocatore, sommaOfferta);
						offertaDAO.addOfferta(offerta);
						//aggiorno il budget della squadra
						squadra.setBudgetRimanente(squadra.getBudgetRimanente()-sommaOfferta);
						squadraDAO.updateSquadra(squadra);
						redirect="leMieOfferte.jsp";
						request.setAttribute("result", "successo");
					}
					else {
						response.getWriter().write("Offerta non effettuata");
					}
				}
				else {
					response.getWriter().write("Offerta non effettuata");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			response.getWriter().write("Offerta non effettuata");
		}
		 HttpSession sessione = request.getSession();
         try {
			sessione.setAttribute("offerte", offertaDAO.getAllOfferteByAstaAllenatore(data, lega,allenatore.getUsername()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
