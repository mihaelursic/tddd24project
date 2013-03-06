package com.tddd24.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class LoginPanel extends DecoratorPanel {

	public LoginPanel(){
		
	final FlexTable loginTable = new FlexTable();
	loginTable.setCellSpacing(6);
	FlexCellFormatter cellFormatter = loginTable.getFlexCellFormatter();

	// Add a title to the form
	loginTable.setHTML(0, 0, "Log in");
	cellFormatter.setColSpan(0, 0, 2);
	cellFormatter.setHorizontalAlignment(
			0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	// Add input forms
	TextBox userName = new TextBox();
	PasswordTextBox password = new PasswordTextBox();
	KeyPressHandler loginHandler = new KeyPressHandler() {

		@Override
		public void onKeyPress(KeyPressEvent event) { // TODO
			if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER){
				Window.alert("TODO!\nUsername: "+((TextBox)loginTable.getWidget(1, 1)).getText() +
						"\nPassword: "+((PasswordTextBox)loginTable.getWidget(2, 1)).getText());
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
			Window.alert("TODO!\nUsername: "+((TextBox)loginTable.getWidget(1, 1)).getText() +
					"\nPassword: "+((PasswordTextBox)loginTable.getWidget(2, 1)).getText());
		}
	});
	loginTable.setWidget(3, 1, loginBtn);

	// Wrap the content in the DecoratorPanel
	this.setWidget(loginTable);
	
	}
}
