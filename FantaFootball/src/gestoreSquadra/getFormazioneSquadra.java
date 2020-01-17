package gestoreSquadra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreLega.Lega;
import gestoreUtente.Allenatore;

/**
 * Questa classe è un control che si occupa di salvare in sessione la formazione attuale della squadra
 * @author Maria Natale
 *
 */
@WebServlet("/getFormazioneSquadra")
public class getFormazioneSquadra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFormazioneSquadra() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @preconditionrequest.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) 
	 * and request.getSession().getAttribute(“lega”)!=null
	 * @postcondition request.getSession().getAttribute(“formazione”)!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SquadraDAO squadraDAO = new SquadraDAO();
		Allenatore allenatore =(Allenatore) request.getSession().getAttribute("utente");
		System.out.println("porcodio");
		Squadra squadra=null;
		String redirect="NewFormazione.jsp";
		Lega lega=(Lega) request.getSession().getAttribute("lega");

		try {
			squadra = squadraDAO.getSquadraByUserELega(allenatore.getUsername(), lega.getNome());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int giornata=Integer.parseInt(getServletContext().getInitParameter("giornata"));
		if (squadra!=null) {
			try {
				Formazione formazione=new FormazioneDAO().getFormazioneBySquadraGiornata(squadra, giornata);
				if(formazione==null) {
					
					formazione= new Formazione(giornata, squadra);
				}
				request.getSession().setAttribute("formazione", formazione);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
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
