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
 * 
 * @author Maria Natale
 *
 */
@WebServlet("/modificaPostServlet")
public class modificaPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificaPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Post oldPost=null;
		Post newPost=oldPost;
		ArrayList<Post> allPost=(ArrayList<Post>) session.getAttribute("allPost");
		ArrayList<Post> postScout=(ArrayList<Post>) session.getAttribute("post");
		PostDAO postDAO=new PostDAO();
		int idPost;
		String testo=request.getParameter("testo");
		
		if (request.getParameter("idPost")!=null && testo!=null) {
			idPost=Integer.parseInt(request.getParameter("idPost"));
			try {
				oldPost=postDAO.getPostById(idPost);
				newPost.setTesto(testo);
				allPost.set(allPost.indexOf(oldPost), newPost);
				postScout.set(allPost.indexOf(oldPost), newPost);
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
