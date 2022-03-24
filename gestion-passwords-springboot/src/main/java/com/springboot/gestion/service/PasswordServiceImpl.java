package com.springboot.gestion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gestion.dao.PasswordDao;
import com.springboot.gestion.entity.Password;

@Service
public class PasswordServiceImpl implements PasswordService{

	@Autowired
	private PasswordDao passwordDao;
	
	@Override
	public List<Password> findAll(){
		return (List<Password>) passwordDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Password findById(Long id){
		return passwordDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Password save(Password password){
		return passwordDao.save(password);
	}

	@Override
	public Password delete(Long id) {
		return null;
	}
}
