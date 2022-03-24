package com.springboot.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gestion.dao.BDUsuarioDao;
import com.springboot.gestion.entity.BDUsuario;

@Service
public class BDUsuarioServiceImpl implements BDUsuarioService{

	@Autowired
	private BDUsuarioDao usuarioDao;
	
	@Override
	public List<BDUsuario> findAll(){
		return (List<BDUsuario>) usuarioDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public BDUsuario findById(Long id){
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public BDUsuario save(BDUsuario usuario){
		return usuarioDao.save(usuario);
	}

	@Override
	public BDUsuario delete(Long id) {
		return null;
	}
}
