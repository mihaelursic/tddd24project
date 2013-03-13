package com.tddd24.project.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class MyHistoryListener implements ValueChangeHandler<String> {

	public void onValueChange(ValueChangeEvent<String> event) {

		if(event.getValue().equals("Login")){
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(LoginPanel.getInstance());
		}
		else if(event.getValue().equals("TopicPage")){
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(TopicPage.getInstance());
		}
		
	}

}
