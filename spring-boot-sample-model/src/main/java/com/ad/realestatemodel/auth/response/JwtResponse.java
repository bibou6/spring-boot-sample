package com.ad.realestatemodel.auth.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	String token;
	
	Long id;
	
	String username;
	
	String email;
	
	List<String> roles;
	
}
