package com.wipro.user_management.service;

import java.util.List;

import com.wipro.user_management.dto.Token;
import com.wipro.user_management.entity.User;

public interface UserService {

	List<User> findAll();
	User findById(int id);
	void save(User user);
	void deleteById(int id);
	Token login(User user);
	
}