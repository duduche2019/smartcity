package com.smartcity.smartcity.representation;

import com.smartcity.smartcity.model.PlotType;

public class PlotRepresentation {

	private int plotId;

	private String plotName;
	private String color;
	private int positionX;
	private int positionY;

	private float initialValue;

	private float finalValue;

	private int cityId;
	private PlotType plotType;

	public PlotRepresentation() {
		super();
	}

	public int getPlotId() {
		return plotId;
	}

	public void setPlotId(int plotId) {
		this.plotId = plotId;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public float getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(float initialValue) {
		this.initialValue = initialValue;
	}

	public float getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(float finalValue) {
		this.finalValue = finalValue;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public PlotType getPlotType() {
		return plotType;
	}

	public void setPlotType(PlotType plotType) {
		this.plotType = plotType;
	}

}
