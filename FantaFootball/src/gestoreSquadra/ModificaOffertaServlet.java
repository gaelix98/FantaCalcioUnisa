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
 * Questa classe � un control che si occupa di passare a OffertaDAO i dati di un�offerta da modificare.
 * @author Maria Natale
 *
 */
@WebServlet("/ModificaOffertaServlet")
public class ModificaOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModificaOffertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @precondition request.getSession().getAttribute(�utente�)!=null and request.getSession().getAttribute(�tipo�).equals(�allenatore�) 
     * and request.getParameter(�data�)!= null and request.getParameter(�lega�)!=null and request.getParameter(�giocatore�) != null and 
     * request.getParameter(�sommaOfferta�) != null and OffertaDAO.getOffertaGiocatoreAllenatore(giocatore, data, lega, squadra)!=null 
     * @postcondition OffertaDAO.getOffertaGiocatoreAllenatore(giocatore, data, lega, squadra).getSomma()=sommaOfferta
     * @throws ServletException, IOException
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
				Giocatore giocatore=giocatoreDAO.getGiocatoreById(idGiocatore);
				
				int numGiocatori=0;
				for (int i=0;squadra.getGiocatori()[i]!=null;i++) {
					numGiocatori++;
				}
				
				if (squadra.getBudgetRimanente()>=sommaOfferta+(25-numGiocatori) && sommaOfferta>=giocatore.getPrezzoBase()) {
					Offerta offerta=offertaDAO.getOffertaByKey(idGiocatore, data, lega, nomeSquadra);
					int diff=offerta.getSomma()-sommaOfferta;
					offerta.setSomma(sommaOfferta);
					offertaDAO.updateOfferta(offerta);
					squadra.setBudgetRimanente(squadra.getBudgetRimanente()+diff);
					squadraDAO.updateSquadra(squadra);
					redirect="leMieOfferte.jsp";
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
