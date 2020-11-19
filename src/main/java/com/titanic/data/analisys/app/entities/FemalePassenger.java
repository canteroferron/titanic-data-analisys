package com.titanic.data.analisys.app.entities;



/**
 * This class represent a pojo for one passenger with gender female
 * @author jcantero
 *
 */
public class FemalePassenger extends BasePassenger implements Passenger {

	/**
	 * 
	 * @param id
	 * 			Identifier
	 * @param survived
	 * 			Survived flag
	 * @param pclass
	 * 			Passenger class
	 */
	public FemalePassenger(int id, short survived, short pclass) {
		super(id, survived, pclass);
	}

	@Override
	public Gender getGender() {
		return Gender.FEMALE;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FemalePassenger [getGender()=");
		builder.append(getGender());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
