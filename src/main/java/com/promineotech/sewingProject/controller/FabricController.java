package com.promineotech.sewingProject.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.sewingProject.entity.Fabric;
import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.service.FabricService;

@RestController
@RequestMapping("/users/{userId}/fabrics")
public class FabricController {

	@Autowired
	private FabricService service;
	
	
	//to get all of a user's fabrics.
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getUsersFabric(@PathVariable Long userId) {
		Iterable<Fabric> fabrics = service.getFabricsByUser(userId);
		if (fabrics != null) {
			Iterator<Fabric> it = fabrics.iterator();
			if (it.hasNext()) {
				return new ResponseEntity<Object>(fabrics, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>("No fabrics for user.", HttpStatus.NOT_FOUND);
	}
	
	//to get a particular fabric by id
	@RequestMapping(value="/{fabricId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getAFabric(@PathVariable Long fabricId, @PathVariable Long userId) {
		Fabric fabric = service.getFabric(userId, fabricId);
		if (fabric != null) {
			return new ResponseEntity<Object>(fabric, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Requested fabric not found.", HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createFabric(@RequestBody Fabric fabric, @PathVariable Long userId) {
		try {
			return new ResponseEntity<Object>(service.createFabric(fabric, userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateFabric(@RequestBody Fabric fabric, @PathVariable Long userId, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateFabric(fabric, userId,  id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete fabric.", HttpStatus.BAD_REQUEST);
		}
	}
	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFabric(@PathVariable Long id) {
		try {
			service.deleteFabric(id);
			return new ResponseEntity<Object>("Sucessfully deleted product with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete product.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
