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
import com.promineotech.sewingProject.entity.Garment;
import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.service.NotebookService;

@RestController
@RequestMapping("/users/{userId}/notebooks")
public class NotebookController {
	
	@Autowired 
	private NotebookService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getUsersNotebooks(@PathVariable Long userId){
		Iterable<Notebook> notebooks = service.getNotebooksByUser(userId);
		if (notebooks != null) {
			Iterator<Notebook> it = notebooks.iterator();
			if (it.hasNext()) {
				return new ResponseEntity<Object>(notebooks, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>("No notebooks for user.", HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value="{notebookId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getNotebook(@PathVariable Long notebookId, @PathVariable Long userId){
		//return new ResponseEntity<Object>(service.getNotebook(notebookId), HttpStatus.OK);
		
		Notebook notebook = service.getNotebook(userId, notebookId);
		if (notebook != null) {
			return new ResponseEntity<Object>(notebook, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Requested notebook not found.", HttpStatus.NOT_FOUND);
		
	}
	
	//this isn't working properly
	@RequestMapping(value ="/{notebookId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateNotebook(@RequestBody Notebook notebook, @PathVariable Long notebookId,
												@PathVariable Long userId){
		try {
			return new ResponseEntity<Object>(service.updateNotebook(notebook, notebookId, userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createNotebook(@RequestBody Notebook notebook, @PathVariable Long userId){
		try {
			return new ResponseEntity<Object>(service.createNotebook(notebook, userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//this isn't working properly
	@RequestMapping(value="/{notebookId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteNotebook(@PathVariable Long notebookId, @PathVariable Long userId){
		try {
			service.deleteNotebook(notebookId, userId);
			return new ResponseEntity<Object> ("Deleted notebook with id:" + notebookId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Requested notebook not found.", HttpStatus.NOT_FOUND);
		}
		
	}

}
