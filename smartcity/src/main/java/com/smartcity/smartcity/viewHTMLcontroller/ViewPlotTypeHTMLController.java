package com.smartcity.smartcity.viewHTMLcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcity.smartcity.DAO.PlotTypeDAO;
import com.smartcity.smartcity.model.PlotType;
import com.smartcity.smartcity.model.enums.InfluenceLevel;

@Controller
@Transactional
@RequestMapping("/plottype/")
public class ViewPlotTypeHTMLController {
	
	@Autowired
	PlotTypeDAO plotypeDAO;
	
	
	@GetMapping("createplotype")
	public String showCreateType(PlotType plottype) {
		return "CreatePlotType";
	}
	
	@RequestMapping(value = "/addPlotype", method = RequestMethod.POST)
	public String addPlotype(@Valid PlotType plotype, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "CreatePlotType";
		}
		plotype.setInfluenceLevel(InfluenceLevel.EUCLIDIEN);
		plotypeDAO.save(plotype);
		return "redirect:/city/showcities";
	}
	

}
