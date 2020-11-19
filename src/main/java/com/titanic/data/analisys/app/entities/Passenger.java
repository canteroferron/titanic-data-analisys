package com.titanic.data.analisys.app.entities;


/**
 * This interface represent the contract of one passenger
 * @author jcantero
 *
 */
public interface Passenger {

	
	/**
	 * 
	 * @return 
	 * 		The identifier
	 */
	int getId();
	
	/**
	 * 
	 * @return 
	 * 		Survived class
	 */
	short getSurvived();
	 
	/**
	 * 
	 * @return 
	 * 		Passenger class
	 */
	short getPclass();
	
	/**
	 * 
	 * @return
	 * 		Passenger gender	
	 */
	Gender getGender();	
}
