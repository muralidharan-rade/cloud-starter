package com.nativejava.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter(value = "PostFilter")
public class Post {

	private int postId;

	@NotNull
	@Size(min = 5, message = "Post size should be more than 5")
	private String postMessage;

	// static filtering
	// @JsonIgnore
	private int userId;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Post(int postId, String postMessage, int userId) {
		super();
		this.postId = postId;
		this.postMessage = postMessage;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", postMessage=" + postMessage + ", userId=" + userId + "]";
	}

}
