package com.promineotech.sewingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.sewingProject.entity.Notebook;
import com.promineotech.sewingProject.service.NotebookService;

@RestController
@RequestMapping("/users/{userId}/notebooks")
public class NotebookController {
	
	@Autowired 
	private NotebookService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllNotebooks(){
		return new ResponseEntity<Object>(service.getAllNotebooks(), HttpStatus.OK);
	}
	
	@RequestMapping(value="{notebookId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getNotebook(@PathVariable Long notebookId){
		return new ResponseEntity<Object>(service.getNotebook(notebookId), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{notebookId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateNotebook(@RequestBody Notebook notebook, @PathVariable Long notebookId){
		try {
			return new ResponseEntity<Object>(service.updateNotebook(notebook, notebookId), HttpStatus.OK);
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
	
	@RequestMapping(value="/{notebookId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteNotebook(@PathVariable Long notebookId){
		service.deleteNotebook(notebookId);
		return new ResponseEntity<Object> ("Deleted notebook with id:" + notebookId, HttpStatus.OK);
	}

}
