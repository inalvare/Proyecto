package com.springboot.gestion.service;

import java.util.List;

import com.springboot.gestion.entity.Password;

public interface PasswordService {

	public List<Password> findAll();
	
	public Password findById(Long id);

	public Password save(Password password);
	
	public Password delete(Long id);
}
