package gestoreLega;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import init.GestoreCalendario;

/**
 * Servlet implementation class OrganizzaPartite
 */
@WebServlet("/OrganizzaPartiteServlet")
public class OrganizzaPartiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganizzaPartiteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeLega = request.getParameter("nomeLega");
		PartitaDAO partitaDAO = new PartitaDAO();
		SquadraDAO squadraDAO = new SquadraDAO();
		if(nomeLega != null && !nomeLega.equals("")) {
			try {
				List<Partita> partite = partitaDAO.getAllPartiteLega(nomeLega);
				if(partite.isEmpty()) {
					List<Squadra> squadreList = squadraDAO.getSquadreByLega(nomeLega);
					Squadra[] squadre = new Squadra[squadreList.size()];
					squadre =  squadreList.toArray(squadre);
					partite = new GestoreCalendario().creaCalendario(squadre);
					for(Partita p: partite) {
						partitaDAO.addPartita(p);
					}
					request.getSession().setAttribute("calendario", partite);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("calendario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
