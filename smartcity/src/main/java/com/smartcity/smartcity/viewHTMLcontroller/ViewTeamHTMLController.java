package com.smartcity.smartcity.viewHTMLcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTeamHTMLController {
	
	@GetMapping("team")
	public String showSignUpForm() {
		return "Team";
	}

}
