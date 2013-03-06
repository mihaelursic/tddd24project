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
				System.out.println("hej");			//TODO: Remove sysout
			}   	
		};
		mainMenu.addItem(new MenuItem("Startpage", startpageCommand));

		/** Create Login module ----------------------------------------------*/
		LoginPanel loginDecPanel = new LoginPanel();
		RootPanel.get().add(loginDecPanel);
		
		/** Create text editor for making new posts --------------------------*/
		TextEditor editorDecPanel = new TextEditor();
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
