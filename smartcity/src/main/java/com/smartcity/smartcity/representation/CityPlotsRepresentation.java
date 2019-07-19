package com.smartcity.smartcity.representation;

import java.util.List;

public class CityPlotsRepresentation {
	private int cityId;

	private String cityName;

	private String cityAbrev;

	private int width;

	private int height;

	private float minimumValue;

	private float maximumValue;

	private String cityDescription;

	private int euclidDist;

	private int distPerBox;

	private int userId;

	private List<PlotRepresentation> plotRepresentation;

	public CityPlotsRepresentation() {
		super();
	}

	public CityPlotsRepresentation(int cityId, String cityName, String cityAbrev, int width, int height,
			float minimumValue, float maximumValue, String cityDescription, int euclidDist, int distPerBox, int userId,
			List<PlotRepresentation> plotRepresentation) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityAbrev = cityAbrev;
		this.width = width;
		this.height = height;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.cityDescription = cityDescription;
		this.euclidDist = euclidDist;
		this.distPerBox = distPerBox;
		this.userId = userId;
		this.plotRepresentation = plotRepresentation;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityAbrev() {
		return cityAbrev;
	}

	public void setCityAbrev(String cityAbrev) {
		this.cityAbrev = cityAbrev;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getMinimumValue() {
		return minimumValue;
	}

	public void setMinimumValue(float minimumValue) {
		this.minimumValue = minimumValue;
	}

	public float getMaximumValue() {
		return maximumValue;
	}

	public void setMaximumValue(float maximumValue) {
		this.maximumValue = maximumValue;
	}

	public String getCityDescription() {
		return cityDescription;
	}

	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}

	public int getEuclidDist() {
		return euclidDist;
	}

	public void setEuclidDist(int euclidDist) {
		this.euclidDist = euclidDist;
	}

	public int getDistPerBox() {
		return distPerBox;
	}

	public void setDistPerBox(int distPerBox) {
		this.distPerBox = distPerBox;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<PlotRepresentation> getPlotRepresentation() {
		return plotRepresentation;
	}

	public void setPlotRepresentation(List<PlotRepresentation> plotRepresentation) {
		this.plotRepresentation = plotRepresentation;
	}

}
