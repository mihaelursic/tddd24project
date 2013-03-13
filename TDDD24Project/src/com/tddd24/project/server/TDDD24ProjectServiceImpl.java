package com.tddd24.project.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tddd24.project.client.TDDD24ProjectService;
import com.tddd24.project.client.Topic;

public class TDDD24ProjectServiceImpl extends RemoteServiceServlet implements TDDD24ProjectService {

	@Override
	public ArrayList<Topic> getAllTopics(){
		
//		ArrayList<Topic> hej = DataManager.getAllTopics();
//		Topic topic = hej.get(0);
//		
//		System.out.println("Topic: "+topic.getSubject());
		
		return DataManager.getAllTopics();
	}
	
}
