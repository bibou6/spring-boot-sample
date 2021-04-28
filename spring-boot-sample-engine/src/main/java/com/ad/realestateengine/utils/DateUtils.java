package com.ad.realestateengine.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
	
	/**
	 * Set the Correct timezone to date
	 * @param Date date
	 * @return Date
	 */
	public static Date withTimezone(Date date) {
		return Date.from(date.toInstant().atZone(ZoneId.of("Europe/Paris")).toInstant());
	}
	
	/**
	 * Set the Correct timezone to LocalDateTime
	 * @param LocalDateTime localDateTime
	 * @return Date
	 */
	public static Date withTimezone(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.of("Europe/Paris")).toInstant());
	}
	
	/**
	 * Set the Correct timezone to LocalDate (atStartOfDay)
	 * @param LocalDate localDateTime
	 * @return Date
	 */
	public static Date withTimezone(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.of("Europe/Paris")).toInstant());
	}

}
