package com.nativejava.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

// @JsonFilter(value = "PostFilter")
@Entity
public class Post {

	@Id
	@GeneratedValue
	private int postId;

	@NotNull
	@Size(min = 5, message = "Post size should be more than 5")
	private String postMessage;

	// static filtering
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post() {

	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postMessage=" + postMessage + ", user=" + user + "]";
	}

}
