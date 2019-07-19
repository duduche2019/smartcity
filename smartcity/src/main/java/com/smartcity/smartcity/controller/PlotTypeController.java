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

import com.smartcity.smartcity.DAO.PlotTypeDAO;
import com.smartcity.smartcity.model.PlotType;

@Transactional
@RestController
public class PlotTypeController {
	@Autowired
	PlotTypeDAO plotTypeDAO;

	/**
	 * Request a new PlotType creation.
	 * 
	 * @param plotType
	 *            PlotType informations for the new plotType creation.
	 * @return URI of the plotType newly created, or 409 (Conflict) if PlotType name
	 *         already exists.
	 */
	@PostMapping(value = "/createPlotType")
	public PlotType addPlotType(@RequestBody PlotType plotType) {
		PlotType createdPlotType = plotTypeDAO.save(plotType);
		return createdPlotType;
	}

	/**
	 * Update an existing PlotType.
	 * 
	 * @param plotType
	 *            PlotType informations to put in the actual plotType.
	 * @return 200 if correctly updated, 404 if no such plotType plotTypeId found.
	 */
	@PutMapping(value = "/updatePlotType")
	public PlotType updatePlotType(@RequestBody PlotType plotType) {
		PlotType updatedPlotType = plotTypeDAO.save(plotType);
		return updatedPlotType;
	}

	/**
	 * Get a specific PlotType.
	 * 
	 * @param plotTypeId
	 *            plotTypeId of the plotType wished.
	 * @return The plotType wished, or 404 if not found.
	 */
	@GetMapping(value = "/getPlotTypeById/{plotTypeId}")
	public PlotType getPlotTypeById(@PathVariable int plotTypeId) {
		PlotType plotTypeFound = plotTypeDAO.findOne(plotTypeId);
		return plotTypeFound;
	}

	/**
	 * Get a specific plotType by name.
	 * 
	 * @param name
	 *            name of the PlotType wished.
	 * @return The plotType wished, or 404 if not found.
	 */
	@GetMapping(value = "/getPlotTypeByName/{plotTypeName}")
	public PlotType getPlotTypeByName(@PathVariable String plotTypeName) {
		PlotType plotTypeFound = plotTypeDAO.getPlotTypeByPlotTypeName(plotTypeName);
		return plotTypeFound;
	}

	/**
	 * Get all plotType.
	 * 
	 * @param
	 * 
	 * @return All plotType, or 404 if not found.
	 */
	@GetMapping(value = "/getAllPlotType")
	public List<PlotType> getAllPlotType() {
		List<PlotType> plotTypesFound = plotTypeDAO.findAll();
		return plotTypesFound;
	}

	/**
	 * Delete a specific plotType.
	 * 
	 * @param plotTypeId
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deletePlotTypeById/{plotTypeId}")
	public void deletePlotTypeById(@PathVariable int plotTypeId) {
		plotTypeDAO.deleteByPlotTypeId(plotTypeId);
	}

	/**
	 * Delete a all PlotType.
	 * 
	 * @param
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllPlotType")
	public void deleteAllPlotType() {
		plotTypeDAO.deleteAll();
	}

}
