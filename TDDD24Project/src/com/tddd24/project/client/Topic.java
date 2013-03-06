package com.tddd24.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public class Topic extends DecoratorPanel {

	int topicId;
	String subject;
	String userName;
	int nrOfposts;
	String categoryName;

	Grid mainGrid;

	public Topic(int topicId, String subject, String userName, int nrOfposts,
			String categoryName) {
		super();
		this.topicId = topicId;
		this.subject = subject;
		this.userName = userName;
		this.nrOfposts = nrOfposts;
		this.categoryName = categoryName;

		// Create grid
		mainGrid = new Grid(1,2);
		
		// Create and add labels for info
		Label subjectLabel = new Label(subject);

		subjectLabel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// TODO: Fire event to open the thread page?
				
			}
		});

		mainGrid.setWidget(0, 0, subjectLabel);
		
		Grid topicInfoGrid = new Grid(2,1);
		topicInfoGrid.setWidget(0, 0, new Label("Created by: "+userName));
		topicInfoGrid.setWidget(1, 0, new Label("Nr of posts in thread: "+nrOfposts));
		
		mainGrid.setWidget(0, 1, topicInfoGrid);

	}



}
