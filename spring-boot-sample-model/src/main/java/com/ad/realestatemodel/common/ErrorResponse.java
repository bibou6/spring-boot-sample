package com.ad.realestatemodel.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	public ErrorResponse(Integer httpCode, ErrorCode errorCode) {
		this.httpCode = httpCode;
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.key = errorCode.name();
	}
	
	Integer httpCode;
	
	String code;
	
	String key;
	
	String message;

}
