package com.smartcity.smartcity.viewHTMLcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartcity.smartcity.DAO.UserDAO;
import com.smartcity.smartcity.model.User;
import com.smartcity.smartcity.security.Decrypter;

@Controller
public class SessionController {
	@Autowired
	UserDAO userDAO;

	@Autowired
	Decrypter decrypter;

	@GetMapping("/accueil")
	public String process(Model model, HttpServletRequest request, HttpSession session) {
		int userId = 0;
		String fristName = "";
		String lastName = "";
		String userAccess = "";

		if ((session.getAttribute("SESSION_USER_ID") == null)) {
			userId = 0;
			request.getSession().setAttribute("SESSION_USER_ID", userId);
		}
		if ((session.getAttribute("SESSION_USER_FRISTNAME") == null)) {
			fristName = "";
			request.getSession().setAttribute("SESSION_USER_FRISTNAME", fristName);
		}
		if ((session.getAttribute("SESSION_USER_LASTNAME") == null)) {
			lastName = "";
			request.getSession().setAttribute("SESSION_USER_LASTNAME", lastName);
		}
		if ((session.getAttribute("SESSION_USER_ACCESS") == null)) {
			userAccess = "";
			request.getSession().setAttribute("SESSION_USER_ACCESS", userAccess);
		}

		return "Index";
	}

	@PostMapping("/usersession")
	public String persistMessage(User user, HttpServletRequest request) {

		User userFound = userDAO.getUserByLogin(user.getLogin());
		if (userFound == null) {
			return "redirect:/user/login";

		} else if (user.getPassword() == decrypter.decrypt(userFound.getPassword())) {
			System.out.println(user.getPassword());
			System.out.println(decrypter.decrypt(userFound.getPassword()));
			return "redirect:/user/login";
		}
		int userId = userFound.getUserId();
		String fristName = userFound.getFirstName();
		String lastName = userFound.getLastName();
		String userAccess = userFound.getAccess().toString();
		request.getSession().setAttribute("SESSION_USER_ID", userId);
		request.getSession().setAttribute("SESSION_USER_FRISTNAME", fristName.toUpperCase());
		request.getSession().setAttribute("SESSION_USER_LASTNAME", lastName.toUpperCase());
		request.getSession().setAttribute("SESSION_USER_ACCESS", userAccess);
		return "redirect:/accueil";

	}

	@GetMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/accueil";
	}
}