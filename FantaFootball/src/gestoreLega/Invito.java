package gestoreLega;

import gestoreUtente.Allenatore;

/**
 * Questa classe rappresenta l'entità Invito.
 * @author Maria Natale
 */
public class Invito {
	private Allenatore allenatore;
	private Lega lega;
	private boolean risposta;
	
	/**
	 * @param allenatore allenatore invitato ad una lega
	 * @param lega lega a cui è invitato l'allenatore
	 * @param risposta risposta all'invito (vera o falsa)
	 * @return 
	 */
	public Invito(Allenatore allenatore, Lega lega, boolean risposta) {
		this.allenatore = allenatore;
		this.lega = lega;
		this.risposta = risposta;
	}

	/**
	 * @return allenatore allenatore invitato
	 */
	public Allenatore getAllenatore() {
		return allenatore;
	}

	/**
	 * @param allenatore allenatore da assegnare all'invito
	 * @return 
	 */
	public void setAllenatore(Allenatore allenatore) {
		this.allenatore = allenatore;
	}

	/**
	 * @return lega lega a cui è invitato l'allenatore
	 */
	public Lega getLega() {
		return lega;
	}

	/**
	 * @param lega lega da assegnare all'invito
	 * @return 
	 */
	public void setLega(Lega lega) {
		this.lega = lega;
	}

	/**
	 * @return risposta risposta all'invito
	 */
	public boolean isRisposta() {
		return risposta;
	}

	/**
	 * @param rispost risposta da assegnare all'invito
	 * @return 
	 */
	public void setRisposta(boolean risposta) {
		this.risposta = risposta;
	}
	
	

}
