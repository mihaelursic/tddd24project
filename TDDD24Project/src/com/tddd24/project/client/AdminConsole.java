package com.tddd24.project.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdminConsole extends Composite {

	private static AdminConsole _instance = null;
	private VerticalPanel panel;
	private static TDDD24ProjectServiceAsync TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
	
	Label banUserLbl = new Label("Ban user with id for one week:");
	final TextBox banUserTextBox = new TextBox();
	final Button banUserBtn = new Button("Ban");
	
	Label rmvTopicLbl = new Label("Remove current topic");
//	final TextBox rmvTopicTextBox = new TextBox();
	final Button rmvTopicBtn = new Button("Remove");
	
	
	Label rmvPostLbl = new Label("Remove post in current thread with post number:");
	final TextBox rmvPostTextBox = new TextBox();
	final Button rmvPostBtn = new Button("Remove");

	public AdminConsole(){

		panel = new VerticalPanel();
		initWidget(panel);


		KeyPressHandler keyHandler = new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode() == KeyCodes.KEY_ENTER){
					if(event.getSource().equals(banUserTextBox)){
						banUser(Integer.parseInt(banUserTextBox.getText()));
					}
//					else if(event.getSource().equals(rmvTopicTextBox)){
//						removeTopic(Integer.parseInt(rmvTopicTextBox.getText()));
//					}
					else if(event.getSource().equals(rmvPostTextBox)){
						removePost(Thread.CURRENTLY_SHOWN_TOPIC, Integer.parseInt(rmvPostTextBox.getText()));
					}
				}
			}
		};

		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(event.getSource().equals(banUserBtn)){
					banUser(Integer.parseInt(banUserTextBox.getText()));
				}
				else if(event.getSource().equals(rmvTopicBtn)){
//					removeTopic(Integer.parseInt(rmvTopicTextBox.getText()));
					removeTopic(Thread.CURRENTLY_SHOWN_TOPIC);
				}
				else if(event.getSource().equals(rmvPostBtn)){
					removePost(Thread.CURRENTLY_SHOWN_TOPIC, Integer.parseInt(rmvPostTextBox.getText()));
				}

			}
		};

		banUserTextBox.addKeyPressHandler(keyHandler);

//		rmvTopicTextBox.addKeyPressHandler(keyHandler);

		rmvPostTextBox.addKeyPressHandler(keyHandler);
		
		banUserBtn.addClickHandler(clickHandler);
		rmvPostBtn.addClickHandler(clickHandler);
		rmvTopicBtn.addClickHandler(clickHandler);
		
		panel.add(banUserLbl);
		panel.add(banUserTextBox);
		panel.add(banUserBtn);
		
		panel.add(rmvPostLbl);
		panel.add(rmvPostTextBox);
		panel.add(rmvPostBtn);
		
		panel.add(rmvTopicLbl);
//		panel.add(rmvTopicTextBox);
		panel.add(rmvTopicBtn);

	}

	private void removeTopic(int topicId){

		// Initialize the service proxy.
		if (TDDD24ProjectSvc == null) {
			TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
		}

		// Set up the callback object.
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				clearTextBoxes();
				Window.alert("Topic removed");
				History.newItem("TopicPage");
			}

		};

		TDDD24ProjectSvc.removeTopic(topicId, callback);

	}


	private void removePost(int topicId, int postNr){


		// Initialize the service proxy.
		if (TDDD24ProjectSvc == null) {
			TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
		}

		// Set up the callback object.
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				clearTextBoxes();
				Window.alert("Post removed");

			}

		};

		TDDD24ProjectSvc.removePost(topicId, postNr, callback);
		
	}

	private void banUser(int userId){


		// Initialize the service proxy.
		if (TDDD24ProjectSvc == null) {
			TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
		}

		// Set up the callback object.
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("User banned for one week");

				clearTextBoxes();
			}

		};

		TDDD24ProjectSvc.banUser(userId, callback);
		
	}

	protected void clearTextBoxes() {
		rmvPostTextBox.setText("");
//		rmvTopicTextBox.setText("");
		banUserTextBox.setText("");
		
	}

	public static AdminConsole getInstance() {
		if(_instance == null){
			_instance = new AdminConsole();
		}
		return _instance;
	}

}
