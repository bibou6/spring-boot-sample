package com.ad.realestateengine.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggerUtils {

	
	/**
	 * Transforms an object into String
	 * @param Object o
	 * @return The object Stringified
	 */
	public static String printObject(Object o) {
		ObjectMapper map = new ObjectMapper();
		try {
			return map.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return null;
		}
		
	}
	
}
