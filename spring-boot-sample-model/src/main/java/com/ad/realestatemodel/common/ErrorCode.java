package com.ad.realestatemodel.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
	
	USER_EXIST_BY_EMAIL(409,"0001", "Email is already taken!"),
	USER_EXIST_BY_USERNAME(409,"0002", "Username is already taken!"),
	USER_NOT_FOUND(404, "0003", "The user was not found in the database"),
	USER_ACCESS_DENIED(403, "0004", "The user has no sufficient rights to access that resource"),
	USER_LOGIN_REQUIRED(401, "0005", "User must be connected to access this resource");
	
	Integer httpStatus;
	
	String code;
	
	String message;
	
	private ErrorCode(Integer httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Integer getHttpStatus() {
		return this.httpStatus;
	}

}
