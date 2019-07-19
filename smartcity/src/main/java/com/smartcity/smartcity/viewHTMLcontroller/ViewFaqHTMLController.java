package com.smartcity.smartcity.viewHTMLcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewFaqHTMLController {
	
	@GetMapping("faq")
	public String showSignUpForm() {
		return "Faq";
	}

}
