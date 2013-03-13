package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;

public class CategoryPage extends Composite {

	private Grid grid;
	private FlexTable catList;

	/**
	 * Creates a new (empty) {@link CategoryPage} with the specified {@link Category}s.
	 * To populate the page with other <code>Category</code>s, use method {@link #populate()}.
	 * @param categories {@link Category} to populate the page.
	 * @see Category
	 */
	public CategoryPage(ArrayList<Category> categories){

		this();
		populate(categories);
		
	}

	/**
	 * Creates a new (empty) {@link CategoryPage}. 
	 * To populate the page, use method {@link #populate()}.
	 * @see Category
	 */
	public CategoryPage(){

		// Make this CategoryPage to a Grid with 2 rows and 1 column
//		super(2,1);
		grid = new Grid(2,1);

		catList = new FlexTable();
		grid.setWidget(0, 0, catList);
		
		// Create Buttons on the bottom to change pages
		Grid btnGrid = new Grid(1,3);
		Button nextBtn = new Button();
		Button prevBtn = new Button();
		
		ClickHandler handler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				// TODO: Fire event to tell if next or previous categories should be shown
				
			}
		};
		
		nextBtn.addClickHandler(handler);
		prevBtn.addClickHandler(handler);
		
		btnGrid.setWidget(0, 0, prevBtn);
		btnGrid.setWidget(0, 3, nextBtn);
		
		grid.setWidget(1, 0, btnGrid);

	}
	
	/**
	 * Populates the {@link CategoryPage} with the specified {@link Category}s.
	 * @param categories the categories to populate the page.
	 */
	public void populate(ArrayList<Category> categories){
		
		catList.removeAllRows();
		
		for(int i = 0; i<categories.size(); i++){
			catList.setWidget(i, 0, categories.get(i));
		}
		
	}

}
