package com.nativejava.user;

public class Post {

	private int postId;
	private String postMessage;
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
