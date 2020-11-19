package com.titanic.data.analisys.app.entities;

/**
 * This class represent a pojo for one passenger with gender male
 * @author jcantero
 *
 */
public class MalePassenger extends BasePassenger implements Passenger {

	
	/**
	 * 
	 * @param id
	 * 			Identifier
	 * @param survived
	 * 			Survived flag
	 * @param pclass
	 * 			Passenger class
	 */
	public MalePassenger(int id, short survived, short pclass) {
		super(id, survived, pclass);
	}

	@Override
	public Gender getGender() {
		return Gender.MALE;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MalePassenger [getGender()=");
		builder.append(getGender());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
