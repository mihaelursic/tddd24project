package com.tddd24.project.client;

import java.util.Date;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;

public class Post extends DecoratorPanel{
	
	int postId;
	User user;
	String content;
	Date timeCreated;
	Date lastUpdated;	
	
	Grid mainGrid;
	Grid topBar;
	Grid userBar;
	
	public Post(){
		super();
	}

	public Post(int postId, User user, String content, Date timeCreated, Date lastUpdated) {
		super();
		
		// Create grids
		mainGrid = new Grid(2,2);
		topBar = new Grid(1,3);
		userBar = new Grid(4,2);
		
		// Instantiate
		this.postId = postId;
		this.user = user;
		this.content = content;
		this.timeCreated = timeCreated;
		this.lastUpdated = lastUpdated;
		
		// Create components and fill topBar
		Button reportBtn = new Button("Report");
		Button qouteBtn = new Button("Qoute");
		
		topBar.setWidget(0, 0, new Label("#"+this.postId));
		topBar.setWidget(0, 1, reportBtn);
		topBar.setWidget(0, 2, qouteBtn);
		
		// Create components and fill userBar
		userBar.setWidget(0, 0, new Label(user.getName()));
		userBar.setWidget(1, 0, new Label(""+user.getRank())); //TODO: Fixa ranksystem (enum?)
		userBar.setWidget(2, 0, new Label(user.getNrOfPosts()+" posts"));
		userBar.setWidget(3, 0, new Label("Registered: "+user.getRegistered().toString()));
		
		// Assemble mainGrid
		Label timeLabel = new Label(timeCreated.toString());
		mainGrid.setWidget(0, 0, timeLabel);
		mainGrid.setWidget(0, 1, topBar);
		mainGrid.setWidget(1, 0, userBar);
		mainGrid.setWidget(1, 1, new InlineHTML("<html>" + content + "<br>Last edited " + lastUpdated + "</html>"));
		
		// Add everything to post decorate grid
		this.setWidth("1000px");
		this.setWidget(mainGrid);
	}
	
}
