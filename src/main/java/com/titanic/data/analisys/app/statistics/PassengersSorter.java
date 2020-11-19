package com.titanic.data.analisys.app.statistics;

import java.util.Objects;

import com.titanic.data.analisys.app.entities.Gender;

/**
 * This class represent a object sorter by gender and passenger class
 * @author jcantero
 *
 */
public class PassengersSorter {

	/**
	 * Passenger gender
	 */
	private final Gender gender;
	
	/**
	 * Passenger class
	 */
	private final Short pclass;


	/**
	 * 
	 * @param gender
	 * 			Passenger gender
	 * @param pclass
	 * 			Passenger class
	 */
	public PassengersSorter(Gender gender, Short pclass) {
		super();
		this.gender = gender;
		this.pclass = pclass;
	}
	
	/**
	 * 
	 * @return Passenger gender
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * 
	 * @return Passenger class
	 */
	public Short getPclass() {
		return pclass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gender, pclass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassengersSorter other = (PassengersSorter) obj;
		return gender == other.gender && pclass == other.pclass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PassengersClasificator [gender=");
		builder.append(gender);
		builder.append(", pclass=");
		builder.append(pclass);
		builder.append("]");
		return builder.toString();
	}
}
