package com.wipro.user_management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.user_management.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

	User findByEmailAndPassWord(String email, String passWord);
}