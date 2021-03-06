package gestoreLega;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;
import gestoreUtente.Allenatore;

/**
 * Questa classe � un control che si occupa di verificare se i dati inseriti per la creazione della lega sono validi, se validi, il control invocher� il DAO
 *  adatto alla memorizzazione della lega.
 * @author Gaetano Casillo
 *
 */
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

	/**
	 * @precondition request.getSession().getAttribute(�utente�)!=null and request.getSession().getAttribute(�tipo�).equals(�allenatore�) 
	 *  and request.getParameter(�Nome�)!=null and rispetta il formato �^{4,50}$� and non deve essere presente nella lega. Request.getParameter(�logo�) rispetta il formato �([^\s]+(\.(?i)(jpg|png|img|))$)�.
	 *  Request.getParameter(�Quota�)!=null and rispetta il formato �^[0-9]{0,2}*$� and request.getParameter(�primoPosto�)!=null and rispetta il formato �^[0-9]{0,2}*$�.
	 *  Request.getParameter(�secondoPosto�)!=null and rispetta il formato �^[0-9]{0,2}*$�.
	 *  Request.getParameter(�terzoPosto�)!=null and rispetta il formato �^[0-9]{0,2}*$�.
	 *  Request.getParameter(�maxAllenatori�)!=null and 4<=maxAllenatori<=10 
	 *  @postcondition LegaDAO.getLegaByNome(nome)!=null
	 *  @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeLega = request.getParameter("nome");
		String QuotaPrimoPosto = request.getParameter("primoPosto");
		String QuotaSecondoPosto = request.getParameter("secondoPosto");
		String QuotaTerzoPosto = request.getParameter("terzoPosto");
		String QuotaMensile = request.getParameter("quotaMensile");
		String MaxAllenatori = request.getParameter("maxAllenatori");
		String budget = request.getParameter("budget");
		LegaDAO legadao = new LegaDAO();
		HttpSession session=request.getSession();
		
		Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
		String redirect="index.jsp";
		String pathLogo = getServletContext().getRealPath("") + File.separator + PATH;
		File saveDir = new File(pathLogo);



		if(!saveDir.exists()) {
			saveDir.mkdir();
		}
		for(Part p: request.getParts()) {
			String nomeFile=extractFileName(p);
				if (nomeFile==null) {
					System.out.println("nome file null");
					nomeFile="logoDefault.jpg";
				}
			if(valida(nomeLega,nomeFile,MaxAllenatori,QuotaPrimoPosto,QuotaSecondoPosto,QuotaTerzoPosto,QuotaMensile)) {
				try{
					boolean legaExists = (legadao.getLegaByNome(nomeLega)!= null);
					if(!legaExists && allenatore !=null) {
						Lega lega = new Lega(nomeLega, nomeFile, Integer.parseInt(MaxAllenatori), Integer.parseInt(QuotaMensile),
								Integer.parseInt(budget),Integer.parseInt(QuotaPrimoPosto) 
								,Integer.parseInt(QuotaSecondoPosto),Integer.parseInt(QuotaTerzoPosto), allenatore);
						//legadao.addLega(lega);
						p.write(pathLogo + File.separator + nomeFile);
						session.setAttribute("legadaCreare",lega);
						/*new InvitoDAO().addInvito(invito);
						ArrayList<Lega> legheCreate=(ArrayList<Lega>) session.getAttribute("legheCreate");
						legheCreate.add(lega);
						ArrayList<Lega> leghe=(ArrayList<Lega>) session.getAttribute("leghe");
						leghe.add(lega);
						session.setAttribute("legheCreate", legheCreate);
						session.setAttribute("leghe", leghe);*/
						redirect = "creasquadra.jsp?nomeLega="+nomeLega;
						request.setAttribute("result", "successo");
					}
					else {
						request.setAttribute("message", "Lega non creata");
						redirect = "crealega.jsp";
						response.getWriter().write("Lega non creata");
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}



	private boolean valida(String nome,String logo,String maxAllenatori,String QuotaPrimoPosto,String QuotaSecondoPosto,String QuotaTerzoPosto,String QuotaMensile) {
		String rexNome = "^.{4,50}$";
		String rexLogo = "[^\\s]+(\\.(?i)(jpg|png|img|))$";
		String rexQuota = "^[0-9]{0,2}$";
		boolean ok=false;
		if(Integer.parseInt(maxAllenatori)>=4 && Integer.parseInt(maxAllenatori)<=10) {
			ok=true;
		}else {return false;}
		
		return Pattern.matches(rexNome, nome) && (Pattern.matches(rexLogo, logo)) && Pattern.matches(rexQuota, QuotaPrimoPosto) &&
				Pattern.matches(rexQuota, QuotaSecondoPosto) && Pattern.matches(rexQuota, QuotaTerzoPosto) 
				&& Pattern.matches(rexQuota, QuotaMensile)
				&& ok;
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


