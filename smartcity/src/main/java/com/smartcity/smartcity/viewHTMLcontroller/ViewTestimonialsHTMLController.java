package com.smartcity.smartcity.viewHTMLcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestimonialsHTMLController {
	
	@GetMapping("temoignages")
	public String showSignUpForm() {
		return "Testimonials";
	}

}
