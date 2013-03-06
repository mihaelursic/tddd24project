package com.tddd24.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public class Category extends DecoratorPanel {

	int catId;
	String catName;
	String description;
	
	Grid mainGrid;

	public Category(int catId, String catName, String description) {
		super();
		
		// Create grid
		mainGrid = new Grid(1,2);
		
		// Instantiate
		this.catId = catId;
		this.catName = catName;
		this.description = description;

		// Create name and desc. labels and add to grid
		Label name = new Label(catName);
		name.setWidth("350px");
		name.addClickHandler(new ClickHandler() { // TODO: Add style så man ser att den är klickbar
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("TODO: Byt sida till tråden");
				
			}
		});
		mainGrid.setWidget(0, 0, name);
		
		Label desc = new Label(description);
		desc.setWordWrap(true);
		mainGrid.setWidget(0, 1, desc);

		// Add everything to post decorate grid
		this.setWidth("100%");
		this.setWidget(mainGrid);
	}
	
}
