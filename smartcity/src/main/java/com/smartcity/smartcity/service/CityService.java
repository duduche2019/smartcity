package com.smartcity.smartcity.service;

import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.model.Plot;
import com.smartcity.smartcity.representation.CityPlotsRepresentation;
import com.smartcity.smartcity.representation.CityRepresentation;

public interface CityService {
	City createCityWithEmptyPlot(City city);

	CityRepresentation getCityRepresentationByCityId(int cityId);

	float calculateInitialPlotValue(float minimumValue, float maximumValue);

	Plot updateCityPlotTypeByPosition(Plot plot);

	CityPlotsRepresentation getCityPlotsRepresentationByCityId(int cityId);

	void calculateInitialPlotValueForCityPlots(int cityId);

}