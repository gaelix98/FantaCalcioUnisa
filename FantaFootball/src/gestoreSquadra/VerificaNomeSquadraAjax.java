package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreLega.LegaDAO;

/**
 * Servlet implementation class VerificaNomeSquadraAjax
 */
@WebServlet("/VerificaNomeSquadraAjax")
public class VerificaNomeSquadraAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaNomeSquadraAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String squadra=request.getParameter("s");
		String lega=request.getParameter("l");
		SquadraDAO squadraDAO=new SquadraDAO();
		boolean result=false;
		if (squadra != null && lega!=null) {
			try {
				if(squadraDAO.getSquadraById(squadra, lega)!=null) {
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
