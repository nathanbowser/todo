package com.nathanbowser.todo.web.signup;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nathanbowser.todo.model.user.Signup;
import com.nathanbowser.todo.model.user.UserService;

@Controller
@RequestMapping("/signup.html")
public class SignupController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "signupValidator")
	private Validator validator;

	@ModelAttribute("formbean")
	public Signup signup() {
		return new Signup();
	}

	@RequestMapping(method = RequestMethod.GET)
	public void view() {
		// no-op
	}

	@RequestMapping(method = RequestMethod.POST, params = "signup")
	public String save(@ModelAttribute("formbean") Signup signup, BindingResult result) {
		validator.validate(signup, result);
		if (result.hasErrors()) {
			return null;
		}

		userService.create(signup);
		return "redirect:login.html";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "cancel")
	public String cancel() {
		return "login";
	}

}
