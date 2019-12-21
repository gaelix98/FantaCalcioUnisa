package gestoreSquadra;

import gestoreLega.Asta;
/**
 * Questa classe rappresenta l'entità Offerta.
 * @author Maria Natale
 */
public class Offerta {
	private Squadra squadra;
	private Asta asta;
	private Giocatore giocatore;
	private int somma;
	
	/**
	 * @param squadra squadra che fa l'offerta
	 * @param asta asta in cui viene fatta l'offerta
	 * @param giocatore giocatore a cui si riferisce l'offerta
	 * @param somma somma offerta per il giocatore
	 * @return 
	 */
	public Offerta(Squadra squadra, Asta asta, Giocatore giocatore, int somma) {
		this.squadra = squadra;
		this.asta = asta;
		this.giocatore = giocatore;
		this.somma = somma;
	}

	/**
	 * @return squadra squadra che fa l'offerta
	 */
	public Squadra getSquadra() {
		return squadra;
	}

	/**
	 * @param squadra squadra da assegnare all'offerta
	 * @return 
	 */
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}

	/**
	 * @return asta asta in cui viene fatta l'offerta
	 */
	public Asta getAsta() {
		return asta;
	}

	/**
	 * @param asta asta da assegnare all'offerta
	 * @return 
	 */
	public void setAsta(Asta asta) {
		this.asta = asta;
	}

	/**
	 * @return giocatore giocatore a cui si riferisce l'offerta
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

	/**
	 * @param giocatore giocatore da assegnare all'offerta
	 * @return 
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/**
	 * @return somma somma offerta per il giocatore
	 */
	public int getSomma() {
		return somma;
	}
	
	/**
	 * @param somma somma da assegnare all'offerta
	 * @return 
	 */
	public void setSomma(int somma) {
		this.somma = somma;
	}
	
	

}
