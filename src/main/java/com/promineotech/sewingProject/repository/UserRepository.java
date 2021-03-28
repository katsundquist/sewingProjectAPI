package com.promineotech.sewingProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.sewingProject.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
