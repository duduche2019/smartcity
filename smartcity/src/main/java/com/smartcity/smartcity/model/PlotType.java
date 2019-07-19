package com.smartcity.smartcity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smartcity.smartcity.model.enums.Category;
import com.smartcity.smartcity.model.enums.InfluenceLevel;

@Entity
@Table(name = "PLOTTYPE")
public class PlotType {

	@Id
	@GeneratedValue
	private int plotTypeId;

	@Column(name = "PLOTTYPENAME", nullable = false, unique = true)
	private String plotTypeName;

	@Column(name = "PLOTTYPEABREVATION", nullable = false, unique = true)
	private String plotTypeAbrev;

	private Category category;

	private InfluenceLevel influenceLevel;

	private int negativeInfluence;

	private int positiveInfluence;

	private int negDistInfluence;

	private int posDistInfluence;

	private String color;

	private String plotTypeDescription;

	private boolean possibleValue;

	public PlotType() {

	}

	public PlotType(String plotTypeName, String plotTypeAbrev, Category category, InfluenceLevel influenceLevel,
			int negativeInfluence, int positiveInfluence, int negDistInfluence, int posDistInfluence, String color,
			String plotTypeDescription, boolean possibleValue) {
		super();
		this.plotTypeName = plotTypeName;
		this.plotTypeAbrev = plotTypeAbrev;
		this.category = category;
		this.influenceLevel = influenceLevel;
		this.negativeInfluence = negativeInfluence;
		this.positiveInfluence = positiveInfluence;
		this.negDistInfluence = negDistInfluence;
		this.posDistInfluence = posDistInfluence;
		this.color = color;
		this.plotTypeDescription = plotTypeDescription;
		this.possibleValue = possibleValue;
	}

	public String getPlotTypeName() {
		return plotTypeName;
	}

	public void setPlotTypeName(String plotTypeName) {
		this.plotTypeName = plotTypeName;
	}

	public String getPlotTypeAbrev() {
		return plotTypeAbrev;
	}

	public void setPlotTypeAbrev(String plotTypeAbrev) {
		this.plotTypeAbrev = plotTypeAbrev;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public InfluenceLevel getInfluenceLevel() {
		return influenceLevel;
	}

	public void setInfluenceLevel(InfluenceLevel influenceLevel) {
		this.influenceLevel = influenceLevel;
	}

	public int getNegativeInfluence() {
		return negativeInfluence;
	}

	public void setNegativeInfluence(int negativeInfluence) {
		this.negativeInfluence = negativeInfluence;
	}

	public int getPositiveInfluence() {
		return positiveInfluence;
	}

	public void setPositiveInfluence(int positiveInfluence) {
		this.positiveInfluence = positiveInfluence;
	}

	public int getNegDistInfluence() {
		return negDistInfluence;
	}

	public void setNegDistInfluence(int negDistInfluence) {
		this.negDistInfluence = negDistInfluence;
	}

	public int getPosDistInfluence() {
		return posDistInfluence;
	}

	public void setPosDistInfluence(int posDistInfluence) {
		this.posDistInfluence = posDistInfluence;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlotTypeDescription() {
		return plotTypeDescription;
	}

	public void setPlotTypeDescription(String plotTypeDescription) {
		this.plotTypeDescription = plotTypeDescription;
	}

	public boolean isPossibleValue() {
		return possibleValue;
	}

	public void setPossibleValue(boolean possibleValue) {
		this.possibleValue = possibleValue;
	}

	public int getPlotTypeId() {
		return plotTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((influenceLevel == null) ? 0 : influenceLevel.hashCode());
		result = prime * result + negDistInfluence;
		result = prime * result + negativeInfluence;
		result = prime * result + ((plotTypeAbrev == null) ? 0 : plotTypeAbrev.hashCode());
		result = prime * result + ((plotTypeDescription == null) ? 0 : plotTypeDescription.hashCode());
		result = prime * result + plotTypeId;
		result = prime * result + ((plotTypeName == null) ? 0 : plotTypeName.hashCode());
		result = prime * result + posDistInfluence;
		result = prime * result + positiveInfluence;
		result = prime * result + (possibleValue ? 1231 : 1237);
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
		PlotType other = (PlotType) obj;
		if (category != other.category)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (influenceLevel != other.influenceLevel)
			return false;
		if (negDistInfluence != other.negDistInfluence)
			return false;
		if (negativeInfluence != other.negativeInfluence)
			return false;
		if (plotTypeAbrev == null) {
			if (other.plotTypeAbrev != null)
				return false;
		} else if (!plotTypeAbrev.equals(other.plotTypeAbrev))
			return false;
		if (plotTypeDescription == null) {
			if (other.plotTypeDescription != null)
				return false;
		} else if (!plotTypeDescription.equals(other.plotTypeDescription))
			return false;
		if (plotTypeId != other.plotTypeId)
			return false;
		if (plotTypeName == null) {
			if (other.plotTypeName != null)
				return false;
		} else if (!plotTypeName.equals(other.plotTypeName))
			return false;
		if (posDistInfluence != other.posDistInfluence)
			return false;
		if (positiveInfluence != other.positiveInfluence)
			return false;
		if (possibleValue != other.possibleValue)
			return false;
		return true;
	}

}
