package com.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 

public class PasswordEncode implements PasswordEncoder { 

	private PasswordEncoder passwordEncoder; 

	public PasswordEncode() { 
		this.passwordEncoder = new BCryptPasswordEncoder(); 
	} 

	public PasswordEncode(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder; 
	} 
	
	@Override 
	public String encode(CharSequence rawPassword) { 
		return passwordEncoder.encode(rawPassword); 
	} 

	@Override public boolean matches(CharSequence rawPassword, String encodedPassword) { 
		return passwordEncoder.matches(rawPassword, encodedPassword); 
	} 
}
