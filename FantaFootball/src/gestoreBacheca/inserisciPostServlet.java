package gestoreBacheca;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreUtente.Scout;

/**
 * Questa classe è un control che si occupa di passare a PostDAO i dati di un post da pubblicare.
 * @author Maria Natale
 *
 */
@WebServlet("/inserisciPostServlet")
public class inserisciPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inserisciPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Date data=new Date(Calendar.getInstance().getTime().getTime());
		String titolo=request.getParameter("titolo");
		String testo=request.getParameter("testo");
		Scout scout=(Scout) session.getAttribute("utente");
		Post post=null;
		ArrayList<Post> allPost=(ArrayList<Post>) session.getAttribute("allPost");
		ArrayList<Post> postScout=(ArrayList<Post>) session.getAttribute("post");
		PostDAO postDAO=new PostDAO();
		
		if (titolo!=null && testo!=null) {
			post=new Post(data, titolo, testo, scout);
			try {
				postDAO.addPost(post);
				allPost.add(post);
				postScout.add(post);
				session.setAttribute("allPost", allPost);
				session.setAttribute("post", postScout);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
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
