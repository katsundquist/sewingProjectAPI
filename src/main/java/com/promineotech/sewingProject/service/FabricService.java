package com.promineotech.sewingProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.entity.User;
import com.promineotech.sewingProject.repository.FabricRepository;
import com.promineotech.sewingProject.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class FabricService {

	private static final Logger logger = LogManager.getLogger(FabricService.class);

	@Autowired 
	private FabricRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	//get all fabrics by particular user
	public Iterable<Fabric> getFabricsByUser(Long userId){
		return(repo.findByUserId(userId));
//		Optional<User> userResponse = userRepo.findById(userId);
//		if (userResponse.isPresent()) {
//			User user = userResponse.get();
//			return(user.getFabrics());
//		}
//		return(new ArrayList<Fabric>());
	}
	
	//get fabric by id
	//public Fabric getFabric(Long id) {
		//return repo.findById(id).get();
	//}
	
	public Fabric getFabric(Long userId, Long id) {
		Optional <Fabric> responseFabric = repo.findById(id);
		if (responseFabric.isPresent()) {
			Fabric fabric = responseFabric.get();
			if (fabric.getId() == id) {
				User user = fabric.getUser();
				if (user.getId() == userId) {
					return (fabric);
				}
			}
		}
		return (null);
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
	
	//needs FabricController
	public Fabric updateFabric(Fabric fabric, Long userId, Long id) throws Exception {
		//check if this User and if statement actually works.
		User user = userRepo.findById(userId).get();
		if (user == null) {
			throw new Exception("User not found.");
		}
		Fabric oldFabric = repo.findById(id).get();
		oldFabric.setFabricType(fabric.getFabricType());
		oldFabric.setFiberContent(fabric.getFiberContent());
		oldFabric.setYardage(fabric.getYardage());
		return repo.save(oldFabric);
	}
	
	//delete a fabric
	
	//needs FabricController
	public void deleteFabric(Long id) {
		repo.deleteById(id);
	}
	
}
