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
 * Servlet implementation class OrganizzaAsta
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private long inMillis(Time time) {
		long millis=0;
		String[] orario = time.toString().split(":");
		millis = (Integer.parseInt(orario[0])*60*60*1000) + (Integer.parseInt(orario[1])*60*1000) + (Integer.parseInt(orario[2])*1000);
		System.out.println(millis);
		return millis;
	}

}
