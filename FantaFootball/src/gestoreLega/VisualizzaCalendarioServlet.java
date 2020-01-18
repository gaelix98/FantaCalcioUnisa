package gestoreLega;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

/**
 * Servlet implementation class VisualizzaCalendarioServlet
 */
@WebServlet("/VisualizzaCalendarioServlet")
public class VisualizzaCalendarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaCalendarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String giornataString = request.getParameter("giornata");
		String lega = request.getParameter("nomeLega");
		List<Partita> partite = null;
		PartitaDAO partitaDAO = new PartitaDAO();
		String redirect = "errorPage.jsp";
		if(giornataString != null && !giornataString.equals("") && lega != null && !lega.equals("")) {
			try {
				int giornata = Integer.parseInt(giornataString);
				partite = partitaDAO.getAllPartiteByGiornataLega(giornata, lega);
				if(!partite.isEmpty()) {
					request.setAttribute("giornata", giornata);
					request.setAttribute("calendario",partite);
					redirect = "calendario.jsp";
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
