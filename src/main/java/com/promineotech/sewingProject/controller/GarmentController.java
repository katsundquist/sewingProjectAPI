package com.promineotech.sewingProject.controller;

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
	public ResponseEntity<Object> getAllGarments(){
		return new ResponseEntity<Object>(service.getAllGarments(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{garmentId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getGarment(@PathVariable Long garmentId) {
		return new ResponseEntity<Object>(service.getGarment(garmentId), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createGarment(@RequestBody Garment garment, @PathVariable Long notebookId) {
		try {
			return new ResponseEntity<Object>(service.createGarment(garment, notebookId), HttpStatus.OK);
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
