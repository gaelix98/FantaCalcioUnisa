package gestoreSquadra;

import java.io.File;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			if(valida(nomeLega,nomeFile)) {
				try{
					lega = legadao.getLegaByNome(nomeLega);
					boolean squadraExists = (squadradao.getSquadraById(nomeSquadra, nomeLega) != null);
					if(!squadraExists && lega != null && allenatore !=null) {
						squadradao.creaSquadra(new Squadra(nomeSquadra,nomeFile,allenatore,lega,0,lega.getBudget()));
						p.write(pathLogo + File.separator + nomeFile);
						request.setAttribute("lega",lega);
						redirect = "areaPersonaleAllenatore.jsp"; 	
						Invito invito=invitod.getInvitoById(allenatore, lega);
						ArrayList<Invito> inviti=(ArrayList<Invito>) session.getAttribute("inviti");
						inviti.remove(invito);
						invito.setRisposta(true);
						invitod.updateInvito(invito); 
						session.setAttribute("inviti", inviti);
					}
					else {
						request.setAttribute("message", "Squadra non creata");
						redirect = "creasquadra.jsp";
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
