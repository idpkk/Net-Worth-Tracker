package com.networth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.networth.constants.Roles;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String username;
	private String password;
	private boolean isActive;
	private String roles;
	
}
