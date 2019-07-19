package com.smartcity.smartcity.assembler;

import org.springframework.stereotype.Component;

import com.smartcity.smartcity.model.Plot;
import com.smartcity.smartcity.representation.PlotRepresentation;

@Component
public class PlotAssembler implements Assembler<Plot, PlotRepresentation> {

	@Override
	public void mergeEntityWithrepresentation(Plot plot, PlotRepresentation plotRepresentation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PlotRepresentation createRepresentation(Plot plot) {

		PlotRepresentation plotRepresentation = new PlotRepresentation();

		if (plot.getPlotId() != 0) {
			plotRepresentation.setPlotId(plot.getPlotId());
		}

		if (plot.getPlotName() != null) {
			plotRepresentation.setPlotName(plot.getPlotName());
		}

		if (plot.getColor() != null) {
			plotRepresentation.setColor(plot.getColor());
		}

		if (plot.getPositionX() != 0) {
			plotRepresentation.setPositionX(plot.getPositionX());
		}

		if (plot.getPositionY() != 0) {
			plotRepresentation.setPositionY(plot.getPositionY());
		}

		if (plot.getInitialValue() != 0) {
			plotRepresentation.setInitialValue(plot.getInitialValue());
		}

		if (plot.getFinalValue() != 0) {
			plotRepresentation.setFinalValue(plot.getFinalValue());
		}

		if (plot.getCity().getCityId() != 0) {
			plotRepresentation.setCityId(plot.getCity().getCityId());
		}

		if (plot.getPlotType() != null) {
			plotRepresentation.setPlotType(plot.getPlotType());
		}

		return plotRepresentation;
	}

}
