package gestoreLega;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Questa classe è un control che si occupa di verificare se i dati inseriti dall’utente siano accettabili,
 * se positivi, il control chiamerà il DAO addetto alla creazione dell’asta(AddAsta).
 * @author Pasquale Caramante 
 */
@WebServlet("/OrganizzaAstaServlet")
public class OrganizzaAstaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganizzaAstaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @preconditon request.getSession().getAttribute(“utente”)!=null and request.getSession().getAttribute(“tipo”).equals(“allenatore”)
	 *  and request.getParameter(“dataInizioAsta”)!=null and request.getParameter(“oraInizioAsta”)!=null and 
	 *  request.getParamter(“dataFineAsta”)!=null and  dataFineAsta>dataInizioAsta and request.getParameter(“nomeLega”)!=null
	 *  @postcondition AstaDAO.getAstaByKey(dataInizioAsta, nomeLega)!=null
	 *  @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Date dataInizioAsta = Date.valueOf(request.getParameter("dataInizioAsta"));
		Time oraInizioAsta = Time.valueOf(request.getParameter("oraInizioAsta")+":00");
		Date dataFineAsta = Date.valueOf(request.getParameter("dataFineAsta"));
		String nomeLega = request.getParameter("nomeLega");
		String redirect = "index.jsp";
		
		try {
		if(dataInizioAsta!=null && oraInizioAsta!= null && dataFineAsta != null) {
			AstaDAO astaDAO = new AstaDAO();
			Lega lega = new LegaDAO().getLegaByNome(nomeLega);
			astaDAO.addAsta(new Asta(dataInizioAsta,lega,oraInizioAsta,dataFineAsta));
			redirect = "areaPersonaleAllenatore.jsp";
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(redirect).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
