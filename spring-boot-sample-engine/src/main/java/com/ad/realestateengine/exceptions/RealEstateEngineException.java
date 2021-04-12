package com.ad.realestateengine.exceptions;

import com.ad.realestatemodel.common.ErrorCode;

public class RealEstateEngineException extends RuntimeException{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ErrorCode code;

	public RealEstateEngineException(ErrorCode code) {
		this.code = code;
		
	}
	
}
