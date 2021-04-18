package com.promineotech.sewingProject.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fabric {

	private Long id;
	private String fabricType;
	private String fiberContent;
	private double yardage;
	
	@JsonIgnore 
	private User user;
	
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

	public String getFabricType() {
		return fabricType;
	}

	public void setFabricType(String fabricType) {
		this.fabricType = fabricType;
	}

	public String getFiberContent() {
		return fiberContent;
	}

	public void setFiberContent(String fiberContent) {
		this.fiberContent = fiberContent;
	}

	public double getYardage() {
		return yardage;
	}

	public void setYardage(double yardage) {
		this.yardage = yardage;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany(mappedBy = "fabrics")
	public Set<Garment> getGarments() {
		return garments;
	}

	public void setGarments(Set<Garment> garments) {
		this.garments = garments;
	} 
}
