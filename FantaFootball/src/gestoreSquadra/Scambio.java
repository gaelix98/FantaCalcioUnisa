package gestoreSquadra;

/**
 * Questa classe rappresenta l'entità Scambio
 * @author Pasquale Caramante
 *
 */

public class Scambio {
	
	private Giocatore giocatore1;
	private Giocatore giocatore2;
	private int prezzoOfferto;
	private Squadra squadra1;
	private Squadra squadra2;
	/**
	 * @param giocatore1
	 * @param giocatore2
	 * @param prezzoOfferto
	 * @param squadra1
	 * @param squadra2
	 */
	public Scambio(Giocatore giocatore1, Giocatore giocatore2, int prezzoOfferto, Squadra squadra1, Squadra squadra2) {
		this.giocatore1 = giocatore1;
		this.giocatore2 = giocatore2;
		this.prezzoOfferto = prezzoOfferto;
		this.squadra1 = squadra1;
		this.squadra2 = squadra2;
	}
	
	/**
	 * @return giocatore1 giocatore
	 */
	public Giocatore getGiocatore1() {
		return giocatore1;
	}
	
	/**
	 * @param giocatore1 giocatore
	 */
	public void setGiocatore1(Giocatore giocatore1) {
		this.giocatore1 = giocatore1;
	}
	
	/**
	 * @return giocatore2 giocatore
	 */
	public Giocatore getGiocatore2() {
		return giocatore2;
	}
	
	/**
	 * @param giocatore2 giocatore
	 */
	public void setGiocatore2(Giocatore giocatore2) {
		this.giocatore2 = giocatore2;
	}
	
	/**
	 * @return prezzoOfferto prezzo offerto
	 */
	public int getPrezzoOfferto() {
		return prezzoOfferto;
	}
	
	/**
	 * @param prezzoOfferto prezzo offerto
	 */
	public void setPrezzoOfferto(int prezzoOfferto) {
		this.prezzoOfferto = prezzoOfferto;
	}
	
	/**
	 * @return squadra1 squadra
	 */
	public Squadra getSquadra1() {
		return squadra1;
	}
	
	/**
	 * @param squadra1 squadra
	 */
	public void setSquadra1(Squadra squadra1) {
		this.squadra1 = squadra1;
	}
	
	/**
	 * @return squadra2 squadra
	 */
	public Squadra getSquadra2() {
		return squadra2;
	}
	
	/**
	 * @param squadra2 squadra
	 */
	public void setSquadra2(Squadra squadra2) {
		this.squadra2 = squadra2;
	}
	
	
}
