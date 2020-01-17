package gestoreBacheca;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestoreUtente.Scout;

/**
 * Questa classe � un control che si occupa di passare a PostDAO i dati di un post da pubblicare.
 * @author Maria Natale
 *
 */
@WebServlet("/InserisciPostServlet")
public class InserisciPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @precondition request.getParameter(�testo�)!=null  and request.getParameter(�titolo�)!=null
					 and request.getSession().getAttribute(�utente�)!=null and
					 request.getSession().getAttribute(�tipo�).equals(�scout�)
	 * @postcondition PostDAO.getPostById(idPost)!=null    
	 * @throws ServletException, IOException 

	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); //sennò si legge buggato
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		Date data=new Date(Calendar.getInstance().getTime().getTime()); 
		String titolo=request.getParameter("titolo");
		String testo=request.getParameter("testo");
		Scout scout=(Scout) session.getAttribute("utente");
		Post post=null;
		ArrayList<Post> allPost=(ArrayList<Post>) session.getAttribute("allPost");
		ArrayList<Post> postScout=(ArrayList<Post>) session.getAttribute("post");
		PostDAO postDAO=new PostDAO();
		
		String redirect="newPost.jsp";
		
		if (titolo!=null && testo.length()>20) {
			post=new Post(data, titolo, testo, scout);  
			try {
				
				postDAO.addPost(post); 
			    Post vogliodormire=postDAO.getUltimoPostByScout(scout.getUsername()); //idea delle 05:42 ringraziamo dio gaetano 
				allPost.add(vogliodormire);
				postScout.add(vogliodormire);
				session.setAttribute("allPost", allPost);
				session.setAttribute("post", postScout);
				redirect="areaPersonaleScout.jsp";
				request.setAttribute("result", "Post inserito");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (redirect.equals("newPost.jsp")) {
			request.setAttribute("message", "Post non inserito");
			response.getWriter().write("Post non inserito");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
