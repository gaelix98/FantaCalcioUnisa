package gestoreUtente;

/**
 * Questa classe rappresenta l'entità Scout.
 * @author Maria Natale
 */
public class Scout {
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	
	/**
	 * @param nome nome dello scout
	 * @param cognome cognome scout
	 * @param email email dello scout
	 * @param username username dello scout
	 * @param password password dello scout
	 * @return 
	 */
	public Scout(String nome, String cognome, String email, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return nome nome dello scout
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome nome da assegnare allo scout
	 * @return 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return cognome cognome dello scout
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome cognome da assegnare allo scout
	 * @return 
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return email email dello scout
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email email da assegnare allo scout
	 * @return 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return username username dello scout
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username username da assegnare allo scout
	 * @return 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password password dello scout
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password password da assegnare allo scout
	 * @return 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

}
