package com.nativejava.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PostRepository postRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User add(User user) {
		User createdUser = userRepo.save(user);
		return createdUser;
	}

	public User getUserById(int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent())
			return user.get();
		else
			return null;
	}

	public void deleteUserById(int userId) {
		try {
			userRepo.deleteById(userId);
		} catch (DataAccessException ex) {
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " :: " + userId);
		}
	}

	public List<Post> findAllPosts(int userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			return user.get().getPosts();
		} else {
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " :: " + userId);
		}
	}

	public Post findPost(int userId, int postId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			List<Post> posts = user.get().getPosts();
			for (Post post : posts) {
				if (post.getPostId() == postId) {
					return post;
				}
			}
		} else {
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " :: " + userId);
		}
		return null;
	}

	public void addPost(Post post, int userId) {
		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {
			User userEntity = user.get();
			post.setUser(userEntity);
			postRepo.save(post);
		} else {
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " :: " + userId);
		}
	}

}
