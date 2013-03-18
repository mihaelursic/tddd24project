package com.tddd24.project.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tddd24.project.client.Post;
import com.tddd24.project.client.Topic;
import com.tddd24.project.client.User;

public class DataManager {

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private static final String HOST = "jdbc:mysql://localhost:3306/tddd24forum";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "";

	private static Connection getConnection() throws SQLException {		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
		} catch (final ClassNotFoundException e) {
			throw new AssertionError(e);
		}
	}

	public static ArrayList<Topic> getAllTopics(){

		Connection connection = null;
		try {
			connection = getConnection();
			final ResultSet resultSet = connection
					.prepareStatement(
							"SELECT Subject, UserId, id, NrOfPosts" +
							" FROM topic")
							.executeQuery();

			ArrayList<Topic> list = new ArrayList<Topic>();

			while (resultSet.next()) {
				ResultSet set = connection.prepareStatement(
						"SELECT Name FROM user WHERE id = "+resultSet.getInt(2)).executeQuery();
				String userName = "";
				if (set.next()){
					userName = set.getString(1);
				}
				Topic topic = new Topic(resultSet.getInt(3), resultSet.getString(1), userName, resultSet.getInt(4), "");
				list.add(topic);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	public static ArrayList<Post> getPostsFromTopic(int topicId) {

		Connection connection = null;
		try {
			connection = getConnection();
			final ResultSet resultSet = connection
					.prepareStatement(
							"SELECT PostNr, TimeCreated, LastUpdated, Content, UserId" +
									" FROM post WHERE TopicId = "+topicId)
									.executeQuery();

			ArrayList<Post> list = new ArrayList<Post>();

			while (resultSet.next()) {
				ResultSet set = connection.prepareStatement(
						"SELECT * FROM user WHERE id = "+resultSet.getInt(5)).executeQuery();
				User user = new User();
				if (set.next()){
					user.setId(set.getInt("id"));
					user.setBanned(set.getDate("Banned"));
					user.setName(set.getString("Name"));
					user.setNrOfPosts(set.getInt("NrOfPosts"));
					user.setRank(set.getInt("Rank"));
					user.setRegistered(set.getDate("Registered"));
				}
				Post post = new Post(resultSet.getInt(1), user, resultSet.getString(4),
						resultSet.getDate(2), resultSet.getDate(3));
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static ArrayList<Integer> checkLogin(String userName, String password) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection connection = null;
		try {
			connection = getConnection();
			final ResultSet resultSet = connection
					.prepareStatement(
							"SELECT Rank, id" +
									" FROM user WHERE Name = '"+userName+"' AND Password = '"+password+"'")
									.executeQuery();

			if (resultSet.next()) {
				list.add(0, resultSet.getInt(1));
				list.add(1, resultSet.getInt(2));
				return list/*new int[] {resultSet.getInt(1), resultSet.getInt(2)}*/;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void addPostInTopic(String html, int topicId, int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			ResultSet resultSet = connection
					.prepareStatement(
							"SELECT MAX(PostNr)" +
									" FROM post WHERE TopicId = "+topicId)
									.executeQuery();
			
			int postNr = 1;
			if(resultSet.next()){
				postNr = resultSet.getInt(1)+1;
			}

			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO post (PostNr, TimeCreated," +
							" LastUpdated, Content, TopicId, UserId) " +
							"VALUES (?,?,?,?,?,?)");

			stmt.setInt(1, postNr);
			stmt.setDate(2, new Date(System.currentTimeMillis()));
			stmt.setDate(3, new Date(System.currentTimeMillis()));
			stmt.setString(4, html);
			stmt.setInt(5, topicId);
			stmt.setInt(6, userId);
			
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement(
					"UPDATE user SET NrOfPosts = (NrOfPosts +1) WHERE id = "+userId);
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement(
					"UPDATE topic SET NrOfPosts = (NrOfPosts +1) WHERE id = "+topicId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void banUser(int userId) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					"UPDATE user SET Rank = -1 WHERE id = "+ userId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removePost(int topicId, int PostNr){
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					"UPDATE post SET Content = '--- Post removed ---' " +
					"WHERE (PostNr = "+ PostNr + " AND TopicId = "+topicId+")");
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeTopic(int id){
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					"DELETE FROM topic WHERE id = "+ id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addTopic(String subject, String content, int userId) {
		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO topic (Subject, NrOfPosts, UserId, CategoryId) VALUES (?,?,?,?) ");
			
			stmt.setString(1, subject);
			stmt.setInt(2, 0);
			stmt.setInt(3, userId);
			stmt.setInt(4, 1);
			
			stmt.executeUpdate();
			
			int topic = -1;
			ResultSet set = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM topic").executeQuery();
			if(set.next()){
				topic = set.getInt(1);
				addPostInTopic(content, topic, userId);
			}else{
				throw new Exception();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();

		}
		
	}

}
