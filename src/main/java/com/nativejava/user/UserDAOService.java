package com.nativejava.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> users = new ArrayList<User>();
	private static int userCount = 3;
	
	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "Jim", new Date()));
		users.add(new User(3, "Jolly", new Date()));
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
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}

}
