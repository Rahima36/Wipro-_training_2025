package com.wipro.user_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column
	String name;
	
	@Column
	String email;
	
	@Column
	String passWord;
	
	@Column
	String salt;

	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSalt() {
		// TODO Auto-generated method stub
		return null;
	}

	
}