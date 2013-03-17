package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("TDDD24Service")
public interface TDDD24ProjectService extends RemoteService {

	public ArrayList<Topic> getAllTopics();

	ArrayList<Post> getPostsFromTopic(int topicId);

	ArrayList<Integer> checkLogin(String userName, String password);

	boolean addPostInThread(String html, int topicId, int userId);
	
}
