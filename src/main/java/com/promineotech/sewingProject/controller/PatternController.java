package com.promineotech.sewingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.sewingProject.entity.Pattern;
import com.promineotech.sewingProject.service.PatternService;

@RestController
@RequestMapping("/patterns")
public class PatternController {

	@Autowired
	private PatternService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllPatterns(){
		return new ResponseEntity<Object>(service.getPatterns(), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{patternId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getPattern(@PathVariable Long patternId) {
			return new ResponseEntity<Object>(service.getPatternById(patternId), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createPattern(@RequestBody Pattern pattern) {
		return new ResponseEntity<Object>(service.createPattern(pattern), HttpStatus.CREATED);
	}
}
