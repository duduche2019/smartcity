package com.smartcity.smartcity.viewHTMLcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcity.smartcity.DAO.ContactDAO;
import com.smartcity.smartcity.model.Contact;

@Controller
@RequestMapping("/contact/")
public class ViewContactHtmlController {

	@Autowired
	ContactDAO contactDAO;

	@GetMapping("pagecontact")
	public String showSignUpForm(Contact contact) {
		return "CreateContact";
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String addContact(@Valid Contact contact, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "CreateContact";
		}

		contactDAO.save(contact);
		
		return "ConfirmContact";

	}
}