package com.smartcity.smartcity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartcity.smartcity.DAO.CityDAO;
import com.smartcity.smartcity.DAO.PlotDAO;
import com.smartcity.smartcity.DAO.PlotTypeDAO;
import com.smartcity.smartcity.assembler.CityAssembler;
import com.smartcity.smartcity.assembler.CityPlotsAssembler;
import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.model.Plot;
import com.smartcity.smartcity.model.PlotType;
import com.smartcity.smartcity.model.enums.Category;
import com.smartcity.smartcity.representation.CityPlotsRepresentation;
import com.smartcity.smartcity.representation.CityRepresentation;

@Service
public class DefaultCityService implements CityService {
	@Autowired
	CityDAO cityDAO;

	@Autowired
	PlotDAO plotDAO;

	@Autowired
	PlotTypeDAO plotTypeDAO;

	@Autowired
	CityAssembler cityAssembler;

	@Autowired
	CityPlotsAssembler cityPlotsAssembler;

	@Transactional
	public City createCityWithEmptyPlot(City city) {
		City createdCity = cityDAO.save(city);
		PlotType pt = new PlotType();
		PlotType plotTypeFound = plotTypeDAO.getPlotTypeByCategory(Category.EMPTY);

		if (plotTypeFound == null) {
			pt.setCategory(Category.EMPTY);
			pt.setPlotTypeName("EMPTY");
			pt.setPlotTypeAbrev("EMP");
			pt.setPossibleValue(false);
			plotTypeDAO.save(pt);
		}

		for (int i = 0; i < createdCity.getWidth(); i++) {
			for (int j = 0; j < createdCity.getHeight(); j++) {
				Plot p = new Plot();
				p.setPlotName(createdCity.getCityAbrev() + " -" + i + "-" + j);
				p.setPositionX(i);
				p.setPositionY(j);
				p.setInitialValue(0);
				p.setColor("#f2f2f2");
				p.setCity(city);

				if (plotTypeFound == null) {
					p.setPlotType(pt);
				} else {
					p.setPlotType(plotTypeFound);
				}

				plotDAO.save(p);
			}
		}
		return createdCity;
	}

	/**
	 * Get a cityRepresentation by siteId.
	 * 
	 * @param siteId.
	 * @return The cityRepresentation of the city, or 404 if not found.
	 */
	@Transactional
	@Override
	public CityRepresentation getCityRepresentationByCityId(int cityId) {

		City cityFound = cityDAO.findOne(cityId);

		CityRepresentation cityRepresentation = cityAssembler.createRepresentation(cityFound);

		return cityRepresentation;
	}

	@Override
	public float calculateInitialPlotValue(float minimumValue, float maximumValue) {
		float initialValue = 0;
		initialValue = ((minimumValue + maximumValue) / 2);
		return initialValue;
	}

	@Transactional
	@Override
	public Plot updateCityPlotTypeByPosition(Plot plot) {
		float initialValue = 0F;

		Plot updatedPlot = null;

		Plot plotFound = plotDAO.getPlotByCityCityIdAndPositionXAndPositionY(plot.getCity().getCityId(),
				plot.getPositionX(), plot.getPositionY());
		if (plotFound != null) {
			if (plot.getPlotType().isPossibleValue() == true) {
				initialValue = calculateInitialPlotValue(plot.getCity().getMinimumValue(),
						plot.getCity().getMaximumValue());
				plot.setInitialValue(initialValue);
				updatedPlot = plotDAO.save(plot);
			}
		}
		return updatedPlot;
	}

	@Transactional
	@Override
	public void calculateInitialPlotValueForCityPlots(int cityId) {
		List<Plot> PlotsFound = plotDAO.findAllPlotByCityCityId(cityId);
		float initialValue = 0F;
			for (Plot pt : PlotsFound) {
				if (pt.getPlotType().isPossibleValue() == true) {
				initialValue = calculateInitialPlotValue(pt.getCity().getMinimumValue(),
							pt.getCity().getMaximumValue());
					pt.setInitialValue(initialValue);
					plotDAO.save(pt);
				}
			}
	}

	@Override
	public CityPlotsRepresentation getCityPlotsRepresentationByCityId(int cityId) {
		City cityFound = cityDAO.findOne(cityId);

		CityPlotsRepresentation cityPlotsRepresentation = cityPlotsAssembler.createRepresentation(cityFound);

		return cityPlotsRepresentation;

	}

}
