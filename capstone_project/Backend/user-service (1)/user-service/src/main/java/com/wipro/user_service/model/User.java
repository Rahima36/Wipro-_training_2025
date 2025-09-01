package com.wipro.user_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String role; // CUSTOMER or ADMIN

    private String address;

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPassword(String encode) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	
	
	
}
