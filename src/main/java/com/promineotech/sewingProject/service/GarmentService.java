package com.promineotech.sewingProject.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.entity.Garment;
import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.entity.Pattern;
import com.promineotech.sewingProject.entity.User;
import com.promineotech.sewingProject.repository.FabricRepository;
import com.promineotech.sewingProject.repository.GarmentRepository;
import com.promineotech.sewingProject.repository.NotebookRepository;
import com.promineotech.sewingProject.repository.PatternRepository;
//import com.promineotech.sewingProject.repository.UserRepository;

@Service
public class GarmentService {
	
	private static final Logger logger = LogManager.getLogger(GarmentService.class);

	@Autowired
	private GarmentRepository repo;
	
	@Autowired
	private NotebookRepository notebookRepo;
	
	@Autowired
	private FabricRepository fabricRepo;
	
	@Autowired
	private PatternRepository patternRepo;
	
	//return all garments in notebook
	public Iterable<Garment> getNotebooksGarments(Long notebookId) {
		return(repo.findByNotebookId(notebookId));
	}
	
	
	//get particular garment
	public Garment getGarment(Long userId, Long notebookId, Long id) {
		Optional<Garment> responseGarment = repo.findGarment(userId, notebookId, id);
		if (responseGarment.isPresent()) {
			return(responseGarment.get());
		}
		return(null);
		
		//this code does three queries when program runs. 
		//the SQL statement in the repo runs four, but might actually 
		//be more efficient with more data.  Leaving this code in  for reference.
		// in the real world, I would never push this code to production, but because
		// this is a project for school, I want to leave this here for now.
		
//		Optional<Garment> responseGarment = repo.findById(id);
//		if (responseGarment.isPresent()) {
//			Garment garment = responseGarment.get();
//			Notebook notebook = garment.getNotebook();
//			if (notebook.getId() == notebookId) {
//				User user = notebook.getUser();
//			    if (user.getId() == userId) {
//				   return(garment);
//			    }
//			}
//		}
//		return(null);
	}
	
	//create garment in database, but does not tie fabrics or patterns
	
	public Garment createGarment(Garment garment, Long notebookId) throws Exception {
		Notebook notebook = notebookRepo.findById(notebookId).get();
		if (notebook == null) {
			throw new Exception("Notebook not found.");
		} 
		garment.setNotebook(notebook);
		return repo.save(garment);
	}

	// adds fabric to created garment
	
	public Garment createNewGarmentFabric(Set<Long> fabricIds, Long id){
		try {
			Garment garment = repo.findById(id).get();
			garment.setFabrics(convertToFabricSet(fabricRepo.findAllById(fabricIds)));
			addGarmentToFabrics(garment);
			return repo.save(garment);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to create new garment");
			throw e;
		}
	}
	
	// adds pattern to created garment
	public Garment createNewGarmentPattern(Set<Long> patternIds, Long id){
		try {
			Garment garment = repo.findById(id).get();
			garment.setPatterns(convertToPatternSet(patternRepo.findAllById(patternIds)));
			addGarmentToPatterns(garment);
			return repo.save(garment);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to create new garment");
			throw e;
		}
	}
	
	private void addGarmentToFabrics(Garment garment) {
		Set<Fabric> fabrics = garment.getFabrics();
		for(Fabric fabric : fabrics) {
			fabric.getGarments().add(garment);
		}
	}

	private void addGarmentToPatterns(Garment garment) {
		Set<Pattern> patterns = garment.getPatterns();
		for(Pattern pattern : patterns) {
			 pattern.getGarments().add(garment);
		}
	}
	
	private Set<Fabric> convertToFabricSet(Iterable<Fabric> iterable) {
		Set<Fabric> set = new HashSet<Fabric>();
		for (Fabric fabric : iterable) {
			set.add(fabric);
		}
		return set;
	}
	
	private Set<Pattern> convertToPatternSet(Iterable<Pattern> iterable) {
		Set<Pattern> set = new HashSet<Pattern>();
		for(Pattern pattern : iterable) {
			set.add(pattern);
		}
		return set;
	}
	
	// update garment, only working for name and description.  Needs additional work to 
	// update fabric and pattern as well
	public Garment updateGarment(Garment garment, Long id) throws Exception {
		Garment foundGarment = repo.findById(id).get();
		if (foundGarment == null) {
			throw new Exception("Garment not found.");
		}
		foundGarment.setName(garment.getName());
		foundGarment.setDescription(garment.getDescription());
		return repo.save(foundGarment);
	}
	
	//deletes garment
	public void deleteGarment(Long id) {
		repo.deleteById(id);
	}

}
