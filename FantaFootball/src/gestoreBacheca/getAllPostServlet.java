package gestoreBacheca;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Questa classe è un control che si occupa di prendere tutti i post 
 * @author Maria Natale
 *
 */
@WebServlet("/getAllPostServlet")
public class getAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @postcondition request.getSession().getAttribute(“allPost”)!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PostDAO postDAO=new PostDAO();
		ArrayList<Post> post=null;
		try {
			post=postDAO.getAllPost();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("allPost", post);
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
