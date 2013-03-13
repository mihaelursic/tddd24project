package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TDDD24ProjectServiceAsync {

	void getAllTopics(AsyncCallback<ArrayList<Topic>> callback);

}
