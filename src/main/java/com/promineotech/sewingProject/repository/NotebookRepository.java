package com.promineotech.sewingProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.sewingProject.entity.Notebook;
//why does it want this import statement?  It's mad if it's
//not there?

public interface NotebookRepository extends CrudRepository <Notebook, Long>{

}
