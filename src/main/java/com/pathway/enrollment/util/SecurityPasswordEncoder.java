package com.pathway.enrollment.util;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class SecurityPasswordEncoder {

	
	/**
	 * The method will encrypt the password
	 * @param password
	 * @return
	 */
	public String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
	
}
