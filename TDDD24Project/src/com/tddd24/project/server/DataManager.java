package com.tddd24.project.server;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public static int checkLogin(String userName, String password) {
		
		Connection connection = null;
		try {
			connection = getConnection();
			final ResultSet resultSet = connection
					.prepareStatement(
							"SELECT Rank" +
							" FROM user WHERE Name = '"+userName+"' AND Password = '"+password+"'")
							.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
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
	
	
	
	
}
