package gestoreUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Questa classe è un control che si occupa di cancellare l’account di un certo utente 
 * @author Maria Natale
 *
 */
@WebServlet("/CancellaProfiloServlet")
public class CancellaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaProfiloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null 
	 * @postcondition AllenatoreDAO.getAllenatoreByUsername(username)==null
	 * @throws ServletException, IOException 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String tipoUtente=(String) session.getAttribute("tipoUtente");
		String redirect="errorPage.jsp";
		if (tipoUtente.equals("scout")) {
			Scout scout=(Scout) session.getAttribute("utente");
			ScoutDAO scoutDAO=new ScoutDAO();
			try {
				if(scoutDAO.deleteScout(scout.getUsername())) {
					redirect="LogoutServlet";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (tipoUtente.equals("allenatore")) {
			Allenatore allenatore=(Allenatore) session.getAttribute("utente");
			AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
			try {
				if(allenatoreDAO.deleteAllenatore(allenatore.getUsername())) {
					redirect="LogoutServlet";
				}
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
