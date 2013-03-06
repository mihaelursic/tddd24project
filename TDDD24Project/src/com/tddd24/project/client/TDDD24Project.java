package com.tddd24.project.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.PageSizePager;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TDDD24Project implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		/** Create Menu bar --------------------------------------------------*/
		MenuBar mainMenu = new MenuBar();

		RootPanel.get().add(mainMenu);

		mainMenu.setAutoOpen(true);
		mainMenu.setWidth("1000px");
		mainMenu.setAnimationEnabled(true);

		// Create Startpage option    
		Command startpageCommand = new Command(){

			@Override
			public void execute() {
				System.out.println("hej");			
			}   	
		};
		mainMenu.addItem(new MenuItem("Startpage", startpageCommand));

		/** Create Login module ----------------------------------------------*/
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

		// Wrap the content in a DecoratorPanel
		DecoratorPanel loginDecPanel = new DecoratorPanel();
		loginDecPanel.setWidget(loginTable);
		RootPanel.get().add(loginDecPanel);
		
		/** Create text editor for making new posts --------------------------*/
		Grid editorGrid = new Grid(3,2);
		final RichTextArea textArea = new RichTextArea();
		textArea.ensureDebugId("cwRichText-area");
		textArea.setSize("100%", "14em");
		RichTextToolbar toolbar = new RichTextToolbar(textArea);
		toolbar.ensureDebugId("cwRichText-toolbar");
		toolbar.setWidth("100%");
		editorGrid.setWidget(0, 0, toolbar);
		editorGrid.setWidget(1, 0, textArea);
		DecoratorPanel editorDecPanel = new DecoratorPanel();
		
		// Create buttons for posting
		Button postBtn = new Button("Post");
		//Button saveBtn = new Button(); // TODO: Ska vi ha det???
		Button cancelBtn = new Button("Cancel");
		Button updateBtn = new Button("Update");
		
		// Add listeners to buttons
		postBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("TODO!\n getHTML(): "+ textArea.getHTML());
				
			}
		});
		cancelBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		updateBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Create button grid and add to editorGrid
		Grid btnGrid = new Grid(1,3);
		btnGrid.setWidget(0, 0, cancelBtn);
		btnGrid.setWidget(0, 1, updateBtn);
		btnGrid.setWidget(0, 2, postBtn);
		editorGrid.setWidget(2, 0, btnGrid);
		
		editorDecPanel.setWidget(editorGrid);
		RootPanel.get().add(editorDecPanel);


		/** Create a list/grid for posts, users, topics, categories, etc.-----*/
		User user = new User(0, "Nike", 5, 1337, new Date(1997, 02, 02, 13, 37), new Date(2000, 02, 02, 13, 38));
		Post post = new Post(1, user, "hello world!!!", new Date(), new Date());

		FlexTable postTable = new FlexTable();
		postTable.setWidget(0, 0, post);
		

		
		RootPanel.get().add(postTable);
		
		/** Add Category to root panel for testing TODO: Remove/move to other place*/
		RootPanel.get().add(new Category(0, "Populära trådar", "Här finns alla de populäraste trådarna. " +
				"Välj och vraka mellan tusentals roliga och intressanta ämnen!----------------------------" +
				"-------------------------------------------------------------------------------------"));
		
	}
}
