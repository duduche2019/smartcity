package com.smartcity.smartcity.viewHTMLcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewServicesHTMLController {
	
	@GetMapping("services")
	public String showSignUpForm() {
		return "Services";
	}

}
