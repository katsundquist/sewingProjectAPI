package com.promineotech.sewingProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.sewingProject.entity.Pattern;
import com.promineotech.sewingProject.repository.PatternRepository;

@Service
public class PatternService {

	private static final Logger logger = LogManager.getLogger(PatternService.class);
	
	@Autowired
	private PatternRepository repo;
	
	//get all patterns
	public Iterable<Pattern> getPatterns() {
		return repo.findAll();
	}
	
	//get pattern by id
	public Pattern getPatternById(Long id)  {
		return repo.findById(id).get();
	}
	
	//create pattern
	public Pattern createPattern(Pattern pattern) {
		return repo.save(pattern);
	}

}
