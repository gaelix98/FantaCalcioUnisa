package gestoreLega;

import gestoreSquadra.Squadra;
/**
 * Questa classe rappresenta l'entità Partita.
 * @author Pasquale Caramante
 */
public class Partita {
	private Squadra squadra1;
	private Squadra squadra2;
	private int giornata;
	private int goalSquadra1;
	private int goalSquadra2;
	
	/**
	 * @param squadra1 squadra di casa
	 * @param squadra2 squadra in trasferta
	 * @param giornata giornata di campionato
	 */
	public Partita(Squadra squadra1, Squadra squadra2, int giornata) {
		this.squadra1 = squadra1;
		this.squadra2 = squadra2;
		this.giornata = giornata;
		this.goalSquadra1 = 0;
		this.goalSquadra2 = 0;
	}
	
	
	/**
	 * 
	 * @param squadra1
	 * @param squadra2
	 * @param giornata
	 * @param goalSquadra1
	 * @param goalSquadra2
	 */
	public Partita(Squadra squadra1, Squadra squadra2, int giornata, int goalSquadra1, int goalSquadra2) {
		this.squadra1 = squadra1;
		this.squadra2 = squadra2;
		this.giornata = giornata;
		this.goalSquadra1 = goalSquadra1;
		this.goalSquadra2 = goalSquadra2;
	}



	/**
	 * @return squadra1 squadra di casa
	 */
	public Squadra getSquadra1() {
		return squadra1;
	}
	
	/**
	 * @param squadra1 squadra di casa da assegnare alla partita
	 */
	public void setSquadra1(Squadra squadra1) {
		this.squadra1 = squadra1;
	}
	
	/**
	 * @return squadra2 squadra in trasferta
	 */
	public Squadra getSquadra2() {
		return squadra2;
	}
	
	/**
	 * @param squadra2 squadra in trasferta da assegnare alla partita
	 */
	public void setSquadra2(Squadra squadra2) {
		this.squadra2 = squadra2;
	}
	
	/**
	 * @return giornata giornata di campionato in cui si svolge la partita
	 */
	public int getGiornata() {
		return giornata;
	}
	
	/**
	 * @param giornata giornata di campionato da assegnare alla partita
	 */
	public void setGiornata(int giornata) {
		this.giornata = giornata;
	}
	
	/**
	 * @return goalSquadra1 numero di goal segnati dalla squadra di casa
	 */
	public int getGoalSquadra1() {
		return goalSquadra1;
	}
	
	/**
	 * @param goalSquadra1 numero di goal da assegnare alla squadra di casa
	 */
	public void setGoalSquadra1(int goalSquadra1) {
		this.goalSquadra1 = goalSquadra1;
	}
	
	/**
	 * @return goalSquadra2 numero di goal segnati dalla squadra in trasferta
	 */
	public int getGoalSquadra2() {
		return goalSquadra2;
	}
	
	/**
	 * @param goalSquadra2 numero di goal da assegnare alla squadra in trasferta
	 */
	public void setGoalSquadra2(int goalSquadra2) {
		this.goalSquadra2 = goalSquadra2;
	}
	
	
}
