package com.tddd24.project.client;

import java.io.Serializable;

public class Topic implements Serializable{

	int topicId;
	String subject;
	String userName;
	int nrOfposts;
	String categoryName;

	private Topic(){
		
	}
	
	public Topic(int topicId, String subject, String userName, int nrOfposts,
			String categoryName) {
		super();
		this.topicId = topicId;
		this.subject = subject;
		this.userName = userName;
		this.nrOfposts = nrOfposts;
		this.categoryName = categoryName;

	}

	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the nrOfposts
	 */
	public int getNrOfposts() {
		return nrOfposts;
	}

	/**
	 * @param nrOfposts the nrOfposts to set
	 */
	public void setNrOfposts(int nrOfposts) {
		this.nrOfposts = nrOfposts;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
