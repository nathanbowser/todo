package com.nathanbowser.todo.web.signup;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nathanbowser.todo.model.user.Signup;
import com.nathanbowser.todo.model.user.UserService;

@Component("signupValidator")
public class SignupValidator implements Validator {

	private static final int MIN_PASSWORD = 6; // TODO Drive from config

	@Resource(name = "userService")
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Signup.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Signup signup = (Signup) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "err.generic.required"); // TODO validate actual email
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "err.generic.required");

		if (userService.userExists(signup.getEmail())) {
			errors.rejectValue("email", "err.signup.exists");
		}

		String password = signup.getPassword1();
		if (!StringUtils.hasText(password) || password.length() < MIN_PASSWORD) {
			errors.rejectValue("password1", "err.signup.password.invalid");
		} else if (!password.equalsIgnoreCase(signup.getPassword2())) {
			errors.rejectValue("password2", "err.signup.password.dontmatch");
		}

	}

}
