package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;

public class Thread extends Composite {
	
		public static int CURRENTLY_SHOWN_TOPIC = -1;

		private static Thread _instance = null;
		private Grid grid;
		private FlexTable postList;
		private static TDDD24ProjectServiceAsync TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);


		private Thread(ArrayList<Post> posts){

			this();
			populate(posts);

		}


		private Thread(){

			grid = new Grid(2,1);
			initWidget(grid);

			postList = new FlexTable();
			grid.setWidget(0, 0, postList);

			// Create Buttons on the bottom to change pages
			Grid btnGrid = new Grid(1,3);
			Button nextBtn = new Button();
			Button prevBtn = new Button();

			ClickHandler handler = new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// TODO: Fire event to tell if next or previous posts should be shown

				}
			};

			nextBtn.addClickHandler(handler);
			prevBtn.addClickHandler(handler);

			btnGrid.setWidget(0, 0, prevBtn);
			btnGrid.setWidget(0, 2, nextBtn);

			grid.setWidget(1, 0, btnGrid);

		}

		public static Thread getInstance(){
			if(_instance == null){
				_instance = new Thread();
			}

			// Initialize the service proxy.
			if (TDDD24ProjectSvc == null) {
				TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
			}

			// Set up the callback object.
			AsyncCallback<ArrayList<Post>> callback = new AsyncCallback<ArrayList<Post>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(ArrayList<Post> result) {
					_instance.populate(result);
				}

			};

			TDDD24ProjectSvc.getPostsFromTopic(Thread.CURRENTLY_SHOWN_TOPIC, callback);

			return _instance;
		}


		public void populate(ArrayList<Post> posts){

			postList.removeAllRows();

			for(int i = 0; i<posts.size(); i++){
				
				Post post = posts.get(i);
				User user = post.getUser();
				
				// Create grids
				Grid mainGrid = new Grid(2,2);
				Grid topBar = new Grid(1,3);
				Grid userBar = new Grid(4,2);
				
				// Create components and fill topBar
				Button reportBtn = new Button("Report");
				Button qouteBtn = new Button("Qoute");
				
				topBar.setWidget(0, 0, new Label("#"+post.getPostId()));
				topBar.setWidget(0, 1, reportBtn);
				topBar.setWidget(0, 2, qouteBtn);
				
				// Create components and fill userBar
				userBar.setWidget(0, 0, new Label(user.getName()));
				userBar.setWidget(1, 0, new Label(""+user.getRank())); //TODO: Fixa ranksystem (enum?)
				userBar.setWidget(2, 0, new Label(user.getNrOfPosts()+" posts"));
				userBar.setWidget(3, 0, new Label("Registered: "+user.getRegistered().toString()));
				
				// Assemble mainGrid
				Label timeLabel = new Label(post.getTimeCreated().toString());
				mainGrid.setWidget(0, 0, timeLabel);
				mainGrid.setWidget(0, 1, topBar);
				mainGrid.setWidget(1, 0, userBar);
				mainGrid.setWidget(1, 1, new InlineHTML("<html>" + post.getContent() + 
						"<br>Last edited " + post.getLastUpdated().toString() + "</html>"));
				
							
				postList.setWidget(i, 0, mainGrid);
			}

		}

	

	
}
