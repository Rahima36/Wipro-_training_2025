package com.wipro.user_management.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

	public static String getEncryptedPassword(String passWord, String salt) {
		// TODO Auto-generated method stub
		return null;
	}

	}