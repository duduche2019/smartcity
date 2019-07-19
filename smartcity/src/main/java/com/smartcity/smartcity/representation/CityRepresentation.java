package com.smartcity.smartcity.representation;

public class CityRepresentation {

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

	public CityRepresentation() {
		super();
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

}
