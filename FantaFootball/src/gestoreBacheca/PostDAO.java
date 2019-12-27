package gestoreBacheca;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestoreUtente.Scout;
import gestoreUtente.ScoutDAO;

/**
 Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Post.
 * @author Pasquale Caramante
 */
public class PostDAO {
	Connection conn = null;
	
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
		return inserito;
	}
	
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
		return rimosso;
		
	}
	
	
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
		return modificato;
	}
	
	
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
		
		return post;
		
	}
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
		
		return post;
		
	}

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
		
		return post;
		
	}
	
}
