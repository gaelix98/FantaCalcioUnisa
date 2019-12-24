package gestoreLega;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import gestoreSquadra.Offerta;
/**
 * Questa classe rappresenta l'entità Asta.
 * @author Pasquale Caramante
 */
public class Asta {
	private Date dataInizio;
	private Lega lega;
	private Time ora;
	private Date dataFine;
	
	/**
	 * @param dataInizio data di inizio dell'asta
	 * @param lega la lega a cui appartiene l'asta
	 * @param ora ora di inizio dell'asta
	 * @param dataFine la data di fine dell'asta
	 */
	public Asta(Date dataInizio, Lega lega, Time ora, Date dataFine) {
		this.dataInizio = dataInizio;
		this.lega = lega;
		this.ora = ora;
		this.dataFine = dataFine;
	}
	
	/**
	 * @return dataInizio data di inizio dell'asta
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	
	/**
	 * @param dataInizio dataInizio data di inizio da assegnare all'asta
	 * @return
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	/**
	 * @return lega la lega a cui appartiene l'asta
	 */
	public Lega getLega() {
		return lega;
	}
	
	/**
	 * @param lega lega da associare all'asta
	 * @return
	 */
	public void setLega(Lega lega) {
		this.lega = lega;
	}
	
	/**
	 * @return ora ora di inizio dell'asta
	 */
	public Time getOra() {
		return ora;
	}
	
	/**
	 * @param ora ora di inizio da assegnare all'asta
	 * @return
	 */
	public void setOra(Time ora) {
		this.ora = ora;
	}
	
	/**
	 * @return dataFine data di fine dell'asta
	 */
	public Date getDataFine() {
		return dataFine;
	}
	
	/**
	 * @param dataFine data di fine da assegnare all'asta
	 * @return
	 */
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
	
	
}
