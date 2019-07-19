package com.smartcity.smartcity.viewHTMLcontroller;

import java.util.ArrayList;
import java.util.List;

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

import com.smartcity.smartcity.DAO.PlotDAO;
import com.smartcity.smartcity.DAO.PlotTypeDAO;
import com.smartcity.smartcity.model.Plot;
import com.smartcity.smartcity.model.PlotType;
import com.smartcity.smartcity.model.enums.Category;
import com.smartcity.smartcity.service.CityService;
import com.smartcity.smartcity.service.PlotService;

@Controller
@Transactional
@RequestMapping("/plot/")
public class ViewPlotHtmlController {

	@Autowired
	private PlotDAO plotDAO;

	@Autowired
	private PlotTypeDAO plotTypeDAO;

	@Autowired
	private PlotService plotService;

	@Autowired
	private CityService cityService;

	@GetMapping(value = "/showplot/{id}")
	public String plotdetail(Model model, PlotType plotType, @PathVariable int id) {
		Plot plot = plotDAO.findOne(id);
		List<PlotType> plotTypeList = new ArrayList<>();
		plotTypeList = plotTypeDAO.findAll();
		model.addAttribute("plotTypeList", plotTypeList);
		model.addAttribute("plot", plot);
		return "AffichePlot";
	}

	@PostMapping(value = "/changeplottype/{cityId}/{plotId}")
	public String changePlotPlotType(Model model, @PathVariable int cityId, @PathVariable int plotId,
			@Valid PlotType plotType, BindingResult result) {
		Plot plot = plotDAO.findOne(plotId);
		PlotType plotTypeFound = plotTypeDAO.findOne(plotType.getPlotTypeId());
		plot.setPlotType(plotTypeFound);
		plotDAO.save(plot);
		plotService.calculateEuclidDistance(cityId);
		boolean emptyFound = true;
		List<Plot> PlotsFound = plotDAO.findAllPlotByCityCityId(cityId);
		for (Plot pt : PlotsFound) {
			if (pt.getPlotType().getCategory() == Category.EMPTY) {
				emptyFound = false;
			}
		}
		if (emptyFound == true) {
			cityService.calculateInitialPlotValueForCityPlots(cityId);
			plotService.calculatePlotFinalValueByCityId(cityId);
			plotService.plotChangeColor(cityId);
		}
		return "redirect:/city/showcity/{cityId}";
	}

}
