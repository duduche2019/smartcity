package com.smartcity.smartcity.viewHTMLcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartcity.smartcity.DAO.CityDAO;
import com.smartcity.smartcity.DAO.PlotDAO;
import com.smartcity.smartcity.DAO.UserDAO;
import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.model.User;
import com.smartcity.smartcity.representation.CityPlotsRepresentation;
import com.smartcity.smartcity.representation.PlotRepresentation;
import com.smartcity.smartcity.service.CityService;

@Controller
@Transactional
@RequestMapping("/city/")
public class ViewCityHtmlController {

	@Autowired
	private CityDAO cityDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PlotDAO plotDAO;

	@Autowired
	CityService cityService;

	@GetMapping("createcity")
	public String showSignUpForm(City city) {
		return "CreateCity";
	}

	@PostMapping(value = "/addCity")
	public String addCity(@Valid City city, BindingResult result, Model model, HttpSession session) {

		if (result.hasErrors()) {
			return "CreateCity";
		}
		int userId = 0;
		if ((session.getAttribute("SESSION_USER_ID") != null)) {
			userId = (int) session.getAttribute("SESSION_USER_ID");
		}
		User user = userDAO.findOne(userId);
		city.setUser(user);
		city.setDistPerBox(22);
		cityService.createCityWithEmptyPlot(city);
		return "redirect:/city/showcities";
	}

	@GetMapping(value = "/showcities")
	public String showCities(Model model, HttpSession session) {
		int userId = 0;
		if ((session.getAttribute("SESSION_USER_ID") != null)) {
			userId = (int) session.getAttribute("SESSION_USER_ID");
		}
		List<City> cities = cityDAO.findAllByUserUserId(userId);
		model.addAttribute("cities", cities);
		return "MyCities";
	}

	@GetMapping("/showcity/{id}")
	public String showCity(Model model, @PathVariable int id) {
		CityPlotsRepresentation city = cityService.getCityPlotsRepresentationByCityId(id);
		List<PlotRepresentation> plotRepresentations = new ArrayList<>();
		plotRepresentations = city.getPlotRepresentation();
		List<PlotRepresentation> newPlotRepresentations = new ArrayList<>();
		for (int y = 0; y <= city.getHeight(); y++) {
			for (int x = 0; x <= city.getWidth(); x++) {
				for (PlotRepresentation plotRepresentation : plotRepresentations) {
					if (plotRepresentation.getPositionX() == x && plotRepresentation.getPositionY() == y) {
						newPlotRepresentations.add(plotRepresentation);
					}
				}
			}
		}
		city.setPlotRepresentation(newPlotRepresentations);
		model.addAttribute("city", city);
		return "ShowCity";
	}

	@GetMapping("/showcityhigthlight/{id}")
	public String showCityHigthLight(Model model, @PathVariable int id) {
		CityPlotsRepresentation city = cityService.getCityPlotsRepresentationByCityId(id);
		List<PlotRepresentation> plotRepresentations = new ArrayList<>();
		plotRepresentations = city.getPlotRepresentation();
		List<PlotRepresentation> newPlotRepresentations = new ArrayList<>();
		for (int y = 0; y <= city.getHeight(); y++) {
			for (int x = 0; x <= city.getWidth(); x++) {
				for (PlotRepresentation plotRepresentation : plotRepresentations) {
					if (plotRepresentation.getPositionX() == x && plotRepresentation.getPositionY() == y) {
						newPlotRepresentations.add(plotRepresentation);
					}
				}
			}
		}
		city.setPlotRepresentation(newPlotRepresentations);
		model.addAttribute("city", city);
		return "ShowCityHightlight";
	}

	@GetMapping(value = "/detailscity/{id}")
	public String produitdetail(Model model, @PathVariable int id) {
		City city = cityDAO.findOne(id);

		model.addAttribute("city", city);
		return "AfficheVille";
	}

	@GetMapping(value = "/deletecity/{id}")
	public String deleteCity(Model model, @PathVariable int id) {
		plotDAO.deleteAllByCityCityId(id);
		cityDAO.deleteByCityId(id);
		return "redirect:/city/showcities";
	}

}
