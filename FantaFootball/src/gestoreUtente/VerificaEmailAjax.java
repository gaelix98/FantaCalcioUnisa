package gestoreUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class verificaEmailAjax
 */
@WebServlet("/VerificaEmailAjax")
public class VerificaEmailAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerificaEmailAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("q");

		ScoutDAO scoutDAO=new ScoutDAO();
		AllenatoreDAO allenatoreDAO=new AllenatoreDAO();
		boolean result=false;
		if (query != null) {
			try {
				if(allenatoreDAO.getAllenatoreByEmail(query)!=null|| scoutDAO.getScoutByEmail(query)!=null) {
					result=true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
