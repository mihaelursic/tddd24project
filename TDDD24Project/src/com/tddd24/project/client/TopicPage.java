package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;

public class TopicPage extends Grid {

	private FlexTable topicList;

	/**
	 * Creates a new (empty) {@link TopicPage} with the specified {@link Topic}s.
	 * To populate the page with other <code>Topic</code>s, use method {@link #populate()}.
	 * @param topics {@link Topic} to populate the page.
	 * @see Topic
	 */
	public TopicPage(ArrayList<Topic> topics){

		this();
		populate(topics);
		
	}

	/**
	 * Creates a new (empty) {@link TopicPage}. 
	 * To populate the page, use method {@link #populate()}.
	 * @see Topic
	 */
	public TopicPage(){

		// Make this TopicPage to a Grid with 2 rows and 1 column
		super(2,1);

		topicList = new FlexTable();
		this.setWidget(0, 0, topicList);
		
		// Create Buttons on the bottom to change pages
		Grid btnGrid = new Grid(1,3);
		Button nextBtn = new Button();
		Button prevBtn = new Button();
		
		ClickHandler handler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				// TODO: Fire event to tell if next or previous topics should be shown
				
			}
		};
		
		nextBtn.addClickHandler(handler);
		prevBtn.addClickHandler(handler);
		
		btnGrid.setWidget(0, 0, prevBtn);
		btnGrid.setWidget(0, 3, nextBtn);
		
		this.setWidget(1, 0, btnGrid);

	}
	
	/**
	 * Populates the {@link TopicPage} with the specified {@link Topic}s.
	 * @param topics the topics to populate the page.
	 */
	public void populate(ArrayList<Topic> topics){
		
		topicList.removeAllRows();
		
		for(int i = 0; i<topics.size(); i++){
			topicList.setWidget(i, 0, topics.get(i));
		}
		
	}
	
}
