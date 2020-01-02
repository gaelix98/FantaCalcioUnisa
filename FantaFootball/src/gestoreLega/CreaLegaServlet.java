package gestoreLega;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import gestoreUtente.Allenatore;

@WebServlet(name = "/CreaLegaServlet",
urlPatterns = "/CreaLegaServlet",
initParams = {@WebInitParam(name = "path-loghi-leghe",value = "img\\leghe")}
		)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
maxFileSize = 1024 * 1024 * 2,
maxRequestSize = 1024 * 1024 * 2)
public class CreaLegaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String PATH;
	
	public void init() {
		PATH= getServletConfig().getInitParameter("path-loghi-leghe");
		
	}
	public CreaLegaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeLega = request.getParameter("nomeLega");
		String QuotaPrimoPosto = request.getParameter("primoPosto");
		String QuotaSecondoPosto = request.getParameter("secondoPosto");
		String QuotaTerzoPosto = request.getParameter("terzoPosto");
		String QuotaMensile = request.getParameter("quotaMensile");
		String MaxAllenatori = request.getParameter("maxAllenatori");
		String budget = request.getParameter("budget");
		LegaDAO legadao = new LegaDAO();
		
		
		Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
		String redirect="index.jsp";
		String pathLogo = getServletContext().getRealPath("") + File.separator + PATH;
		File saveDir = new File(pathLogo);



		if(!saveDir.exists()) {
			saveDir.mkdir();
		}
		for(Part p: request.getParts()) {
			String nomeFile = extractFileName(p);
			if(valida(nomeLega,nomeFile,MaxAllenatori,QuotaPrimoPosto,QuotaSecondoPosto,QuotaTerzoPosto,QuotaMensile)) {
				try{
					boolean legaExists = (legadao.getLegaByNome(nomeLega)!= null);
					if(!legaExists && allenatore !=null) {
						Lega lega = new Lega(nomeLega, nomeFile, Integer.parseInt(MaxAllenatori), Integer.parseInt(QuotaMensile),
								Integer.parseInt(budget),Integer.parseInt(QuotaPrimoPosto) 
								,Integer.parseInt(QuotaSecondoPosto),Integer.parseInt(QuotaTerzoPosto), allenatore);
						legadao.addLega(lega);
						p.write(pathLogo + File.separator + nomeFile);
						request.setAttribute("lega",lega);
						redirect = "lega.jsp"; // da creare : pagina destinazione se la creazione squadra ha successo	
					}
					else {
						request.setAttribute("message", "Squadra non creata");
						redirect = "crealega.jsp";
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



	private boolean valida(String nome,String logo,String maxAllenatori,String QuotaPrimoPosto,String QuotaSecondoPosto,String QuotaTerzoPosto,String QuotaMensile) {
		String rexNome = "^.{4,50}$";
		String rexLogo = "[^\\s]+(\\.(?i)(jpg|png|img|))$";
		String rexQuota = "^[0-9]{0,2}*$";
		boolean ok=false;
		if(((Integer.parseInt(maxAllenatori)<9 && Integer.parseInt(maxAllenatori)>4) || Integer.parseInt(maxAllenatori)==10 )) {
			ok=true;
		}else {return false;}
		
		
		
		return Pattern.matches(rexNome, nome) && Pattern.matches(rexLogo, logo) && Pattern.matches(rexQuota, QuotaPrimoPosto) && Pattern.matches(rexQuota, QuotaSecondoPosto)
				&& Pattern.matches(rexQuota, QuotaTerzoPosto) && Pattern.matches(rexQuota, QuotaMensile) && ok;
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


