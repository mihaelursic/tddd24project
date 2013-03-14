package com.tddd24.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
@RemoteServiceRelativePath("TopicPage")
public class TopicPage extends Composite {

	private static TopicPage _instance = null;
	private Grid grid;
	private FlexTable topicList;
	private static TDDD24ProjectServiceAsync TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);

	/**
	 * Creates a new (empty) {@link TopicPage} with the specified {@link Topic}s.
	 * To populate the page with other <code>Topic</code>s, use method {@link #populate()}.
	 * @param topics {@link Topic} to populate the page.
	 * @see Topic
	 */
	private TopicPage(ArrayList<Topic> topics){

		this();
		populate(topics);

	}

	/**
	 * Creates a new (empty) {@link TopicPage}. 
	 * To populate the page, use method {@link #populate()}.
	 * @see Topic
	 */
	private TopicPage(){

		// Make this TopicPage to a Grid with 2 rows and 1 column
		//		super(2,1);
		grid = new Grid(2,1);
		initWidget(grid);

		topicList = new FlexTable();
		grid.setWidget(0, 0, topicList);

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
		btnGrid.setWidget(0, 2, nextBtn);

		grid.setWidget(1, 0, btnGrid);

	}

	public static TopicPage getInstance(){
		if(_instance == null){
			_instance = new TopicPage();
		}

		// Initialize the service proxy.
		if (TDDD24ProjectSvc == null) {
			TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
		}

		// Set up the callback object.
		AsyncCallback<ArrayList<Topic>> callback = new AsyncCallback<ArrayList<Topic>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Topic> result) {
				_instance.populate(result);
			}

		};

		TDDD24ProjectSvc.getAllTopics(callback);

		return _instance;
	}

	/**
	 * Populates the {@link TopicPage} with the specified {@link Topic}s.
	 * @param topics the topics to populate the page.
	 */
	public void populate(ArrayList<Topic> topics){

		topicList.removeAllRows();

		for(int i = 0; i<topics.size(); i++){
			
			final Topic topic = topics.get(i);
			
			// Create grid
			Grid mainGrid = new Grid(1,2);
			
			// Create and add labels for info
			Button subjectLabel = new Button(topic.getSubject());

			subjectLabel.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Thread.CURRENTLY_SHOWN_TOPIC = topic.getTopicId();
					History.newItem("Thread");
				}
			});

			mainGrid.setWidget(0, 0, subjectLabel);
			
			Grid topicInfoGrid = new Grid(2,1);
			topicInfoGrid.setWidget(0, 0, new Label("Created by: "+topic.getUserName()));
			topicInfoGrid.setWidget(1, 0, new Label("Nr of posts in thread: "+topic.getNrOfposts()));
			
			mainGrid.setWidget(0, 1, topicInfoGrid);
						
			topicList.setWidget(i, 0, mainGrid);
			topicList.setWidget(i, 1, new Label("#"+topic.getTopicId()));
		}

	}

}
