package com.smartcity.smartcity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CITY")
public class City {

	@Id
	@GeneratedValue
	private int cityId;

	@NotBlank(message = " Veuillez renseigner le champ nom")
	@Column(name = "CITYNAME", nullable = false, unique = true)
	private String cityName;

	@Size(min = 3, max = 3)
	@Column(name = "CITYABREVATION", nullable = false, unique = true)
	private String cityAbrev;

	// @NotBlank(message = " Veuillez renseigner le champ longueur")
	// @NotEmpty(message= "salary may not be empty")
	// @Size(min = 4, max = 12)
	private int width;

	// @NotBlank(message = " Veuillez renseigner le champ hauteur")
	// @NotEmpty(message= "salary may not be empty")
	// @Size(min = 4, max = 12)
	private int height;

	// @NotNull(message = " Veuillez renseigner le champ valeur minimum")
	// @NotEmpty(message = " Veuillez renseigner le champ valeur minimum")
	// @DecimalMin("0.00")
	// @DecimalMax("50000.00")
	// @Size(min = 0, max = 50000)
	private float minimumValue;

	// @NotBlank(message = " Veuillez renseigner le champ valeur maximum")
	// @NotEmpty(message = " Veuillez renseigner le champ valeur minimum")
	// @DecimalMin("0.00")
	// @DecimalMax("50000.00")
	// @Size(min = 0, max = 50000)
	private float maximumValue;

	@Size(max = 355)
	@Column(length = 355)
	private String cityDescription;

	@Column(name = "EUCLIDEANDISTANCE")
	private int euclidDist;

	@Column(name = "DISTANCEPERBOX")
	private int distPerBox;

	@ManyToOne
	@JoinColumn(name = "user_Id", nullable = false)
	// @NotNull(message = ErrorCodes._USER_CITY)
	private User user;

	public City() {
		super();
	}

	public City(String cityName, String cityAbrev, int width, int height, float minimumValue, float maximumValue,
			String cityDescription, int euclidDist, int distPerBox, User user) {
		super();
		this.cityName = cityName;
		this.cityAbrev = cityAbrev;
		this.width = width;
		this.height = height;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.cityDescription = cityDescription;
		this.euclidDist = euclidDist;
		this.distPerBox = distPerBox;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCityId() {
		return cityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityAbrev == null) ? 0 : cityAbrev.hashCode());
		result = prime * result + ((cityDescription == null) ? 0 : cityDescription.hashCode());
		result = prime * result + cityId;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + distPerBox;
		result = prime * result + euclidDist;
		result = prime * result + height;
		result = prime * result + Float.floatToIntBits(maximumValue);
		result = prime * result + Float.floatToIntBits(minimumValue);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + width;
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
		City other = (City) obj;
		if (cityAbrev == null) {
			if (other.cityAbrev != null)
				return false;
		} else if (!cityAbrev.equals(other.cityAbrev))
			return false;
		if (cityDescription == null) {
			if (other.cityDescription != null)
				return false;
		} else if (!cityDescription.equals(other.cityDescription))
			return false;
		if (cityId != other.cityId)
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (distPerBox != other.distPerBox)
			return false;
		if (euclidDist != other.euclidDist)
			return false;
		if (height != other.height)
			return false;
		if (Float.floatToIntBits(maximumValue) != Float.floatToIntBits(other.maximumValue))
			return false;
		if (Float.floatToIntBits(minimumValue) != Float.floatToIntBits(other.minimumValue))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}
