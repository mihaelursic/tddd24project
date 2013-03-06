package com.tddd24.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RichTextArea;

public class TextEditor extends DecoratorPanel {

	public TextEditor(){
		
	super();
		
	Grid editorGrid = new Grid(3,2);
	final RichTextArea textArea = new RichTextArea();
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
			Window.alert("TODO!\n getHTML(): "+ textArea.getHTML());		
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
	
	this.setWidget(editorGrid);
	}
}
