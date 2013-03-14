package com.tddd24.project.client;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPanel extends DecoratorPanel {

	private static TDDD24ProjectServiceAsync TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
	private static LoginPanel _instance = null;

	private LoginPanel(){

		final FlexTable loginTable = new FlexTable();
		loginTable.setCellSpacing(6);
		FlexCellFormatter cellFormatter = loginTable.getFlexCellFormatter();

		// Add a title to the form
		loginTable.setHTML(0, 0, "Log in");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(
				0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Add input forms
		final TextBox userName = new TextBox();
		final PasswordTextBox password = new PasswordTextBox();
		KeyPressHandler loginHandler = new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER){
					login(userName.getText(), password.getText());
					System.out.println("rank: "+TDDD24Project.USER_RANK);
					if(TDDD24Project.USER_RANK != -1){

						userName.setText("");
						password.setText("");
						History.newItem("TopicPage");

					}
				}
			}
		};
		userName.addKeyPressHandler(loginHandler);
		password.addKeyPressHandler(loginHandler);

		loginTable.setHTML(1, 0, "Username");
		loginTable.setWidget(1, 1, userName);
		loginTable.setHTML(2, 0, "Password");
		loginTable.setWidget(2, 1, password);

		// Add login button
		Button loginBtn = new Button("Log in", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) { // TODO
				login(userName.getText(), password.getText());
				if(TDDD24Project.USER_RANK != -1){

					userName.setText("");
					password.setText("");


				}
			}
		});
		loginTable.setWidget(3, 1, loginBtn);

		// Wrap the content in the DecoratorPanel
		this.setWidget(loginTable);

	}

	public static LoginPanel getInstance(){
		if(_instance == null){
			_instance = new LoginPanel();
		}
		return _instance;
	}

	private void login(String userName, String password){
		// Initialize the service proxy.
		if (TDDD24ProjectSvc == null) {
			TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
		}

		// Set up the callback object.
		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Integer result) {

				int rank = (int)result;
				TDDD24Project.USER_RANK = rank;

				switch (rank) {
				case -1 :
					Window.alert("Login failed. Check your user name and password");
					break;
				case 1 :
//					History.newItem("TopicPage");
					break;
				case 2 :
//					History.newItem("TopicPage");
					break;
				}
			}
		};

		TDDD24ProjectSvc.checkLogin(userName, password, callback);

	}

}
