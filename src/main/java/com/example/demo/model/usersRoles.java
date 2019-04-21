package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.example.demo.types.RoleType;
 

@Entity
public class usersRoles implements Serializable{

	@Id
	private Long id;
	@Enumerated(EnumType.STRING)
	private RoleType role;

	public usersRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getRole() {
		return role;
	}

	
	public void setRole(RoleType role) {
		this.role = role;
	}
	
	
	
}
