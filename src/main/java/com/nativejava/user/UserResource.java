package com.nativejava.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	UserDAOService service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = service.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " : " + userId);
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		if (user.getName() == null || user.getDob() == null) {
			throw new UserInputNotValidException(UserConstants.USER_INPUT_NOT_VALID);
		}
		User addedUser = service.add(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{userId}/posts")
	public List<Post> getAllUserPosts(@PathVariable int userId) {
		return service.findAllPosts(userId);
	}
	
	@GetMapping("/users/{userId}/posts/{postId}")
	public Post getUserPost(@PathVariable int userId, @PathVariable int postId) {
		Post post = service.findPost(userId, postId);
		if(post == null) {
			throw new PostNotFoundException(UserConstants.POST_NOT_FOUND + " : " + postId);
		}
		return post;
	}
	
	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Object> createUserPost(@RequestBody Post post, @PathVariable int userId) {
		if (post.getPostMessage() == null) {
			throw new PostInputNotValidException(UserConstants.POST_NOT_VALID);
		}
		post.setUserId(userId);
		Post savedPost = service.addPost(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getPostId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	

}
