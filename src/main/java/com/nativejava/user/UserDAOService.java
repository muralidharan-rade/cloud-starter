package com.nativejava.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<User>();
	private static List<Post> posts = new ArrayList<Post>();
	private static int userCount = 3;
	private static int postCount = 103;

	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "Jim", new Date()));
		users.add(new User(3, "Jolly", new Date()));

		posts.add(new Post(100, "twitter", 1));
		posts.add(new Post(101, "facebook", 2));
		posts.add(new Post(102, "instagram", 3));
	}

	public List<User> findAll() {
		return users;
	}

	public User add(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public User getUserById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public List<Post> findAllPosts(int userId) {
		List<Post> userPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getUserId() == userId) {
				userPosts.add(post);
			}
		}
		return userPosts;
	}

	public Post findPost(int userId, int postId) {
		Post userPost = null;

		for (Post post : posts) {
			if (post.getPostId() == postId && post.getUserId() == userId) {
				userPost = post;
			}
		}
		return userPost;
	}
	
	public Post addPost(Post post) {
		post.setPostId(++postCount);
		posts.add(post);
		return post;
	}

}
