package com.promineotech.sewingProject.service;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.User;
import com.promineotech.sewingProject.repository.UserRepository;

@Service
public class UserService {

	//private static final Logger Logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repo;
	
	//get specific user
	public User getUserById(Long id) throws Exception {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
			//logger.error("Exception occurred while trying to retrieve customer: " + id, e);
			throw e;
		}
	}
	
	//get all users
	public Iterable<User> getUsers(){
		return repo.findAll();
	}
	
	// post user
	public User createUser(User user) {
		return repo.save(user);
	}
	
	// delete user
	public void deleteUser(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			//logger.error("Exception occurred while trying to delete cusomter: " + id, e);
			throw new Exception("Unable to delete cusomter.");
			
		}
	}
}
