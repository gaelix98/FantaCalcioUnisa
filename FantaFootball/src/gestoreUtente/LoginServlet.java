package gestoreUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreBacheca.Post;
import gestoreBacheca.PostDAO;
import gestoreLega.Invito;
import gestoreLega.InvitoDAO;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreSquadra.Scambio;
import gestoreSquadra.ScambioDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * 
 * @author Maria Natale
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
		ScoutDAO scoutDAO=new ScoutDAO();
		Allenatore allenatore=null;
		Scout scout=null;
		String redirect="";
		session.setAttribute("tipoUtente", null);
		
		try {
			if (allenatoreDAO.checkLogin(username, password)) {
				allenatore=allenatoreDAO.getAllenatoreByUsername(username);
				redirect="index.jsp";
				
				LegaDAO legaDAO=new LegaDAO();
				//cerco le leghe di cui l'allenatore è presidente
				ArrayList<Lega> legheCreate=legaDAO.getLegheByPresidente(allenatore);
				
				SquadraDAO squadraDAO=new SquadraDAO();
				ArrayList<Squadra> squadreAllenatore=squadraDAO.getSquadreByAllenatore(allenatore.getUsername());
				//cerco le leghe di cui l'allenatore fa parte ma non è il presidente
				ArrayList<Lega> leghe=new ArrayList<>();
				for (Squadra squadra: squadreAllenatore) {
					if (!squadra.getAllenatore().getUsername().equals(squadra.getLega().getPresidente().getUsername())) {
						leghe.add(squadra.getLega());
					}
				}
				
				//cerco gli scambi da accettare
				ScambioDAO scambioDAO=new ScambioDAO();
				ArrayList<Scambio> scambi=new ArrayList<>();
				for (Squadra squadra: squadreAllenatore) {
					ArrayList<Scambio> scambiSquadra=scambioDAO.getScambiNonAccettatiSquadra(squadra);
					scambi.addAll(scambiSquadra);
				}
				
				//cerco inviti da accettare
				InvitoDAO invitoDAO=new InvitoDAO();
				ArrayList<Invito> inviti=(ArrayList<Invito>) invitoDAO.getInvitoByAllenatore(allenatore.getUsername());
				
				session.setAttribute("utente", allenatore);
				session.setAttribute("tipoUtente", "allenatore");
				session.setAttribute("legheCreate", legheCreate);
				session.setAttribute("leghe", leghe);
				session.setAttribute("scambi", scambi);
				session.setAttribute("inviti", inviti);
			}
			else if(scoutDAO.checkLogin(username, password)) {
				scout=scoutDAO.getScoutByUsername(username);
				redirect="index.jsp";
				
				
				PostDAO postDAO=new PostDAO();
				ArrayList<Post> post=postDAO.getPostByScout(scout.getUsername());
				session.setAttribute("utente", scout);
				session.setAttribute("tipoUtente", "scout");
				session.setAttribute("post", post);
			}
			else {
				redirect="login.jsp";
				request.setAttribute("message", "Username o password errati!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			redirect="errorPage.jsp";
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
