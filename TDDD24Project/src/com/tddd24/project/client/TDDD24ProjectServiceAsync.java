package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TDDD24ProjectServiceAsync {

	void getAllTopics(AsyncCallback<ArrayList<Topic>> callback);

	void getPostsFromTopic(int topicId,
			AsyncCallback<ArrayList<Post>> callback);

	void checkLogin(String userName, String password, AsyncCallback<ArrayList<Integer>> callback);

	void addPostInThread(String html, int topicId, int userId, AsyncCallback<Boolean> callback);

}
