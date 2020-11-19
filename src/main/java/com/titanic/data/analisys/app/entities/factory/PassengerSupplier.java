package com.titanic.data.analisys.app.entities.factory;

import com.titanic.data.analisys.app.entities.Passenger;


/**
 * This functional interface represent a supplier for create new instances of the {@code passenger} 
 * 
 * @author jcantero
 *
 * @param <D> Object with the data of the passenger
 * @param <R> {@code Passenger} 
 */
@FunctionalInterface
public interface PassengerSupplier<D, R extends Passenger> {
	
	R get(D data);
}
