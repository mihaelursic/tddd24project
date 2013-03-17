package com.tddd24.project.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tddd24.project.client.Post;
import com.tddd24.project.client.TDDD24ProjectService;
import com.tddd24.project.client.Topic;

public class TDDD24ProjectServiceImpl extends RemoteServiceServlet implements TDDD24ProjectService {

	@Override
	public ArrayList<Topic> getAllTopics(){
		
//		ArrayList<Topic> hej = DataManager.getAllTopics();
//		Topic topic = hej.get(0);
//		
//		System.out.println("Topic: "+topic.getSubject());
		
		return DataManager.getAllTopics();
	}

	@Override
	public ArrayList<Post> getPostsFromTopic(int topicId) {
		return DataManager.getPostsFromTopic(topicId);
	}

	@Override
	public ArrayList<Integer> checkLogin(String userName, String password) {
		return DataManager.checkLogin(userName, password);
		
	}

	@Override
	public boolean addPostInThread(String html, int topicId, int userId) {

		DataManager.addPostInTopic(html, topicId, userId);
		
		
		return false;
	}
	
	public void banUser(int userId){
		DataManager.banUser(userId);
	}
	
	public void removePost(int PostNr){
		DataManager.removePost(PostNr);
	}
	
	public void removeTopic(int id){
		DataManager.removeTopic(id);
	}
}
