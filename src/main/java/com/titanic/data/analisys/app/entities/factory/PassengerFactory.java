package com.titanic.data.analisys.app.entities.factory;

import java.util.HashMap;
import java.util.Map;

import com.titanic.data.analisys.app.entities.FemalePassenger;
import com.titanic.data.analisys.app.entities.Gender;
import com.titanic.data.analisys.app.entities.MalePassenger;
import com.titanic.data.analisys.app.entities.Passenger;

/**
 * This class implement a factory for create object {@code Passenger} from array of {@code String}
 * @author jcantero
 *
 */
public final class PassengerFactory {

	/**
	 * Constant "female" as it appears in the file CSV
	 */
	private final static String FAMELE = "female";
	
	/**
	 * Map of {@code PassengerSupplier} for create new {@code Passenger}
	 * The key of the map is the kind of gender
	 * The value is a supplier for build the object {@code Passenger} 
	 */
	private final static Map<Gender, PassengerSupplier<String[], Passenger>> map = new HashMap<>();
	
	static {
		map.put(
				Gender.FEMALE, 
				line -> new FemalePassenger(Integer.valueOf(line[0]), Short.valueOf(line[1]), Short.valueOf(line[2])));
		
		map.put(
				Gender.MALE, 
				line -> new MalePassenger(Integer.valueOf(line[0]), Short.valueOf(line[1]), Short.valueOf(line[2])));
	}
	
	/**
	 * Hide constructor
	 */
	private PassengerFactory() {
	}
	
	/**
	 * 
	 * @param line 
	 * 			Array of the {@code String} that represent the data of the one passenger
	 * @return
	 * 		{@code Passenger} with its data populated
	 */
	public static Passenger build(String[] line) {		
		PassengerSupplier<String[], Passenger> supplier = FAMELE.equals(line[4]) ? map.get(Gender.FEMALE) : map.get(Gender.MALE);
		return supplier.get(line);
	}
	
}
	