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
 * Servlet implementation class getRosaServlet
 */
@WebServlet("/getRosaServlet")
public class getRosaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRosaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
