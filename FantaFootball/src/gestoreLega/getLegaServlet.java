package gestoreLega;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreSquadra.Formazione;
import gestoreSquadra.FormazioneDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * 
 * @author Maria Natale
 *
 */
@WebServlet("/getLegaServlet")
public class getLegaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getLegaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @precondition request.getParameter(“q”)!=null and request.getSession().getAttribute(“utente”)!=null 
	 * and request.getSession().getAttribute(“tipo”).equals(“allenatore”)
	 * @postcondition request.getSession().getAttribute(“lega”)!=null and request.getSession().getAttribute(“classifica”)!=null and 
	 * request.getSession().getAttribute(“formazioni”)!=null and request.getSession().getAttribute(“calendario”)!=null and 
	 * request.getSession().getAttribute(“aste”)!=null
	 * @throws ServletException, IOException

	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		LegaDAO legaDAO=new LegaDAO();
		SquadraDAO squadraDAO=new SquadraDAO();
		AstaDAO astaDAO=new AstaDAO();
		PartitaDAO partitaDAO=new PartitaDAO();
		FormazioneDAO formazioneDAO=new FormazioneDAO();
		int giornataAttuale=Integer.parseInt(getServletContext().getInitParameter("giornata"));
		
		try {
			Lega lega=legaDAO.getLegaByNome(request.getParameter("q"));
			List<Squadra> classifica=squadraDAO.getSquadreByLega(lega.getNome());
			
			ArrayList<Formazione> formazioni=new ArrayList<>();
			for (int giornata=1;giornata<=giornataAttuale;giornata++) {
				for (Squadra squadra: classifica) {
					Formazione x=formazioneDAO.getFormazioneBySquadraGiornata(squadra, giornata);
					if (x!=null && x.isSchierata()) {
						formazioni.add(x);
					}
					
				}
			}
			
			List<Partita> calendario=partitaDAO.getAllPartiteLega(lega.getNome());
			List<Asta> aste=astaDAO.getAsteByLega(lega);
			
			session.setAttribute("lega", lega);
			session.setAttribute("classifica", classifica);
			session.setAttribute("formazioni", formazioni);
			session.setAttribute("calendario", calendario);
			session.setAttribute("aste", aste);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("lega.jsp");
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
