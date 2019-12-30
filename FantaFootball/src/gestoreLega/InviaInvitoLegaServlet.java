package gestoreLega;

import java.io.IOException;
import java.sql.Date;
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
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;


@WebServlet("/InviaInvitoLegaServlet")
public class InviaInvitoLegaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InviaInvitoLegaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean èpresidente=false;
		LegaDAO legad = new LegaDAO();
		InvitoDAO invitd = new InvitoDAO();
		AllenatoreDAO allenatord= new AllenatoreDAO();
		String lega=request.getParameter("lega");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String redirect="";
		Allenatore invitato = null;
		Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
		ArrayList<Lega> leghe;
		try {
			leghe = legad.getLegheByPresidente(allenatore);
			int i=0;
			for(i=0;i<leghe.size();i++) { //checko if presidente
				if (leghe.get(i).getNome()==lega) {
					èpresidente=true;
					break;
				}
			}
			if (èpresidente && (username!= null || email!=null)) {
				if(username!=null) {
					invitato = allenatord.getAllenatoreByUsername(username);
					invitd.addInvito(new Invito(invitato, leghe.get(i), false));
					 }
				if(email!=null) {
					invitato= allenatord.getAllenatoreByEmail(email);
					invitd.addInvito(new Invito(invitato, leghe.get(i), false));
					
				}
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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