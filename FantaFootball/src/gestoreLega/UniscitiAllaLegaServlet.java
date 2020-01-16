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

/**
 * Questa classe è un control che si occupa di verificare la risposta dell’allenatore all’invito ad una lega. 
 * Se positiva, verrà chiamato il DAO addetto all’associazione di un allenatore ad una lega (UpdateInvito), 
 * se negativa,  verrà chiamato il DAO addetto all’eliminazione dell’invito.
 * @author Gaetano Casillo
 *
 */
@WebServlet(name = "/UniscitiAllaLega")
public class UniscitiAllaLegaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UniscitiAllaLegaServlet() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”) 
	 * and request.getParameter(“Risposta”)!=null and typeof(Risposta)=boolean and request.getParameter(“Lega”)!=null. 
	 * @postcondition InvitoDAO.getInvitoById(utente, Lega).getRisposta()==true
	 * @throws ServletException, IOException
	 */
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
