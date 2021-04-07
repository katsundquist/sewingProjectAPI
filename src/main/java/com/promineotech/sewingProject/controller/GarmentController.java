package com.promineotech.sewingProject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.sewingProject.entity.Garment;
import com.promineotech.sewingProject.service.GarmentService;

@RestController
@RequestMapping("/users/{userId}/notebooks/{notebookId}/garments")
public class GarmentController {

	@Autowired
	private GarmentService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllGarments(@PathVariable Long userId, @PathVariable Long notebookId){
		return new ResponseEntity<Object>(service.getNotebooksGarments(notebookId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{garmentId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getGarment(@PathVariable Long userId, @PathVariable Long notebookId,
                                             @PathVariable Long garmentId) {
		Garment garment = service.getGarment(userId, notebookId, garmentId);
		if (garment != null) {
			return new ResponseEntity<Object>(garment, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Requested garment not found", HttpStatus.NOT_FOUND);
	}
	
	//createNewGarment(Set<Long> fabricIds, Set<Long> patternIds, Long notebookId
	
	/*
	 * 
	 * The old post I had before trying to allow for many to many.
	 * 
	 * @RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createGarment(@RequestBody Garment garment, @PathVariable Long notebookId) {
		try {
			return new ResponseEntity<Object>(service.createGarment(garment, notebookId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	 * 
	 * 
	 */
	
	
	// I dont think I'm allowed to have two @RequestBodys in one.  Why does that information neeed to be 
	//accessible here? @RequestBody Set<Long> patternId,
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createGarment(@RequestBody Set<Long> fabricIds,  @PathVariable Long notebookId) {
		try {
			return new ResponseEntity<Object>(service.createNewGarment(fabricIds, notebookId), HttpStatus.CREATED);  //patternId removed from between
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{garmentId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateGarment(@RequestBody Garment garment, @PathVariable Long garmentId) {
		try {
			return new ResponseEntity<Object>(service.updateGarment(garment, garmentId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{garmentId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteGarment(@PathVariable Long garmentId) {
		service.deleteGarment(garmentId);
		return new ResponseEntity<Object>("Deleted garment with id: " + garmentId, HttpStatus.OK);
	}

}
