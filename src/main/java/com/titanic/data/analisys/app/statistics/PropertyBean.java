package com.titanic.data.analisys.app.statistics;


/**
 * This class represent a tuple pojo with a key and a value
 * 
 * @author jcantero
 *
 */
public class PropertyBean {
	
	/** 
	 * Key 
	 */
	private final String key;
	
	/**
	 * Value
	 */
	private final Double value;

	/**
	 * 	
	 * @param key
	 * 			Key
	 * @param value
	 * 			Value
	 */
	public PropertyBean(String key, Double value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * 
	 * @return
	 * 		Key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 
	 * @return
	 * 		Value
	 */
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertyBean [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
	
}
