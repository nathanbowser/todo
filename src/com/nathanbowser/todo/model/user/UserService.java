package com.nathanbowser.todo.model.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User getCurrentUser();

	void create(Signup signup);

	boolean userExists(String email);

}
