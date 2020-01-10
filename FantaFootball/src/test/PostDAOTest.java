package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DriverManagerConnectionPool;
import gestoreBacheca.Post;
import gestoreBacheca.PostDAO;
import gestoreUtente.Scout;
import gestoreUtente.ScoutDAO;

/**
 * 
 * @author Pasquale Caramante
 *
 */

public class PostDAOTest {
private static PostDAO postDAO = new PostDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
    }
	
	@Test
	public void addPost() throws SQLException{
		ScoutDAO scoutDAO = new ScoutDAO();
		Scout scout = scoutDAO.getScoutByUsername("Narducci2000");
		assertNotNull(scout);
		Post post = new Post(Date.valueOf("2020-01-04"),"Guida all'asta del fantacalcio 2019/2020","prova prova prova prova",scout);
		ArrayList<Post> postList = postDAO.getAllPost();
		assertEquals(3, postList.size());
		postDAO.addPost(post);
		postList = postDAO.getAllPost();
		assertEquals(4,postList.size());
	}
	
	@Test
	public void removePost() throws SQLException{
		Post post = postDAO.getPostById(1);
		assertNotNull(post);
		postDAO.removePost(1);
		post = postDAO.getPostById(1);
		assertNull(post);
	}
	
	@Test
	public void updatePost() throws SQLException{
		Post post = postDAO.getPostById(1);
		assertNotNull(post);
		assertEquals(Date.valueOf("2019-12-24"),post.getData());
		post.setData(Date.valueOf("2020-01-04"));
		postDAO.updatePost(post);
		post = postDAO.getPostById(1);
		assertEquals(Date.valueOf("2020-01-04"),post.getData());
	}
	@Test
	public void getPostByScout() throws SQLException{
		ArrayList<Post> post = postDAO.getPostByScout("Narducci2000");
		assertEquals(1,post.size());
	}
	
	@Test
	public void getAllPost() throws SQLException{
		ArrayList<Post> postList = postDAO.getAllPost();
		assertEquals(3, postList.size());
	} 
	@Test
	public void getPostById() throws SQLException{
		Post post = postDAO.getPostById(1);
		assertNotNull(post);
	}
}
