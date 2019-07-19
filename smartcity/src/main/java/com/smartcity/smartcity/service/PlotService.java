package com.smartcity.smartcity.service;

import java.util.List;

import com.smartcity.smartcity.algorithme.CityCase;
import com.smartcity.smartcity.representation.PlotRepresentation;

public interface PlotService {
	void calculatePlotFinalValueByCityId(int cityId);

	PlotRepresentation getPlotRepresentationByPlotId(int plotId);

	List<PlotRepresentation> getAllPlotRepresentationByCityId(int cityId);

	List<CityCase> modeleParCasesDepuisRTB(int cityId);

	float[][] PercentageInfluenceParCases(List<CityCase> villeCaseList, int cityId);
	
	void calculateEuclidDistance(int cityId);

	void plotChangeColor(int cityId);
}
