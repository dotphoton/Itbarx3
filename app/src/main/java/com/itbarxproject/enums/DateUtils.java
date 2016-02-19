package com.itbarxproject.enums;

/**
 * TODO: Add a class header comment!
 */
public enum DateUtils {
	MINUTE("Min."),MINUTES("Mins."),HOUR("Hour"),HOURS("Hours"),DAY("Day"),DAYS("Days"),MONTH("Month"),MONTHS("Months"),
	YEAR("Year"),YEARS("Years"),MINUS("MINUS");

	private final String val;

	 DateUtils(String value) {
		val = value;
	}

	@Override
	public String toString() {

		return val;
	}
}
