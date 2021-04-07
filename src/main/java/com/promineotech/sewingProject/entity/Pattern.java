package com.promineotech.sewingProject.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pattern {

	private Long id;
	private String name;
	private String description;
	
	@JsonIgnore
	private Set<Garment> garments;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
//
//	@ManyToMany(mappedBy="patterns") //,cascade = CascadeType.ALL
//	public Set<Garment> getGarments() {
//		return garments;
//	}
//
//	public void setGarments(Set<Garment> garments) {
//		this.garments = garments;
//	} 

}
