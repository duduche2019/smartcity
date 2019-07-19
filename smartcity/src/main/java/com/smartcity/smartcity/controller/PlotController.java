package com.smartcity.smartcity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartcity.smartcity.DAO.PlotDAO;
import com.smartcity.smartcity.exception.CityNotFoundException;
import com.smartcity.smartcity.exception.PlotNotFoundException;
import com.smartcity.smartcity.model.Plot;
import com.smartcity.smartcity.representation.PlotRepresentation;
import com.smartcity.smartcity.service.CityService;
import com.smartcity.smartcity.service.PlotService;

@Transactional
@RestController
public class PlotController {

	@Autowired
	PlotDAO plotDAO;

	@Autowired
	CityService cityService;

	@Autowired
	PlotService plotService;

	/**
	 * Request a new plot creation.
	 * 
	 * @param plot
	 *            Plot informations for the new plot creation.
	 * @return URI of the city newly created, or 409 (Conflict) if Plot name already
	 *         exists.
	 */

	@PostMapping(value = "/createPlot")
	public Plot addPlot(@RequestBody Plot plot) {
		Plot createdPlot = plotDAO.save(plot);
		return createdPlot;
	}

	/**
	 * Update an existing plot.
	 * 
	 * @param plot
	 *            Plot informations to put in the actual plot.
	 * @return 200 if correctly updated, 404 if no such plot plotId found.
	 */
	@PutMapping(value = "/updatePlot")
	public Plot updatePlot(@RequestBody Plot plot) {
		Plot updatedPlot = plotDAO.save(plot);
		return updatedPlot;
	}

	/**
	 * Get a specific city by name.
	 * 
	 * @param name
	 *            name of the city wished.
	 * @return The city wished, or 404 if not found.
	 * @throws CityNotFoundException
	 */
	@GetMapping(value = "/getAllPlotsByCityId/{cityId}")
	public List<Plot> getCityByName(@PathVariable int cityId) throws CityNotFoundException {
		List<Plot> PlotsFound = plotDAO.findAllPlotByCityCityId(cityId);
		return PlotsFound;
	}

	/**
	 * Get a specific city by name.
	 * 
	 * @param name
	 *            name of the city wished.
	 * @return The city wished, or 404 if not found.
	 * @throws CityNotFoundException
	 */
	@GetMapping(value = "/getAllPlotsFinalValueByCityId/{cityId}")
	public void getAllPlotsFinalValueByCityId(@PathVariable int cityId) throws CityNotFoundException {
		 plotService.calculatePlotFinalValueByCityId(cityId);
	}

	/**
	 * Update an existing plot.
	 * 
	 * @param plot
	 *            Plot informations to put in the actual plot.
	 * @return 200 if correctly updated, 404 if no such plot plotId found.
	 */
	@PutMapping(value = "/updatePlotByPostions")
	public Plot updatePlotByPostionsAndCityId(@RequestBody Plot plot) {
		Plot updatedPlot = cityService.updateCityPlotTypeByPosition(plot);
		return updatedPlot;
	}

	/**
	 * Get a specific plot.
	 * 
	 * @param plotId
	 *            plotId of the plot wished.
	 * @return The plot wished, or 404 if not found.
	 */
	@GetMapping(value = "/getPlotById/{plotId}")
	public Plot getCityById(@PathVariable int plotId) {
		Plot cityFound = plotDAO.findOne(plotId);
		return cityFound;
	}

	/**
	 * Get a specific plot.
	 * 
	 * @param positionX
	 *            and positionY plotId of the plot wished.
	 * @return The plot wished, or 404 if not found.
	 */
	@GetMapping(value = "/getPlotByPostionAndCityId/{cityId}/{positionX}/{positionY}")
	public Plot getPlotByPostion(@PathVariable int positionX, @PathVariable int positionY, @PathVariable int cityId) {
		Plot plotFound = plotDAO.getPlotByCityCityIdAndPositionXAndPositionY( cityId,positionX, positionY);
		return plotFound;
	}

	/**
	 * Get a specific plot by name.
	 * 
	 * @param name
	 *            name of the plot wished.
	 * @return The plot wished, or 404 if not found.
	 */
	@GetMapping(value = "/getPlotByName/{name}")
	public Plot getPlotByName(@PathVariable String name) {
		Plot plotFound = plotDAO.getPlotByPlotName(name);
		return plotFound;
	}

	/**
	 * Get all plots.
	 * 
	 * @param
	 * 
	 * @return All plots, or 404 if not found.
	 */
	@GetMapping(value = "/getAllPlots")
	public List<Plot> getAllPlots() {
		List<Plot> plotsFound = plotDAO.findAll();
		return plotsFound;
	}

	/**
	 * Delete a specific plot.
	 * 
	 * @param plotId
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteByPlotId/{plotId}")
	public void deleteCityById(@PathVariable int plotId) {
		plotDAO.deleteByPlotId(plotId);
	}

	/**
	 * Delete a all plots.
	 * 
	 * @param
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllPlots")
	public void deleteAllPlots() {
		plotDAO.deleteAll();
	}
	
	
	/**
	 * Get a specific plotRepresentation.
	 * 
	 * @param  plot of the plot wished.
	 * @return The plot wished, or 404 if not found.
	 */
	@GetMapping(value = "/getPlotRepresentationById/{plotId}")
	public PlotRepresentation getPlotRepresentationById(@PathVariable int plotId) throws PlotNotFoundException {
		PlotRepresentation plotRepresentation = plotService.getPlotRepresentationByPlotId(plotId);
		return plotRepresentation;
	}
	
	

}
