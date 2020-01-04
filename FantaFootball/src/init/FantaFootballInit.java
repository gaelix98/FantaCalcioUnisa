package init;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.tomcat.jni.Time;

import gestoreLega.Asta;
import gestoreLega.AstaDAO;
import gestoreLega.Lega;
import gestoreLega.LegaDAO;
import gestoreSquadra.Giocatore;
import gestoreSquadra.Offerta;
import gestoreSquadra.OffertaDAO;
import gestoreSquadra.Squadra;
import gestoreSquadra.SquadraDAO;

/**
 * Implementazione di un Context Listener per inizializzare e chiudere lo ScheduledExecutorService che gestisce le aste e le partite
 *@author Pasquale Caramante
 */
@WebListener
public class FantaFootballInit implements ServletContextListener {
	
	
	
	private ScheduledExecutorService executor;
    /**
     * Default constructor. 
     */
    public FantaFootballInit() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {  
    	 executor.shutdownNow();
    	    try {
    	      if (!executor.awaitTermination(50, TimeUnit.SECONDS)) {
    	        executor.shutdownNow();
    	        System.err.println("executor did not terminate");
    	      }
    	    } catch (InterruptedException ie) {
    	      executor.shutdownNow();
    	      Thread.currentThread().interrupt();
    	    }
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { //all'avvio dell'app istanzia uno ScheduledExecutorService che viene salvato nel contesto globale dell'app
         ServletContext sc = sce.getServletContext();			
         executor = Executors.newScheduledThreadPool(1);
         LocalDateTime mezzanotte = LocalDateTime.now().plusDays(1L).toLocalDate().atStartOfDay();
         
         //usa questa variabile per testare lo scheduler, ripete il task ogni minuto a partire dal prossimo minuto
         //LocalDateTime mezzanotte = LocalDateTime.now().plusMinutes(1L).withSecond(0);
         
		/*viene assegnato un task(controllo risultati delle aste) da eseguire 
        per la prima volta a mezzanotte del giorno corrente e ripetuto ogni 24 ore (86400000 in ms)*/
         executor.scheduleWithFixedDelay(new Runnable() {
         									@Override 
         									public void run() {
         										AstaDAO astaDAO = new AstaDAO();
         										OffertaDAO offertaDAO = new OffertaDAO();
         										SquadraDAO squadraDAO = new SquadraDAO();  
         										try {
         											ArrayList<Asta> aste = astaDAO.getAsteScaduteOggi();
         											for(Asta asta : aste) {
         												Date dataInizioAsta = asta.getDataInizio();
         												String nomeLega = asta.getLega().getNome();
         												ArrayList<Offerta> offerte = offertaDAO.getOfferteVincentiByAsta(dataInizioAsta, nomeLega);
         												for(Offerta tmp : offerte) {
         													Squadra squadra = tmp.getSquadra();
         													Giocatore giocatore = tmp.getGiocatore();
         													squadraDAO.addGiocatoreSquadra(squadra.getNome(), squadra.getLega().getNome(), giocatore.getId());
         												}
         											}
         										}catch(SQLException e) {
         											e.printStackTrace();
         										}
         									} 
         								}, LocalDateTime.now().until(mezzanotte, ChronoUnit.MILLIS), /*86400000L*/60000L, TimeUnit.MILLISECONDS);

		
		 sc.setAttribute("executor", executor);
    }
	
}
