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

import com.smartcity.smartcity.DAO.CityDAO;
import com.smartcity.smartcity.DAO.PlotDAO;
import com.smartcity.smartcity.exception.CityNotFoundException;
import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.representation.CityPlotsRepresentation;
import com.smartcity.smartcity.representation.CityRepresentation;
import com.smartcity.smartcity.service.CityService;

@Transactional
@RestController
public class CityController {

	@Autowired
	CityDAO cityDAO;

	@Autowired
	PlotDAO plotDAO;

	@Autowired
	CityService cityService;

	/**
	 * Request a new city creation.
	 * 
	 * @param city City informations for the new city creation.
	 * @return URI of the city newly created, or 409 (Conflict) if City name already
	 *         exists.
	 */
	@PostMapping(value = "/createCity")
	public City addCity(@RequestBody City city) {
		City createdCity = cityService.createCityWithEmptyPlot(city);
		return createdCity;
	}

	/**
	 * Update an existing city.
	 * 
	 * @param city City informations to put in the actual city.
	 * @return 200 if correctly updated, 404 if no such city cityId found.
	 */
	@PutMapping(value = "/updateCity")
	public City updateCity(@RequestBody City city) {
		City updatedCity = cityDAO.save(city);
		return updatedCity;
	}
	
	/**
	 * Update an existing city.
	 * 
	 * @param city City informations to put in the actual city.
	 * @return 200 if correctly updated, 404 if no such city cityId found.
	 */
	/*@PutMapping(value = "/updateCity")
	public City updatePlotTypeForCity(@RequestBody int  cityId, int positionX, int positionY,Category catogory ) {
		
		City updatedCity = cityDAO.save(city);
		return updatedCity;
	}*/

	/**
	 * Get a specific city.
	 * 
	 * @param cityId cityId of the city wished.
	 * @return The city wished, or 404 if not found.
	 */
	@GetMapping(value = "/getCityById/{cityId}")
	public City getCityById(@PathVariable int cityId) {
		City cityFound = cityDAO.findOne(cityId);
		return cityFound;
	}
	
	@GetMapping(value = "/getCityInitialValue/{cityId}")
	public void calculateInitialPlotValueForCityPlotTest(@PathVariable int cityId) {
		cityService.calculateInitialPlotValueForCityPlots(cityId);
	
	}


	/**
	 * Get a specific cityRepresentation.
	 * 
	 * @param cityId cityId of the city wished.
	 * @return The city wished, or 404 if not found.
	 */
	@GetMapping(value = "/getCityRepresentationById/{cityId}")
	public CityRepresentation getCityRepresentationById(@PathVariable int cityId) throws CityNotFoundException {
		CityRepresentation cityRepresentation = cityService.getCityRepresentationByCityId(cityId);
		return cityRepresentation;
	}

	/**
	 * Get a specific city by name.
	 * 
	 * @param name name of the city wished.
	 * @return The city wished, or 404 if not found.
	 * @throws CityNotFoundException 
	 */
	@GetMapping(value = "/getCityByName/{name}")
	public City getCityByName(@PathVariable String name) throws CityNotFoundException{
		City cityFound = cityDAO.getCityByCityName(name);
		 if (cityFound == null) {
	           throw new CityNotFoundException();
	       }
		return cityFound;
	}
	

	/**
	 * Get all cities.
	 * 
	 * @param
	 * 
	 * @return All cities, or 404 if not found.
	 */
	@GetMapping(value = "/getAllCities")
	public List<City> getAllCities() {
		List<City> citiesFound = cityDAO.findAllByOrderByCityNameAsc();
		return citiesFound;
	}

	/**
	 * Delete a specific city and all related plots.
	 * 
	 * @param cityId
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteByCityId/{cityId}")
	public void deleteCityById(@PathVariable int cityId) {
		plotDAO.deleteAllByCityCityId(cityId);
		cityDAO.deleteByCityId(cityId);
	}

	/**
	 * Delete a all cities.
	 * 
	 * @param
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllCities")
	public void deleteAllCities() {
		cityDAO.deleteAll();
	}
	
	/**
	 * Get a specific city with all related plotRepresentation.
	 * 
	 * @param  plot of the plot wished.
	 * @return The plot wished, or 404 if not found.
	 */
	@GetMapping(value = "/getCityPlotsRepresentationByCityId/{cityId}")
	public CityPlotsRepresentation getCityPlotsRepresentationById(@PathVariable int cityId) throws CityNotFoundException {
		CityPlotsRepresentation cityPlotsRepresentation = cityService.getCityPlotsRepresentationByCityId(cityId);
		return cityPlotsRepresentation;
	}
}
