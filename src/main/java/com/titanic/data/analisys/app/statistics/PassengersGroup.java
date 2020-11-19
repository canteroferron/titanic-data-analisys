package com.titanic.data.analisys.app.statistics;

import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import com.titanic.data.analisys.app.entities.Passenger;

/**
 * This class represent a group of the passengers sorter by {@code PassengerSorter}
 * @author jcantero
 *
 */
public class PassengersGroup {
	
	/**
	 * Properties for save the statistical
	 */
	private final Properties properties = new Properties();
	
	/**
	 * Sorter of the group
	 */
	private final PassengersSorter sorter;
	
	/**
	 * {@code List} of the {@code Passenger} belong to group
	 */
	private final List<Passenger> passengers;

	/**
	 * 
	 * @param sorter
	 * 			Sorter of the group
	 * @param passengers
	 * 			{@code List} of the {@code Passenger} belong to group
	 */
	public PassengersGroup(PassengersSorter sorter, List<Passenger> passengers) {
		this.sorter = sorter;
		this.passengers = passengers;
	}
	
	/**
	 * 
	 * @return Sorter
	 */
	public PassengersSorter getSorter() {
		return sorter;
	}
	
	/**
	 * 
	 * @return {@code List} of the {@code Passenger} belong to group
	 */
	public List<Passenger> getPassengers() {
		return passengers;
	}
	
	
	/**
	 * 
	 * @param function
	 * 			{@code Function} interface to calculate one statistical
	 * 				The first parameter is the {@code List} of the {@code Passenger} belong to group
	 * 				The second parameter is a {@code PropertyBean}
	 */
	public void addStatisticValue(Function<List<Passenger>, PropertyBean> function) {
		PropertyBean bean = function.apply(passengers);
		properties.put(bean.getKey(), bean.getValue());
	}
	
	/**
	 * 
	 * @param key 
	 * 			Key of the statistical
	 * @return
	 * 		Value of the statistical. Return null if the key does not exist
	 */
	public Double getStatisticValueBy(String key) {
		return (Double) properties.get(key);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PassengersGroup [sorter=");
		builder.append(sorter);
		builder.append(", properties=");
		builder.append(properties);
		builder.append("]");
		return builder.toString();
	}
}
