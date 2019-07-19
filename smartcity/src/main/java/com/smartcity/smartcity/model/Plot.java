package com.smartcity.smartcity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLOT")
public class Plot {

	@Id
	@GeneratedValue
	private int plotId;

	@Column(name = "PLOTNAME", nullable = false, unique = true)
	private String plotName;
	private String color;
	private int positionX;
	private int positionY;
	@Column(name = "INITIALVALUE", nullable = false)
	private float initialValue;

	@Column(name = "FINALVALUE", nullable = false)
	private float finalValue;

	@ManyToOne
	@JoinColumn(name = "city_Id", nullable = false)
	private City city;

	@ManyToOne
	@JoinColumn(name = "plot_Type_Id", nullable = false)
	private PlotType plotType;

	public Plot() {
		super();
	}

	public Plot(String plotName, String color, int positionX, int positionY, float initialValue, float finalValue,
			City city, PlotType plotType) {
		super();
		this.plotName = plotName;
		this.color = color;
		this.positionX = positionX;
		this.positionY = positionY;
		this.initialValue = initialValue;
		this.finalValue = finalValue;
		this.city = city;
		this.plotType = plotType;
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

	public PlotType getPlotType() {
		return plotType;
	}

	public void setPlotType(PlotType plotType) {
		this.plotType = plotType;
	}

	public int getPlotId() {
		return plotId;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + Float.floatToIntBits(finalValue);
		result = prime * result + Float.floatToIntBits(initialValue);
		result = prime * result + plotId;
		result = prime * result + ((plotName == null) ? 0 : plotName.hashCode());
		result = prime * result + ((plotType == null) ? 0 : plotType.hashCode());
		result = prime * result + positionX;
		result = prime * result + positionY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plot other = (Plot) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Float.floatToIntBits(finalValue) != Float.floatToIntBits(other.finalValue))
			return false;
		if (Float.floatToIntBits(initialValue) != Float.floatToIntBits(other.initialValue))
			return false;
		if (plotId != other.plotId)
			return false;
		if (plotName == null) {
			if (other.plotName != null)
				return false;
		} else if (!plotName.equals(other.plotName))
			return false;
		if (plotType == null) {
			if (other.plotType != null)
				return false;
		} else if (!plotType.equals(other.plotType))
			return false;
		if (positionX != other.positionX)
			return false;
		if (positionY != other.positionY)
			return false;
		return true;
	}

}
