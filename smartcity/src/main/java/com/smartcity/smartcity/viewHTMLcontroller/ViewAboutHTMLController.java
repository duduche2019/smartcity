package com.smartcity.smartcity.viewHTMLcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewAboutHTMLController {
	
		@GetMapping("about")
		public String showSignUpForm() {
			return "About";
		}
	}



