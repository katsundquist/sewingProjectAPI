package com.promineotech.sewingProject.entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	private Long id;
	private String username;
	private Set<Notebook> notebooks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@OneToMany(mappedBy = "user")
	public Set<Notebook> getNotebooks() {
		return notebooks;
	}

	public void setNotebooks(Set<Notebook> notebooks) {
		this.notebooks = notebooks;
	}

}
