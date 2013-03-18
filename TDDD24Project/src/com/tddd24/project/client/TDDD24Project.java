package com.tddd24.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TDDD24Project implements EntryPoint {

	public static int USER_RANK = -1;
	public static int USER_ID = -1;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		String initToken = History.getToken();
		
		if(initToken.length() == 0){
			History.newItem("Login");
		}
		History.addValueChangeHandler(new MyHistoryListener());
		History.fireCurrentHistoryState();

		/** Create Menu bar --------------------------------------------------*/
		MenuBar mainMenu = new MenuBar();

		RootPanel.get("Menu").add(mainMenu);

		mainMenu.setAutoOpen(true);
		mainMenu.setWidth("1000px");
		mainMenu.setAnimationEnabled(true);

		// Create Startpage option    
		Command startpageCommand = new Command(){

			@Override
			public void execute() {
				History.newItem("TopicPage");
			}   	
		};
		mainMenu.addItem(new MenuItem("Startpage", startpageCommand));
		
		Command newTopicCommand = new Command() {
			
			@Override
			public void execute() {
				History.newItem("CreateTopic");
				
			}
		};
		mainMenu.addItem(new MenuItem("New topic", newTopicCommand));
				
		/** Create Login module ----------------------------------------------*//*
		LoginPanel loginDecPanel = new LoginPanel();
		RootPanel.get().add(loginDecPanel);
		
		*//** Create text editor for making new posts --------------------------*//*
		TextEditor editorDecPanel = new TextEditor();
		RootPanel.get().add(editorDecPanel);

		*//** Create a list/grid for posts, users, topics, categories, etc.-----*//*
		User user = new User(0, "Nike", 5, 1337, new Date(1997, 02, 02, 13, 37), new Date(2000, 02, 02, 13, 38));
		Post post = new Post(1, user, "hello world!!!", new Date(), new Date());

		FlexTable postTable = new FlexTable();
		postTable.setWidget(0, 0, post);

		RootPanel.get().add(postTable);
		
		*//** Add Category to root panel for testing TODO: Remove/move to other place*//*
		RootPanel.get().add(new Category(0, "Populära trådar", "Här finns alla de populäraste trådarna. " +
				"Välj och vraka mellan tusentals roliga och intressanta ämnen!----------------------------" +
				"-------------------------------------------------------------------------------------"));*/
		
	
	}
}
