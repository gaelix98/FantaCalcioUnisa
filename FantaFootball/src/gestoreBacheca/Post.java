package gestoreBacheca;

import java.sql.Date;

import gestoreUtente.Scout;

/**
 * Questa classe rappresenta l'entità Post.
 * @author Pasquale Caramante
 */
public class Post {
	private int idPost;
	private Date data;
	private String titolo;
	private String testo;
	private Scout scout;
	
	/**
	 * @param idPost id del post
	 * @param data data di pubblicazione del post
	 * @param titolo titolo del post
	 * @param testo testo contenuto nel post
	 * @param scout lo scout autore del post
	 */
	public Post(int idPost, Date data, String titolo, String testo, Scout scout) {
		this.idPost = idPost;
		this.data = data;
		this.titolo = titolo;
		this.testo = testo;
		this.scout = scout;
	}
	
	/**
	 * @return idPost id del post
	 */
	public int getIdPost() {
		return idPost;
	}

	/**
	 * @param idPost idPost id da assegnare al post
	 * @return
	 */
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	
	/**
	 * @return data data di pubblicazione del post
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data data di pubblicazione da assegnare al post
	 * @return
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
	/**
	 * @return titolo titolo del post
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo titolo da assegnare al post
	 * @return
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return testo testo contenuto nel post
	 */
	public String getTesto() {
		return testo;
	}

	/**
	 * @param testo testo da inserire nel post
	 * @return
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	/**
	 * @return scout scout autore del post
	 */
	public Scout getScout() {
		return scout;
	}

	/**
	 * @param scout scout da assegnare al post come autore
	 * @return
	 */
	public void setScout(Scout scout) {
		this.scout = scout;
	}
	
	
	
}
