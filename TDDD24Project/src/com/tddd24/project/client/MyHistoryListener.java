package com.tddd24.project.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class MyHistoryListener implements ValueChangeHandler<String> {

	public void onValueChange(ValueChangeEvent<String> event) {

		if(event.getValue().equals("Login")){
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(LoginPanel.getInstance());
		}
		else if(event.getValue().equals("TopicPage")){
			if(TDDD24Project.USER_RANK != -1){
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(TopicPage.getInstance());
			}
			else{
				History.newItem("Login");
			}
		}
		else if(event.getValue().equals("Thread")){	

			if(Thread.CURRENTLY_SHOWN_TOPIC == -1){
				History.newItem("TopicPage");
			}else{
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(Thread.getInstance());
			}
		}
		else if(event.getValue().equals("CreateTopic")){	

			if(TDDD24Project.USER_RANK != -1){
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(NewTopicPage.getInstance());
			}
			else{
				History.newItem("Login");
			}
		}

	}

}
