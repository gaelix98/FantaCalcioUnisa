package gestoreUtente;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta l'entità Allenatore.
 * @author Maria Natale
 */

import gestoreSquadra.*;

public class Allenatore {
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	private List<Squadra> squadre;
	
	/**
	 * @param nome nome dell'allenatore
	 * @param cognome cognome dell'allenatore
	 * @param email email dell'allenatore
	 * @param username username dell'allenatore
	 * @param password password dell'allenatore
	 * @return 
	 */
	public Allenatore(String nome, String cognome, String email, String username, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
		this.password = password;
		this.squadre = new ArrayList<Squadra>();
	}

	/**
	 * @return nome nome dell'allenatore
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome nome da assegnare all'allenatore
	 * @return 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return cognome cognome dell'allenatore
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome cognome da assegnare all'allenatore
	 * @return 
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return email email dell'allenatore
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email email da assegnare all'allenatore
	 * @return 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return username username dell'allenatore
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username username da assegnare all'allenatore
	 * @return 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password password dell'allenatore
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password password da assegnare all'allenatore
	 * @return 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return squadre fantasquadre gestite dall'allenatore
	 */
	public List<Squadra> getSquadre() {
		return squadre;
	}

	/**
	 * @param squadre fantasquadre da assegnare all'allenatore
	 * @return 
	 */
	public void setSquadre(List<Squadra> squadre) {
		this.squadre = squadre;
	}
	
	
	

}
