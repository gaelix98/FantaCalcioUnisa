package GestoreLega;

import java.util.HashSet;
import java.util.Set;

import GestoreSquadra.Squadra;
import GestoreUtente.Allenatore;

/**
 * Questa classe rappresenta l'entità Lega.
 * @author Maria Natale
 */
public class Lega {
	private String nome;
	private String logo;
	private int maxAllenatori;
	private int quotaMensile;
	private int budget;
	private int primoPosto;
	private int secondoPosto;
	private int terzoPosto;
	private Allenatore presidente;
	private Set<Partita> partite;
	private Set<Squadra> squadre;
	
	
	/**
	 * 
	 * @param nome nome univoco da assegnare alla lega
	 * @param logo logo da assegnare alla lega
	 * @param maxAllenatori numero massimo di allenatori partecipanti alla lega
	 * @param quotaMensile quota che ogni allenatore deve pagare mensilmente
	 * @param budget budget che ogni squadra ha a disposizione
	 * @param primoPosto percentuale di vincita per il primo posto 
	 * @param secondoPosto percentuale di vincita per il secondo posto
	 * @param terzoPosto percentuale di vincita per il terzo posto
	 * @param presidente allenatore che ha creato la lega
	 */
	public Lega(String nome, String logo, int maxAllenatori, int quotaMensile, int budget, int primoPosto,
			int secondoPosto, int terzoPosto, Allenatore presidente) {
		this.nome = nome;
		this.logo = logo;
		this.maxAllenatori = maxAllenatori;
		this.quotaMensile = quotaMensile;
		this.budget = budget;
		this.primoPosto = primoPosto;
		this.secondoPosto = secondoPosto;
		this.terzoPosto = terzoPosto;
		this.presidente = presidente;
		this.partite=new HashSet<>();
		this.squadre=new HashSet<>();
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

	public int getMaxAllenatori() {
		return maxAllenatori;
	}

	public void setMaxAllenatori(int maxAllenatori) {
		this.maxAllenatori = maxAllenatori;
	}

	public int getQuotaMensile() {
		return quotaMensile;
	}

	public void setQuotaMensile(int quotaMensile) {
		this.quotaMensile = quotaMensile;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getPrimoPosto() {
		return primoPosto;
	}

	public void setPrimoPosto(int primoPosto) {
		this.primoPosto = primoPosto;
	}

	public int getSecondoPosto() {
		return secondoPosto;
	}

	public void setSecondoPosto(int secondoPosto) {
		this.secondoPosto = secondoPosto;
	}

	public int getTerzoPosto() {
		return terzoPosto;
	}

	public void setTerzoPosto(int terzoPosto) {
		this.terzoPosto = terzoPosto;
	}

	public Allenatore getPresidente() {
		return presidente;
	}

	public void setPresidente(Allenatore presidente) {
		this.presidente = presidente;
	}

	public Set<Partita> getPartite() {
		return partite;
	}

	public void setPartite(Set<Partita> partite) {
		this.partite = partite;
	}

	public Set<Squadra> getSquadre() {
		return squadre;
	}

	public void setSquadre(Set<Squadra> squadre) {
		this.squadre = squadre;
	}
	
	

}
