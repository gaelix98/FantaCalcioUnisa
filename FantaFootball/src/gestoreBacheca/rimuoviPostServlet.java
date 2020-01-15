package gestoreBacheca;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Questa classe è un control che si occupa di passare a PostDAO i dati di un post da rimuovere. 
 * @author Maria Natale
 *
 */
@WebServlet("/rimuoviPostServlet")
public class rimuoviPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rimuoviPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @precondition request.getParameter(“idPost”) != null   And request.getSession().getAttribute(“utente”)!=null and
					 Request.getSession().getAttribute(“tipo”).equals(“scout”)

	 * @postcondition PostDAO.getPostById(idPost)==null    
	 * @throws ServletException, IOException 

	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Post post=null;
		ArrayList<Post> allPost=(ArrayList<Post>) session.getAttribute("allPost");
		ArrayList<Post> postScout=(ArrayList<Post>) session.getAttribute("post");
		PostDAO postDAO=new PostDAO();
		int idPost;
		
		if (request.getParameter("idPost")!=null) {
			idPost=Integer.parseInt(request.getParameter("idPost"));
			try {
				postDAO.removePost(idPost);
				post=postDAO.getPostById(idPost);
				allPost.remove(post);
				postScout.remove(post);
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
