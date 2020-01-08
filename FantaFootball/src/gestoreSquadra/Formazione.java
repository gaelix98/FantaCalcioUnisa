package gestoreSquadra;
/**
 * Questa classe rappresenta l'entità Formazione.
 * @author Pasquale Caramante
 */
public class Formazione {
	private int giornata;
	private boolean schierata;
	private Giocatore[] giocatori;
	private Giocatore[] panchina;
	private Squadra squadra;
	/**
	 * @param giornata giornata di campionato in cui viene schierata la formazione
	 * @param schierata valore booleano che indica se la formazione è schierata o provvisoria
	 * @param squadra squadra che schiera la formazione
	 */
	public Formazione(int giornata, boolean schierata,Squadra squadra) {
		this.giornata = giornata;
		this.schierata = schierata;
		this.giocatori = new Giocatore[11];
		this.panchina = new Giocatore[7];
		this.squadra = squadra;
	}
	
	public Formazione(int giornata, Squadra squadra) {
		this.giornata = giornata;
		this.schierata = schierata;
		this.giocatori = new Giocatore[11];
		this.panchina = new Giocatore[7];
		this.squadra = squadra;
	}
	
	/**
	 * @return giornata giornata di campionato in cui viene schierata la formazione
	 */
	public int getGiornata() {
		return giornata;
	}
	
	/**
	 * @param giornata giornata di campionato da assegnare alla formazione
	 */
	public void setGiornata(int giornata) {
		this.giornata = giornata;
	}
	
	/**
	 * @return schierata <code>true</code> se la formazione è stata schierata
     *                  <code>false</code> se la formazione è provvisoria.
	 */
	public boolean isSchierata() {
		return schierata;
	}
	
	/**
	 * @param schierata schierata valore booleano che indica se la formazione è stat schierata o è provvisoria
	 */
	public void setSchierata(boolean schierata) {
		this.schierata = schierata;
	}
	
	/**
	 * @return giocatori array di giocatori schierati in campo
	 */
	public Giocatore[] getGiocatori() {
		return giocatori;
	}
	
	/**
	 * @param giocatori array di giocatori da schierare in campo 
	 */
	public void setGiocatori(Giocatore[] giocatori) {
		this.giocatori = giocatori;
	}
	
	/**
	 * @return panchina array di giocatori schierati in panchina
	 */
	public Giocatore[] getPanchina() {
		return panchina;
	}
	
	/**
	 * @param panchina panchina array di giocatori da schierare in panchina 
	 */
	public void setPanchina(Giocatore[] panchina) {
		this.panchina = panchina;
	}
	
	/**
	 * @return squadra squadra che ha schierato la formazione
	 */
	public Squadra getSquadra() {
		return squadra;
	}
	
	/**
	 * @param squadra squadra a cui assegnare questa formazione
	 */
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}
	
	
	
	
}
