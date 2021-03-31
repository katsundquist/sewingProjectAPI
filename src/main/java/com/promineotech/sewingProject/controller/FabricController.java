package com.promineotech.sewingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.service.FabricService;

@RestController
@RequestMapping("/users/{userId)/fabric")
public class FabricController {

	@Autowired
	private FabricService service;
	
	
	//to get all of a user's fabrics.
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getUsersFabric() {
		return new ResponseEntity<Object>(service.getFabricsByUser(), HttpStatus.OK);
	}
	
	//to get a particular fabric
	@RequestMapping(value="/{fabricId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getAFabric(@PathVariable Long id) {
		return new ResponseEntity<Object>(service.getFabric(id), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createFabric(@RequestBody Fabric fabric, @PathVariable Long userId) {
		try {
			return new ResponseEntity<Object>(service.createFabric(fabric, userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		}
	
}
