package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;

public class Thread extends Composite {

	public static int CURRENTLY_SHOWN_TOPIC = -1;

	private static Thread _instance = null;
	private Grid grid;
	private FlexTable postList;
	final RichTextArea textArea = new RichTextArea();
	DecoratorPanel panel = new DecoratorPanel();

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

		//			// Create Buttons on the bottom to change pages
		//			Grid btnGrid = new Grid(1,3);
		//			Button nextBtn = new Button();
		//			Button prevBtn = new Button();
		//
		//			ClickHandler handler = new ClickHandler() {
		//
		//				@Override
		//				public void onClick(ClickEvent event) {
		//
		//					// TODO: Fire event to tell if next or previous posts should be shown
		//
		//				}
		//			};
		//
		//			nextBtn.addClickHandler(handler);
		//			prevBtn.addClickHandler(handler);

		//			btnGrid.setWidget(0, 0, prevBtn);
		//			btnGrid.setWidget(0, 2, nextBtn);

		//			grid.setWidget(1, 0, btnGrid);

		// Create a texteditor for new posts

		Grid editorGrid = new Grid(3,2);
		textArea.ensureDebugId("cwRichText-area");
		textArea.setSize("100%", "14em");
		RichTextToolbar toolbar = new RichTextToolbar(textArea);
		toolbar.ensureDebugId("cwRichText-toolbar");
		toolbar.setWidth("100%");
		editorGrid.setWidget(0, 0, toolbar);
		editorGrid.setWidget(1, 0, textArea);

		// Create buttons for posting
		Button postBtn = new Button("Post");
		//Button saveBtn = new Button(); // TODO: Ska vi ha det???
		Button cancelBtn = new Button("Cancel");
		Button updateBtn = new Button("Update");

		// Add listeners to buttons
		postBtn.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				postOnServer(textArea.getHTML());		
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

		panel.setWidget(editorGrid);

		grid.setWidget(1, 0, panel);

	}

	protected void postOnServer(String html) {


		// Initialize the service proxy.
		if (TDDD24ProjectSvc == null) {
			TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
		}

		// Set up the callback object.
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Boolean result) {
				textArea.setText("");
				getInstance();
				
			}


		};

		TDDD24ProjectSvc.addPostInThread(html, CURRENTLY_SHOWN_TOPIC, TDDD24Project.USER_ID, callback);

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
