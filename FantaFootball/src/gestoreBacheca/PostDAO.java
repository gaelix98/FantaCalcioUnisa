package gestoreBacheca;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreUtente.Scout;
import gestoreUtente.ScoutDAO;

/**
 Questa classe � un manager che si occupa di interagire con il database. Gestisce le query riguardanti Post.
 * @author Pasquale Caramante
 */
public class PostDAO {
	Connection conn = null;
	
	/**
	 * 
	 * @param post post da aggiungere
	 * @return true if database.post-> includes(select(p| p.id=post.getId())), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean addPost(Post post) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean inserito = false;
		int ris;
		String sql = "Insert into post(DataPubblicazione,Titolo,Testo,Scout) values (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, post.getData().toString());
		ps.setString(2, post.getTitolo());
		ps.setString(3, post.getTesto());
		ps.setString(4, post.getScout().getUsername());
		
		try {
			ris = ps.executeUpdate();
			if(ris==1) {
				inserito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return inserito;
	}

	/**
	 * 
	 * @param id id del post da rimuovere
	 * @return true if database.post->not includes(select(p| p.id=id)), false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean removePost(int id) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean rimosso = false;
		int ris;
		String sql = "delete from post where post.idPost = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		try {
			ris = ps.executeUpdate();
			if(ris==1) {
				rimosso = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return rimosso;
		
	}
	
	/**
	 * 
	 * @param post post da aggiornare 
	 * @return true se il post � stato aggiornato, false altrimenti
	 * @throws SQLException
	 */
	public synchronized boolean updatePost(Post post) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		boolean modificato = false;
		int ris;
		String sql = "Update post set post.DataPubblicazione = ?,post.Titolo = ?,post.Testo = ? ,post.Scout = ? where post.idPost = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, post.getData().toString());
		ps.setString(2, post.getTitolo());
		ps.setString(3, post.getTesto());
		ps.setString(4, post.getScout().getUsername());
		ps.setInt(5, post.getIdPost());
		
		try {
			ris = ps.executeUpdate();
			if(ris==1) {
				modificato = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return modificato;
	}
	
	/**
	 * 
	 * @param scout username dello scout di cui si vogliono cercare i post
	 * @return post->select(p|post.scout=scout)
	 * @throws SQLException
	 */
	public synchronized ArrayList<Post> getPostByScout(String scout) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Post> post = new ArrayList<Post>();
		Scout scouttmp = new ScoutDAO().getScoutByUsername(scout);
		String sql = "select * from post where post.scout= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, scout);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Post tmp = null;
			int id = rs.getInt("idPost");
			Date data = null;
			data = Date.valueOf(rs.getString("dataPubblicazione"));
			String titolo = rs.getString("Titolo");
			String testo = rs.getString("Testo");
			tmp = new Post(id,data,titolo,testo,scouttmp);
			
			post.add(tmp);
		}
		conn.close();
		return post;
	}
	
	public synchronized Post getUltimoPostByScout(String scout) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		
		Scout scouttmp = new ScoutDAO().getScoutByUsername(scout);
		String sql = "select * from post where post.scout= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, scout);
		ResultSet rs = ps.executeQuery();
		Post tmp = null;
		while(rs.next()) {
			
			int id = rs.getInt("idPost");
			Date data = null;
			data = Date.valueOf(rs.getString("dataPubblicazione"));
			String titolo = rs.getString("Titolo");
			String testo = rs.getString("Testo");
			tmp = new Post(id,data,titolo,testo,scouttmp);
			
			
		}
		conn.close();
		return tmp;
	}
	
	/**
	 * 
	 * @return database.post
	 * @throws SQLException
	 */
	public synchronized ArrayList<Post> getAllPost() throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<Post> post = new ArrayList<Post>();
		
		String sql = "select * from post order by dataPubblicazione desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Post tmp = null;
			int id = rs.getInt("idPost");
			Date data = null;
			data = Date.valueOf(rs.getString("dataPubblicazione"));
			String titolo = rs.getString("Titolo");
			String testo = rs.getString("Testo");
			Scout scouttmp = new ScoutDAO().getScoutByUsername(rs.getString("Scout"));
			tmp = new Post(id,data,titolo,testo,scouttmp);
			
			post.add(tmp);
		}
		conn.close();
		return post;
		
	}

	/**
	 * 
	 * @param idPost id del post da cercare
	 * @return post->select(p|post.idPost=idPost)
	 * @throws SQLException
	 */
	public synchronized Post getPostById(int idPost) throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		Post post=null;
		
		String sql = "select * from post where idPost=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idPost);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("idPost");
			Date data = null;
			data = Date.valueOf(rs.getString("dataPubblicazione"));
			String titolo = rs.getString("Titolo");
			String testo = rs.getString("Testo");
			Scout scouttmp = new ScoutDAO().getScoutByUsername(rs.getString("Scout"));
			post = new Post(id,data,titolo,testo,scouttmp);
		}
		conn.close();
		return post;
		
	}
	
}
