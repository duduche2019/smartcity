package com.smartcity.smartcity.viewHTMLcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcity.smartcity.DAO.UserDAO;
import com.smartcity.smartcity.model.User;
import com.smartcity.smartcity.model.enums.Access;

@Controller
@RequestMapping("/user/")
public class ViewUserHtmlController {

	@Autowired
	UserDAO userDAO;
	
	@GetMapping("signup")
	public String showSignUpForm(User user) {
		return "CreateUser";
	}
	

	@GetMapping("login")
	public String showLoginForm(User user) {
		return "Login";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "CreateUser";
		}
		user.setAccess(Access.USER);
		userDAO.save(user);
		return "Login";

	}

}
