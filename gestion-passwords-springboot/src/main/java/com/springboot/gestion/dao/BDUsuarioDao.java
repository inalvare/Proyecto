package com.springboot.gestion.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.gestion.entity.BDUsuario;

@Repository
public interface BDUsuarioDao extends CrudRepository<BDUsuario, Long>{
	
}
