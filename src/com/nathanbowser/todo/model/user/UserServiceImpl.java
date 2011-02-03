package com.nathanbowser.todo.model.user;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nathanbowser.todo.model.candidate.TodoList;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao userDao;

	public User getCurrentUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof User) {
				user = (User) principal;
			}
		}

		return user;
	}

	@Transactional
	public void create(Signup signup) {
		User user = new User();
		user.setCreateDate(new Date());
		user.setEmail(signup.getEmail());
		user.setPassword(new ShaPasswordEncoder().encodePassword(signup.getPassword1(), null)); // no salt
		user.setName(signup.getName());
		user.setTodoList(new TodoList());
		userDao.save(user);
	}

	public boolean userExists(String email) {
		return userDao.findByEmail(email) != null;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
