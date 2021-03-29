package com.promineotech.sewingProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.entity.User;
import com.promineotech.sewingProject.repository.NotebookRepository;
import com.promineotech.sewingProject.repository.UserRepository;

@Service
public class NotebookService {

	private static final Logger logger = LogManager.getLogger(NotebookService.class);

	@Autowired
	private NotebookRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	//make new notebook
	public Notebook createNotebook(Notebook notebook, Long userId) throws Exception {
		User user = userRepo.findById(userId).get();
		if (user == null) {
			throw new Exception("User not found.");
		}
		notebook.setUser(user);
		return repo.save(notebook);
	}
	
	//get all notebooks
	public Iterable<Notebook> getAllNotebooks(){
		return repo.findAll();
	}
	
	//get particular notebook
	public Notebook getNotebook(Long id) {
		return repo.findById(id).get();
	}
	
	//update notebook
	
	public Notebook updateNotebook(Notebook notebook, Long id) throws Exception {
		try {
			Notebook oldNotebook = repo.findById(id).get();
			oldNotebook.setName(notebook.getName());
			oldNotebook.setDescription(notebook.getDescription());
			return repo.save(oldNotebook);
		} catch (Exception e) {
			logger.error("Exception occurrec while trying to update notebook: " + id, e);
			throw new Exception("Unable to update notebook.");
		}
	}

	//delete notebook	
	public void deleteNotebook(Long id) {
		repo.deleteById(id);
	}
}
