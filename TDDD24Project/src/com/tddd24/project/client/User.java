package com.tddd24.project.client;

import java.util.Date;

public class User {

	int id;
	String name;
	int rank;
	int nrOfPosts;
	Date banned;
	Date registered;
	
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
	
	
}
