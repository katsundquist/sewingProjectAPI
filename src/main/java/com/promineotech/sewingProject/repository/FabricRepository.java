package com.promineotech.sewingProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.sewingProject.entity.Fabric;

public interface FabricRepository extends CrudRepository<Fabric, Long>{
	public List<Fabric> findByUserId(Long userId); 
}
