package com.springboot.gestion.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.gestion.entity.Password;


@Repository
public interface PasswordDao extends CrudRepository<Password, Long>{
	
}
