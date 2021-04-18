package com.promineotech.sewingProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.entity.Garment;

public interface GarmentRepository extends CrudRepository<Garment, Long>{
    @Query("select g from Garment g "+
           "inner join g.notebook n " +
    	   "ON n.id = g.notebook " +
           "inner join n.user u " +
    	   "ON n.user = u.id " +
    	   "WHERE u.id = :user_id " +
           "AND n.id = :notebook_id " +
    	   "AND g.id = :id")
    public Optional<Garment> findGarment(@Param("user_id") Long userId, 
    		                             @Param("notebook_id") Long notebookId, 
    		                             @Param("id") Long id);
    
    public List<Garment> findByNotebookId(Long notebookId); 
    
}
