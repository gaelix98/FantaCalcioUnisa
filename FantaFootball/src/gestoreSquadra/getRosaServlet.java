package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreLega.Lega;

/**
 * Questa classe è un control che si occupa di passare a SquadraDAO i dati di una squadra da visualizzare
 */
@WebServlet("/getRosaServlet")
public class getRosaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getRosaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) 
	 * and request.getParameter(“s”) != null 
	 * @postcondition request.getSession().getAttribute(“rosa”)!=null and request.getSession().getAttribute(“squadra”)!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeSquadra=request.getParameter("s");
		HttpSession session=request.getSession();
		Lega lega=(Lega) session.getAttribute("lega");
		Squadra squadra=null;
		Giocatore[] rosa=null;
		try {
			rosa=new GiocatoreDAO().getGiocatoriBySquadra(lega.getNome(), nomeSquadra);
			squadra=new SquadraDAO().getSquadraById(nomeSquadra, lega.getNome());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("rosa", rosa);
		session.setAttribute("squadra", squadra);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("visualizzaRosa.jsp");
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
