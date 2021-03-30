package com.promineotech.sewingProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Garment;
import com.promineotech.sewingProject.entity.Notebook;
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
	
	//return all garments
	public Iterable<Garment> getAllGarments() {
		return repo.findAll();
	}
	
	//get particular garment
	public Garment getGarment(Long id) {
		return repo.findById(id).get();
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
		return repo.save(foundGarment);
	}
	
	//deletes garment
	public void deleteGarment(Long id) {
		repo.deleteById(id);
	}

}
