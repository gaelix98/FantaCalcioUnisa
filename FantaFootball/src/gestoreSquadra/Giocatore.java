package gestoreSquadra;

/**
 * Questa classe rappresenta l'entità Giocatore
 * @author Pasquale Caramante
 *
 */
public class Giocatore {
	private String id;
	private String nome;
	private String cognome;
	private String ruolo;
	private String squadra;
	private int presenze;
	private float votoMedio;
	private int goal;
	private int assist;
	private int ammonizioni;
	private int espulsioni;
	private int rigoriSegnati;
	private int rigoriSbagliati;
	private int rigoriParati;
	
	
	/**
	 * @param id
	 * @param nome
	 * @param cognome
	 * @param ruolo
	 * @param squadra
	 * @param presenze
	 * @param votoMedio
	 * @param goal
	 * @param assist
	 * @param ammonizioni
	 * @param espulsioni
	 * @param rigoriSegnati
	 * @param rigoriSbagliati
	 * @param rigoriParati
	 */
	public Giocatore(String id, String nome, String cognome, String ruolo, String squadra) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
		this.squadra = squadra;
		this.presenze = 0;
		this.votoMedio = 0.0f;
		this.goal = 0;
		this.assist = 0;
		this.ammonizioni = 0;
		this.espulsioni = 0;
		this.rigoriSegnati = 0;
		this.rigoriSbagliati = 0;
		this.rigoriParati = 0;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @param cognome cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}
	
	/**
	 * @param ruolo ruolo
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	/**
	 * @return squadra
	 */
	public String getSquadra() {
		return squadra;
	}
	
	/**
	 * @param squadra squadra
	 */
	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}
	
	/**
	 * @return presenze
	 */
	public int getPresenze() {
		return presenze;
	}
	
	/**
	 * @param presenze presenze
	 */
	public void setPresenze(int presenze) {
		this.presenze = presenze;
	}
	
	/**
	 * @return votoMedio
	 */
	public float getVotoMedio() {
		return votoMedio;
	}
	
	/**
	 * @param votoMedio votoMedio
	 */
	public void setVotoMedio(float votoMedio) {
		this.votoMedio = votoMedio;
	}
	
	/**
	 * @return goal
	 */
	public int getGoal() {
		return goal;
	}
	
	/**
	 * @param goal goal
	 */
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	/**
	 * @return assist
	 */
	public int getAssist() {
		return assist;
	}
	
	/**
	 * @param assist assist
	 */
	public void setAssist(int assist) {
		this.assist = assist;
	}
	
	/**
	 * @return ammonizioni
	 */
	public int getAmmonizioni() {
		return ammonizioni;
	}
	
	/**
	 * @param ammonizioni ammonizioni
	 */
	public void setAmmonizioni(int ammonizioni) {
		this.ammonizioni = ammonizioni;
	}
	
	/**
	 * @return espulsioni
	 */
	public int getEspulsioni() {
		return espulsioni;
	}
	
	/**
	 * @param espulsioni espulsioni
	 */
	public void setEspulsioni(int espulsioni) {
		this.espulsioni = espulsioni;
	}
	
	/**
	 * @return rigoriSegnati
	 */
	public int getRigoriSegnati() {
		return rigoriSegnati;
	}
	
	/**
	 * @param rigoriSegnati rigoriSegnati
	 */
	public void setRigoriSegnati(int rigoriSegnati) {
		this.rigoriSegnati = rigoriSegnati;
	}
	
	/**
	 * @return rigoriSbagliati
	 */
	public int getRigoriSbagliati() {
		return rigoriSbagliati;
	}
	
	/**
	 * @param rigoriSbagliati rigoriSbagliati
	 */
	public void setRigoriSbagliati(int rigoriSbagliati) {
		this.rigoriSbagliati = rigoriSbagliati;
	}
	
	/**
	 * @return rigoriParati
	 */
	public int getRigoriParati() {
		return rigoriParati;
	}
	
	/**
	 * @param rigoriParati rigoriParati
	 */
	public void setRigoriParati(int rigoriParati) {
		this.rigoriParati = rigoriParati;
	}
	
	
}
