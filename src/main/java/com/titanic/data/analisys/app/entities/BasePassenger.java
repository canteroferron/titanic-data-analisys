package com.titanic.data.analisys.app.entities;


/**
 * This class is a pojo that represent the data of the one passenger
 * @author jcantero
 *
 */
public abstract class BasePassenger implements Passenger {

	/** 
	 * Identifier 
	 */
	private final int id;
	
	/** 
	 * Survived flag
	 *   0 -> Passenger did not survive
	 *   1 -> Passenger survived 
	 */  
	private final short survived;
	
	/**
	 * passenger class
	 * 	1 -> First class
	 *  2 -> Second class
	 *  3 -> Third class
	 */
	private final short pclass;
	

	/**
	 * 
	 * @param id
	 * 			Identifier
	 * @param survived
	 * 			Survived flag
	 * @param pclass
	 * 			Passenger class
	 */
	public BasePassenger(int id, short survived, short pclass) {
		this.id = id;
		this.survived = survived;
		this.pclass = pclass;		
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public short getSurvived() {
		return survived;
	}

	@Override
	public short getPclass() {
		return pclass;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasePassenger [id=");
		builder.append(id);
		builder.append(", survived=");
		builder.append(survived);
		builder.append(", pclass=");
		builder.append(pclass);
		builder.append("]");
		return builder.toString();
	}
}
