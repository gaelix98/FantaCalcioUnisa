package gestoreSquadra;

import gestoreUtente.Allenatore;
import gestoreLega.Lega;

/**
 * Questa classe rappresenta l'entità Squadra.
 * @author Maria Natale
 */
public class Squadra {
	private String nome;
	private String logo;
	private Allenatore allenatore;
	private Lega lega;
	private int punti;
	private Formazione formazione;
	private Giocatore[] giocatori;
	private int budgetRimanente;
	
	/**
	 * @param nome nome della squadra
	 * @param logo logo della squadra
	 * @param allenatore allenatore della squadra
	 * @param lega lega di cui fa parte la squadra
	 * @param punti punti della squadra
	 * @param budgetRimanente budget rimanente in FM
	 */
	public Squadra(String nome, String logo, Allenatore allenatore, Lega lega, int punti, int budgetRimanente) {
		this.nome = nome;
		this.logo = logo;
		this.allenatore = allenatore;
		this.lega = lega;
		this.punti = punti;
		this.budgetRimanente = budgetRimanente;
		this.giocatori=new Giocatore[25];
		this.formazione=null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Allenatore getAllenatore() {
		return allenatore;
	}

	public void setAllenatore(Allenatore allenatore) {
		this.allenatore = allenatore;
	}

	public Lega getLega() {
		return lega;
	}

	public void setLega(Lega lega) {
		this.lega = lega;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	public Formazione getFormazione() {
		return formazione;
	}

	public void setFormazione(Formazione formazione) {
		this.formazione = formazione;
	}

	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Giocatore[] giocatori) {
		this.giocatori = giocatori;
	}

	public int getBudgetRimanente() {
		return budgetRimanente;
	}

	public void setBudgetRimanente(int budgetRimanente) {
		this.budgetRimanente = budgetRimanente;
	}
	
	
	
	
}
