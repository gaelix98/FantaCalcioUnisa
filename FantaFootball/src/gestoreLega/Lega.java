package gestoreLega;

import java.util.HashSet;
import java.util.Set;

import gestoreSquadra.Squadra;
import gestoreUtente.Allenatore;

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
	}

	/**
	 *@return nome nome univoco della lega
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome nome univoco da assegnare alla lega
	 * @return
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * 
	 * @return logo logo assegnato alla lega
	 */
	public String getLogo() {
		return logo;
	}
	
	/**
	 * 
	 * @param logo logo da assegnare alla lega
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 
	 * @return maxAllenatori numero massimo di allenatori partecipanti alla lega
	 */
	public int getMaxAllenatori() {
		return maxAllenatori;
	}
	
	/**
	 * 
	 * @param maxAllenatori numero massimo di allenatori partecipanti da assegnare alla lega
	 */
	public void setMaxAllenatori(int maxAllenatori) {
		this.maxAllenatori = maxAllenatori;
	}
	
	/**
	 * 
	 * @return quotaMensile quota che ogni allenatore di questa lega deve pagare mensilmente
	 */
	public int getQuotaMensile() {
		return quotaMensile;
	}
	
	/**
	 * 
	 * @param quotaMensile quota che ogni allenatore di questa lega deve pagare mensilmente da assegnare a questa lega
	 */
	public void setQuotaMensile(int quotaMensile) {
		this.quotaMensile = quotaMensile;
	}
	
	/**
	 * 
	 * @return budget budget iniziale che ogni squadra della lega ha disposizione
	 */
	public int getBudget() {
		return budget;
	}
	
	/**
	 * 
	 * @param budget budget iniziale da assegnare
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	/**
	 * 
	 * @return primoPosto percentuale di vincita del primo posto
	 */
	public int getPrimoPosto() {
		return primoPosto;
	}
	
	/**
	 * 
	 * @param primoPosto percentuale di vincita del primo posto da assegnare alla lega
	 */
	public void setPrimoPosto(int primoPosto) {
		this.primoPosto = primoPosto;
	}
	
	/**
	 * 
	 * @return secondoPosto percentuale di vincita del secondo posto
	 */
	public int getSecondoPosto() {
		return secondoPosto;
	}
	
	/**
	 * 
	 * @param secondoPosto percentuale di vincita del secondo posto da assegnare alla lega
	 */
	public void setSecondoPosto(int secondoPosto) {
		this.secondoPosto = secondoPosto;
	}
	
	/**
	 * 
	 * @return terzoPosto percentuale di vincita del terzo posto
	 */
	public int getTerzoPosto() {
		return terzoPosto;
	}
	
	/**
	 * 
	 * @param terzoPosto percentuale di vincita del terzo posto da assegnare alla lega
	 */
	public void setTerzoPosto(int terzoPosto) {
		this.terzoPosto = terzoPosto;
	}
	
	/**
	 * 
	 * @return presidente l'allenatore che ha creato la lega
	 */
	public Allenatore getPresidente() {
		return presidente;
	}

	/**
	 * 
	 * @param presidente l'allenatore che ha creato la lega
	 */
	public void setPresidente(Allenatore presidente) {
		this.presidente = presidente;
	}
	
	/**
	 * 
	 * @return partite l'insieme delle partite di questa lega
	 */
	public Set<Partita> getPartite() {
		return partite;
	}

	/**
	 * 
	 * @param partite l'insieme delle partite da assegnare a questa lega
	 */
	public void setPartite(Set<Partita> partite) {
		this.partite = partite;
	}
	

}
