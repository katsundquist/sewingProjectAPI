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
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Garment {

	private Long id;
	private String name;
	private String description;
	private Set<Pattern> patterns;
	private Set<Fabric> fabrics;
	
	@JsonIgnore
	private Notebook notebook;

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

	@ManyToOne
	@JoinColumn(name = "notebookId")
	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "garment_pattern",
//	           joinColumns = @JoinColumn(name = "garment_id", referencedColumnName = "id"),
//	           inverseJoinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "id"))
//	public Set<Pattern> getPatterns() {
//		return patterns;
//	}
//
//	public void setPatterns(Set<Pattern> patterns) {
//		this.patterns = patterns;
//	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "garment_fabric",
               joinColumns = @JoinColumn(name = "garment_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "fabric_id", referencedColumnName = "id"))
	public Set<Fabric> getFabrics() {
		return fabrics;
	}

	public void setFabrics(Set<Fabric> fabrics) {
		this.fabrics = fabrics;
	}
}
