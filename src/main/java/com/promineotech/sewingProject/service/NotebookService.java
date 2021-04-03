package com.promineotech.sewingProject.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.entity.Garment;
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
	public Iterable<Notebook> getNotebooksByUser(Long userId){
		return (repo.findByUserId(userId));
	}
	
	//get particular notebook
	public Notebook getNotebook(Long userId, Long id) {
		//return repo.findById(id).get();
		Optional<Notebook> responseNotebook = repo.findById(id);
		if (responseNotebook.isPresent()) {
			Notebook notebook = responseNotebook.get();
			if (notebook.getId() == id) {
				User user = notebook.getUser();
				if (user.getId() == userId) {
					return (notebook);
				}
			}
		}
		return (null);
	}
	
	//update notebook
	
	//the problem here is that it will update notebook regardless of if the userid in the path is correct or not.
	//I'm not sure how to validate the userId to make the code run.
	public Notebook updateNotebook(Notebook notebook, Long id, Long userId) throws Exception {
		User user = userRepo.findById(userId).get();
		//if (user == null) {
		//	throw new Exception("User not found.");
		//}
		if (user.getId() == userId) {
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
		return null;
	}

	//delete notebook	this doesn't work, will delete regardless of userId
	public void deleteNotebook(Long id, Long userId) throws Exception {
		User user = userRepo.findById(userId).get();
		if (user == null) {
			throw new Exception("User not found.");
		}
		repo.deleteById(id);
	}
}
