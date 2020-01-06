package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class prendiGiocatore
 */
@WebServlet("/prendiGiocatore")
public class PrendiGiocatoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrendiGiocatoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id= request.getParameter("id");
		int idg;
		String redirect="errorPage.jsp";
		Giocatore preso= null;
		if (id!=null) {
			idg=Integer.parseInt(id);
			GiocatoreDAO giocatored = new GiocatoreDAO();
			try {
				preso=giocatored.getGiocatoreById(idg);
				if(preso!=null) {
				redirect="faiDavveroOfferta.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("giocatore", preso);
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
