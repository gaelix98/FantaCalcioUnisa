package gestoreSquadra;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestoreUtente.Allenatore;

/**
 * Servlet implementation class PrendiOfferteServlet
 */
@WebServlet("/PrendiOfferteServlet")
public class PrendiOfferteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrendiOfferteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Allenatore allenatore=(Allenatore) request.getSession().getAttribute("utente");
		Date dataInizioAsta=Date.valueOf(request.getParameter("data"));
		String nomeLega=request.getParameter("lega");
		
		
		String redirect="errorPage.jsp";
		ArrayList<Offerta> offerte= null;
		if (allenatore!=null && dataInizioAsta!=null && nomeLega!=null) {
			
			OffertaDAO offertad = new OffertaDAO();
			try {
				offerte=offertad.getAllOfferteByAstaAllenatore(dataInizioAsta, nomeLega, allenatore.getUsername());
				if(offerte!=null) {
				redirect="leMieOfferte.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("offerte", offerte);
		System.out.println(offerte.size());
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
