package com.promineotech.sewingProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.entity.User;
import com.promineotech.sewingProject.repository.FabricRepository;
import com.promineotech.sewingProject.repository.UserRepository;

@Service
public class FabricService {

	private static final Logger logger = LogManager.getLogger(FabricService.class);

	@Autowired 
	private FabricRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	//get all fabrics by particular user, this is not right, but I'm not sure how 
	//to change it to only get fabrics by particular user.
	public Iterable<Fabric> getFabricsByUser(){
		return repo.findAll();
	}
	
	//get fabric by id
	public Fabric getFabric(Long id) {
		return repo.findById(id).get();
	}
	
	//create fabric
	public Fabric createFabric(Fabric fabric, Long userId) throws Exception {
		User user = userRepo.findById(userId).get();
		if (user == null) {
			throw new Exception("User not found.");
		}
		fabric.setUser(user);
		return repo.save(fabric);
	}
	
	//update fabric overall
	public Fabric updateFabric(Fabric fabric, Long id) {
		Fabric oldFabric = repo.findById(id).get();
		oldFabric.setFabricType(fabric.getFabricType());
		oldFabric.setFiberContent(fabric.getFiberContent());
		oldFabric.setYardage(fabric.getYardage());
		return repo.save(oldFabric);
	}
	
	//delete a fabric
	public void deleteFabric(Long id) {
		repo.deleteById(id);
	}
	
}