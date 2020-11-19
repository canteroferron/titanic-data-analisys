package com.titanic.data.analisys.app.statistics;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * This class represent a {@code List} of the {@code PassengersGroup} for performing accions
 * @author jcantero
 *
 */
public class PassengersGroups {
	
	
	/**
	 * {@code List} of the {@code PassengersGroup}
	 */
	private final List<PassengersGroup> groups;

	/**
	 * 
	 * @param groups {@code List} of the {@code PassengersGroup}
	 */
	public PassengersGroups(List<PassengersGroup> groups) {
		super();
		this.groups = groups;
	}

	
	/**
	 * @param statistical
	 * 			Statistical to performing actions
	 * @param cosumer 
	 * 			{@code BiConsumer} that receive a {@code List} of the {@code PassengersGroup} for performing actions
	 */
	public void toDo(String statistical, BiConsumer<String, List<PassengersGroup>> consumer) {
		consumer.accept(statistical, groups);
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("PassengersDataAcumulator [counts=");
		for (PassengersGroup count : groups) {
			builder.append("\n\t").append(count);
		}
		builder.append("]");
		return builder.toString();
	}
}
