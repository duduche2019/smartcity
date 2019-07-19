package com.smartcity.smartcity.assembler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.representation.CityPlotsRepresentation;
import com.smartcity.smartcity.representation.PlotRepresentation;
import com.smartcity.smartcity.service.PlotService;

@Component
public class CityPlotsAssembler implements Assembler<City, CityPlotsRepresentation> {

	@Autowired
	PlotService plotService;

	@Override
	public void mergeEntityWithrepresentation(City city, CityPlotsRepresentation cityPlotsRepresentation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CityPlotsRepresentation createRepresentation(City city) {
		CityPlotsRepresentation cityPlotsRepresentation = new CityPlotsRepresentation();

		if (city.getCityId() != 0) {
			cityPlotsRepresentation.setCityId(city.getCityId());
		}

		if (city.getCityName() != null) {
			cityPlotsRepresentation.setCityName(city.getCityName());
		}

		if (city.getCityAbrev() != null) {
			cityPlotsRepresentation.setCityAbrev(city.getCityAbrev());
		}
		if (city.getCityDescription() != null) {
			cityPlotsRepresentation.setCityDescription(city.getCityDescription());
		}

		if (city.getDistPerBox() != 0) {
			cityPlotsRepresentation.setDistPerBox(city.getDistPerBox());
		}

		if (city.getEuclidDist() != 0) {
			cityPlotsRepresentation.setEuclidDist(city.getEuclidDist());
		}

		if (city.getHeight() != 0) {
			cityPlotsRepresentation.setHeight(city.getHeight());
		}
		
		if (city.getWidth() != 0) {
			cityPlotsRepresentation.setWidth(city.getWidth());
		}

		if (city.getMaximumValue() != 0) {
			cityPlotsRepresentation.setMaximumValue(city.getMaximumValue());
		}

		if (city.getMinimumValue() != 0) {
			cityPlotsRepresentation.setMinimumValue(city.getMinimumValue());
		}

		if (city.getUser() != null) {
			cityPlotsRepresentation.setUserId(city.getUser().getUserId());
		}

		List<PlotRepresentation> plotRepresentationFound = plotService
				.getAllPlotRepresentationByCityId(city.getCityId());

		if (plotRepresentationFound != null) {
			cityPlotsRepresentation.setPlotRepresentation(plotRepresentationFound);
		}

		return cityPlotsRepresentation;
	}

}
