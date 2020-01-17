package gestoreBacheca;

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

import gestoreLega.Asta;
import gestoreLega.AstaDAO;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreLega.Partita;
import gestoreLega.PartitaDAO;
import gestoreSquadra.Formazione;
import gestoreSquadra.FormazioneDAO;
import gestoreSquadra.Giocatore;
import gestoreSquadra.GiocatoreDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import gestoreUtente.ScoutDAO;


@WebServlet("/getPostServlet")
public class getPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    
 


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDAO postDAO=new PostDAO();
		String post=request.getParameter("p");
		HttpSession session=request.getSession();
		Post post1=null;
		int x;
			if(post!=null) {
		 x=Integer.parseInt(post);
		 try {
			 post1=postDAO.getPostById(x);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
		
		session.setAttribute("post1",post1);
	
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("post.jsp");
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
