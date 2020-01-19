package gestoreSquadra;

import java.io.File;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import gestoreLega.Invito;
import gestoreLega.InvitoDAO;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreUtente.Allenatore;

/**
 * @autor Pasquale Caramante
 * Questa classe è un control che si occupa di verificare se i dati inseriti per la creazione della squadra sono validi, 
 * se validi, il control invocherà il DAO adatto alla memorizzazione della squadra. (CreaSquadra)
 */
@WebServlet(name = "/CreaSquadraServlet",
urlPatterns = "/CreaSquadraServlet",
initParams = {@WebInitParam(name = "path-loghi-squadre",value = "img\\squadre")}
		)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
maxFileSize = 1024 * 1024 * 2,
maxRequestSize = 1024 * 1024 * 2)
public class CreaSquadraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String PATH;


	public void init() {
		PATH = getServletConfig().getInitParameter("path-loghi-squadre");
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaSquadraServlet() {
		super();
	}
	
	/**
	 * @precondition request.getSession().getAttribute(“utente”)!=null and Request.getSession().getAttribute(“tipo”).equals(“allenatore”) and 
	 * request.getParameter(“Nome”)!=null and rispetta il formato “^{4,50}$” and non deve essere presente nella lega.
	 * Request.getParameter(“logo”) rispetta il formato “([^\s]+(\.(?i)(jpg|png|img|))$)” and request.getParameter(“nomeLega”)!=null
	 * @postcondition SquadraDAO.getSquadraById(nome, nomeLega)!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeLega = request.getParameter("nomeLega");
		String nomeSquadra = request.getParameter("nome");
		LegaDAO legadao = new LegaDAO();
		SquadraDAO squadradao = new SquadraDAO();
		InvitoDAO invitod = new InvitoDAO();
		Lega lega = null;
		Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
		String redirect="index.jsp";
		String pathLogo = getServletContext().getRealPath("") + File.separator + PATH;
		File saveDir = new File(pathLogo);
		HttpSession session=request.getSession();


		if(!saveDir.exists()) {
			saveDir.mkdir();
		}
		for(Part p: request.getParts()) {
			String nomeFile = extractFileName(p);
			if (nomeFile==null) {
				System.out.println("nome file null");
			}
			
			if(valida(nomeLega,nomeFile)) {
				try{
					
					//controllo se è la prima squadra della lega. La lega verrà creata solo se può essere creata la squadra
					Lega legaDaCreare=(Lega)session.getAttribute("legadaCreare");
					
					if(legaDaCreare!=null) {
						legadao.addLega(legaDaCreare);
						ArrayList<Lega> legheCreate=(ArrayList<Lega>) session.getAttribute("legheCreate");
						legheCreate.add(legaDaCreare);
						ArrayList<Lega> leghe=(ArrayList<Lega>) session.getAttribute("leghe");
						leghe.add(legaDaCreare);
						session.setAttribute("legheCreate", legheCreate);
						session.setAttribute("leghe", leghe);
						session.setAttribute("legadaCreare", null);
					}
					
					lega = legadao.getLegaByNome(nomeLega);
					boolean squadraExists = (squadradao.getSquadraById(nomeSquadra, nomeLega) != null);
					if(!squadraExists && lega != null && allenatore !=null) {
						squadradao.creaSquadra(new Squadra(nomeSquadra,nomeFile,allenatore,lega,0,lega.getBudget()));
						p.write(pathLogo + File.separator + nomeFile);
						redirect = "areaPersonaleAllenatore.jsp"; 
						
						if(!allenatore.getUsername().equals(lega.getPresidente().getUsername())) {
							Invito invito=invitod.getInvitoById(allenatore, lega);
							ArrayList<Invito> inviti=(ArrayList<Invito>) session.getAttribute("inviti");
							inviti.remove(invito);
							invito.setRisposta(true);
							invitod.updateInvito(invito); 
							session.setAttribute("inviti", inviti);
							List<Lega> leghe=(ArrayList<Lega>)session.getAttribute("leghe");
							leghe.add(lega);
							session.setAttribute("leghe", leghe);
						}	
					}
					else {
						request.setAttribute("message", "Squadra non creata");
						redirect = "creasquadra.jsp";
						legadao.deleteLega(lega);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}

				System.out.println(pathLogo + File.separator + nomeFile);
			}	
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



	private boolean valida(String nome,String logo) {
		String rexNome = "^.{4,50}$";
		String rexLogo = "[^\\s]+(\\.(?i)(jpg|png|img|))$";
		
		return Pattern.matches(rexNome, nome) && Pattern.matches(rexLogo, logo);
	}

	private String extractFileName(Part part) {
		String contentdisp = part.getHeader("content-disposition");
		String[] items = contentdisp.split(";");
		for(String s : items) {
			if(s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=")+2,s.length()-1);
			}
		}
		return "";
	}

}
