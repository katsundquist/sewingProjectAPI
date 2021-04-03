package com.promineotech.sewingProject.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Garment;
import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.entity.User;
import com.promineotech.sewingProject.repository.GarmentRepository;
import com.promineotech.sewingProject.repository.NotebookRepository;
//import com.promineotech.sewingProject.repository.UserRepository;

@Service
public class GarmentService {
	
	private static final Logger logger = LogManager.getLogger(GarmentService.class);

	@Autowired
	private GarmentRepository repo;
	
	@Autowired
	private NotebookRepository notebookRepo;
	
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
		//the SQL statment in the repo runs four, but might actually 
		//be more effecient with more data.  Leaving this code in  for reference.
		
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
	
	//create garment in database
	public Garment createGarment(Garment garment, Long notebookId) throws Exception {
		Notebook notebook = notebookRepo.findById(notebookId).get();
		if (notebook == null) {
			throw new Exception("Notebook not found.");
		} 
		garment.setNotebook(notebook);
		return repo.save(garment);
	}
	
	//update garment
	public Garment updateGarment(Garment garment, Long id) throws Exception {
		Garment foundGarment = repo.findById(id).get();
		if (foundGarment == null) {
			throw new Exception("Garment not found.");
		}
		foundGarment.setName(garment.getName());
		foundGarment.setDescription(garment.getDescription());
		foundGarment.setPatterns(garment.getPatterns());
		foundGarment.setFabrics(garment.getFabrics());
		return repo.save(foundGarment);
	}
	
	//deletes garment
	public void deleteGarment(Long id) {
		repo.deleteById(id);
	}

}
