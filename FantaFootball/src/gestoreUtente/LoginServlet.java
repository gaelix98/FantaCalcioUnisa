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
 * Servlet implementation class LoginServlet
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
		
		try {
			if (allenatoreDAO.checkLogin(username, password)) {
				allenatore=allenatoreDAO.getAllenatoreByUsername(username);
				redirect="index.jsp";
				session.setAttribute("utente", allenatore);
				session.setAttribute("tipoUtente", "allenatore");
			}
			else if(scoutDAO.checkLogin(username, password)) {
				scout=scoutDAO.getScoutByUsername(username);
				redirect="index.jsp";
				session.setAttribute("utente", scout);
				session.setAttribute("tipoUtente", "scout");
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
