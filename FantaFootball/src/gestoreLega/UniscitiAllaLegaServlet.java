package gestoreLega;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import gestoreUtente.Allenatore;

@WebServlet(name = "/UniscitiAllaLega")
public class UniscitiAllaLegaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UniscitiAllaLegaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
		boolean esci=false;
		int i=0;
		
		String q = request.getParameter("q");
		int risposta = Integer.parseInt(q);
		
		LegaDAO legadao = new LegaDAO();
		String redirect="index.jsp";
		InvitoDAO invitod= new InvitoDAO();
		try {
			List<Invito> invito = invitod.getInvitoByAllenatore(allenatore.getUsername());
			for( i=0;i<invito.size()||!esci;i++) {
				if(invito.get(i).getLega().getNome().equals(nome)==true){
					esci=true;
				}
			}
			
			if(risposta==0) {
              invitod.deleteInvito(allenatore.getUsername(), nome);
			}
			if(risposta==1) {
				redirect="creasquadra.jsp";
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher(redirect).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
