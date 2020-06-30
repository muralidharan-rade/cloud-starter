package com.nativejava.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nativejava.user.util.UserResourceUtil;

@RestController
public class UserResource {

	@Autowired
	UserDAOService service;

	@Autowired
	UserResourceUtil util;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{userId}")
	public EntityModel<User> getUser(@PathVariable int userId) {
		User user = service.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " : " + userId);

		// hateoas

		EntityModel<User> userEntity = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUserPosts(userId));
		userEntity.add(linkTo.withRel("user-posts"));

		return userEntity;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User addedUser = service.add(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{userId}")
	public User deleteUser(@PathVariable int userId) {
		User user = service.deleteUserById(userId);
		if (user == null)
			throw new UserNotFoundException(UserConstants.USER_NOT_FOUND + " : " + userId);
		return user;
	}

	@GetMapping("/users/{userId}/posts")
	public MappingJacksonValue getAllUserPosts(@PathVariable int userId) {
		List<Post> posts = service.findAllPosts(userId);

		Set<String> filterSet = new HashSet<String>(Arrays.asList("postId", "postMessage", "userId"));
		return util.getPostBeanMapping(posts, filterSet);
	}

	@GetMapping("/users/{userId}/posts/{postId}")
	public MappingJacksonValue getUserPost(@PathVariable int userId, @PathVariable int postId) {
		Post post = service.findPost(userId, postId);

		if (post == null) {
			throw new PostNotFoundException(UserConstants.POST_NOT_FOUND + " : " + postId);
		}

		Set<String> filterSet = new HashSet<String>(Arrays.asList("postId", "postMessage"));
		return util.getPostBeanMapping(post, filterSet);
	}

	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Object> createUserPost(@Valid @RequestBody Post post, @PathVariable int userId) {
		post.setUserId(userId);
		Post savedPost = service.addPost(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getPostId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
