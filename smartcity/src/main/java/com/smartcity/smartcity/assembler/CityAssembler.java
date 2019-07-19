package com.smartcity.smartcity.assembler;

import org.springframework.stereotype.Component;

import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.representation.CityRepresentation;


@Component
public class CityAssembler implements Assembler<City, CityRepresentation> {

	@Override
	public void mergeEntityWithrepresentation(City city, CityRepresentation cityrepresentation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CityRepresentation createRepresentation(City city) {
		CityRepresentation cityRepresentation = new CityRepresentation();

		if (city.getCityId() != 0) {
			cityRepresentation.setCityId(city.getCityId());
		}

		if (city.getCityName() != null) {
			cityRepresentation.setCityName(city.getCityName());
		}

		if (city.getCityAbrev() != null) {
			cityRepresentation.setCityAbrev(city.getCityAbrev());
		}
		if (city.getCityDescription() != null) {
			cityRepresentation.setCityDescription(city.getCityDescription());
		}

		if (city.getDistPerBox() != 0) {
			cityRepresentation.setDistPerBox(city.getDistPerBox());
		}

		if (city.getEuclidDist() != 0) {
			cityRepresentation.setEuclidDist(city.getEuclidDist());
		}

		if (city.getHeight() != 0) {
			cityRepresentation.setHeight(city.getHeight());
		}
		
		if (city.getWidth() != 0) {
			cityRepresentation.setWidth(city.getWidth());
		}

		if (city.getMaximumValue() != 0) {
			cityRepresentation.setMaximumValue(city.getMaximumValue());
		}

		if (city.getMinimumValue() != 0) {
			cityRepresentation.setMinimumValue(city.getMinimumValue());
		}

		if (city.getUser() != null) {
			cityRepresentation.setUserId(city.getUser().getUserId());
		}

		return cityRepresentation;
	}

}
