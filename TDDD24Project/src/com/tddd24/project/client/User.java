package com.tddd24.project.client;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	int id;
	String name;
	int rank;
	int nrOfPosts;
	Date banned;
	Date registered;
	
	public User(){
		
	}
	
	public User(int id, String name, int rank, int nrOfPost, Date banned, Date registered){
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.nrOfPosts = nrOfPost;
		this.banned = banned;
		this.registered = registered;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @return the nrOfPosts
	 */
	public int getNrOfPosts() {
		return nrOfPosts;
	}

	/**
	 * @return the banned
	 */
	public Date getBanned() {
		return banned;
	}

	/**
	 * @return the registered
	 */
	public Date getRegistered() {
		return registered;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * @param nrOfPosts the nrOfPosts to set
	 */
	public void setNrOfPosts(int nrOfPosts) {
		this.nrOfPosts = nrOfPosts;
	}

	/**
	 * @param banned the banned to set
	 */
	public void setBanned(Date banned) {
		this.banned = banned;
	}

	/**
	 * @param registered the registered to set
	 */
	public void setRegistered(Date registered) {
		this.registered = registered;
	}
	
	
}
