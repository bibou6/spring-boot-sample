package com.ad.realestateengine.security.rules;

public enum SecurityRoles {
	
	ACCESS_MY_USER, // Read connected user
	ACCESS_ALL_USER, // Read all User
	UPDATE_ALL_USER, // Write all User
	
	ACCESS_MY_CITY, // R/W Connected User Cities
	ACCESS_ALL_CITY, // R/W All Users Cities
	
	ACCESS_MY_CONTACT, // R/W Connected User Contacts
	ACCESS_ALL_CONTACT, // R/W All Contacts

}
