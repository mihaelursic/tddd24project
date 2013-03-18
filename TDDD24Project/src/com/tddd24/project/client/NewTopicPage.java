package com.tddd24.project.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewTopicPage extends Composite {

	private static NewTopicPage _instance = null;
	private static TDDD24ProjectServiceAsync TDDD24ProjectSvc = GWT.create(TDDD24ProjectService.class);
	final RichTextArea textArea = new RichTextArea();
	final TextBox subjectBox = new TextBox();


	private NewTopicPage(){
		
		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
		
		final Label subject = new Label("Subject:");

		panel.add(subject);
		panel.add(subjectBox);

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
		Button postBtn = new Button("Create topic");
		//Button saveBtn = new Button(); // TODO: Ska vi ha det???
		Button cancelBtn = new Button("Cancel");

		// Add listeners to buttons
		postBtn.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				createTopic(subjectBox.getText(), textArea.getText(), TDDD24Project.USER_ID);
			}
		});
		cancelBtn.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				textArea.setText("");
				subjectBox.setText("");
			}
		});


		// Create button grid and add to editorGrid
		Grid btnGrid = new Grid(1,3);
		btnGrid.setWidget(0, 0, cancelBtn);
		btnGrid.setWidget(0, 2, postBtn);
		editorGrid.setWidget(2, 0, btnGrid);

		panel.add(editorGrid);

	}

	public static NewTopicPage getInstance() {
		if(_instance == null){
			_instance = new NewTopicPage();
		}
		return _instance;
		
	}

	private void createTopic(String subject, String content, int userId) {

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
				subjectBox.setText("");
				History.newItem("TopicPage");

			}


		};

		TDDD24ProjectSvc.addTopic(subject, content, userId, callback);

	}




}
