package com.springboot.gestion.service;

import java.util.List;

import com.springboot.gestion.entity.BDUsuario;

public interface BDUsuarioService{

	public List<BDUsuario> findAll();
	
	public BDUsuario findById(Long id);

	public BDUsuario save(BDUsuario usuario);
	
	public BDUsuario delete(Long id);

}
