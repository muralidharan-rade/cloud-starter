package com.nativejava.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	@Autowired
	UserRepository userRepo;

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
		return userRepo.findAll();
	}

	public User add(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public User getUserById(int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent())
			return user.get();
		else
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

	public User deleteUserById(int userId) {
		Iterator<User> i = users.iterator();
		while (i.hasNext()) {
			User user = i.next();
			if (user.getId() == userId) {
				i.remove();
				return user;
			}
		}
		return null;

	}

}
