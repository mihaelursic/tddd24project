package com.tddd24.project.client;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
	
	int postId;
	User user;
	String content;
	Date timeCreated;
	Date lastUpdated;	

	private Post(){
		
	}
	
	public Post(int postId, User user, String content, Date timeCreated, Date lastUpdated) {
		
		// Instantiate
		this.postId = postId;
		this.user = user;
		this.content = content;
		this.timeCreated = timeCreated;
		this.lastUpdated = lastUpdated;
		
		
	}

	/**
	 * @return the postId
	 */
	public int getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the timeCreated
	 */
	public Date getTimeCreated() {
		return timeCreated;
	}

	/**
	 * @param timeCreated the timeCreated to set
	 */
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}
