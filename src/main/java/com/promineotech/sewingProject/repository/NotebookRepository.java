package com.promineotech.sewingProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.sewingProject.entity.Notebook;

public interface NotebookRepository extends CrudRepository <Notebook, Long>{
	public List<Notebook> findByUserId(Long userId); 
	// this is so I can get the id information to be able to use it in the service class
}
