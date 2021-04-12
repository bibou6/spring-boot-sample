package com.ad.realestatemodel.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
	
	USER_EXIST_BY_EMAIL("0001", "Email is already taken!"),
	USER_EXIST_BY_USERNAME("0002", "Username is already taken!"),
	USER_NOT_FOUND("0003", "The user was not found in the database"),
	USER_ACCESS_DENIED("0004", "The user has no sufficient rights to access that resource"),
	USER_LOGIN_REQUIRED("0005", "User must be connected to access this resource");
	
	String code;
	
	String message;
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}

}
