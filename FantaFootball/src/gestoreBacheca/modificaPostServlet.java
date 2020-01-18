package gestoreBacheca;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Questa classe � un control che si occupa di passare a PostDAO i dati di un post da modificare
 * 
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
	 * @precondition request.getParameter(�idPost�) != null and request.getParameter(�testo�) != null   
	 * And request.getSession().getAttribute(�utente�)!=null and
	 * Request.getSession().getAttribute(�tipo�).equals(�scout�)

	 * @postcondition PostDAO.getPostById(idPost).testo == testo 
	 * @throws ServletException, IOException 

	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); //sennò si legge buggato
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		Post oldPost=null;
		Post newPost=null;
		String testo=request.getParameter("text");
		String id=request.getParameter("idpost");
		int idPost=Integer.parseInt(id);
		ArrayList<Post> allPost=(ArrayList<Post>) session.getAttribute("allPost");
		ArrayList<Post> postScout=(ArrayList<Post>) session.getAttribute("post");
		PostDAO postDAO=new PostDAO();
		 
		if (request.getParameter("idpost")!=null && testo.length()>20) {
		
			try {
				oldPost=postDAO.getPostById(idPost);
				newPost=oldPost;
				newPost.setTesto(testo);
				postDAO.updatePost(newPost);
				request.setAttribute("result", "Post modificato");
				int i=0;
				for(;i<allPost.size() && allPost.get(i)!=null;i++) {
					if(allPost.get(i).getIdPost()==newPost.getIdPost()) {
						break;
						
					}
				}
				int h=0;
				for(;h<postScout.size() && postScout.get(h)!=null ;h++) {
					if(postScout.get(h).getIdPost()==newPost.getIdPost()) {
						break;	
					}
				}
				
				
				allPost.set(i, newPost); //qua mette  al posto del vecchio, quello nuovo solo che non trova ilvecchio
				postScout.set(h, newPost);  //metti in moto il motore ho messo il for per forza, getindexof andava in -1 e questa è l'idea delle 04:10. 
				session.setAttribute("allPost", allPost);
				session.setAttribute("post", postScout);
			} catch (SQLException e) {
				e.printStackTrace();
	
			}
			
		}
		else {
			response.getWriter().write("Post non modificato");
		}
		//speriamo bene
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("areaPersonaleScout.jsp");
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
