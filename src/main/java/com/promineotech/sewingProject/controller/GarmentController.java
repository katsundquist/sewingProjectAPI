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
	
	 @RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createGarment(@RequestBody Garment garment, @PathVariable Long notebookId) {
		try {
			return new ResponseEntity<Object>(service.createGarment(garment, notebookId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}/fabrics", method=RequestMethod.POST)
	public ResponseEntity<Object> createGarmentFabric(@RequestBody Set<Long> fabricIds,  @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.createNewGarmentFabric(fabricIds, id), HttpStatus.CREATED);  
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}/patterns", method=RequestMethod.POST)
	public ResponseEntity<Object> createGarmentPattern(@RequestBody Set<Long> patternIds,  @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.createNewGarmentPattern(patternIds, id), HttpStatus.CREATED); 
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	// this put only updates name and description, needs an additional put 
	// to update fabrics and patterns.
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
