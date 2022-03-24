package com.springboot.gestion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gestion.entity.Password;
import com.springboot.gestion.service.PasswordService;

@RestController
@RequestMapping("/api")
public class PasswordRestController {

	@Autowired
	private PasswordService passwordService;
	
	@GetMapping({"/contrasenas"})
	public List<Password> index(){
		return passwordService.findAll();
	}
	
	@GetMapping("buscarContrasena/{id}")
	public ResponseEntity<?> findClienteById(@PathVariable Long id) {
		Password password=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			passwordService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		password=passwordService.findById(id);
		if (password==null) {
			response.put("mensaje", "El password ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Password>(password,HttpStatus.OK);
	}	
	
	@PutMapping("nuevaContrasena")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveCliente(@PathVariable Password password) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			passwordService.save(password);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "El password no se ha creado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El password se ha creado con exito");
		response.put("password",password);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}	
	
	@PutMapping("actualizarContrasena/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateCliente(@RequestBody Password password, @PathVariable Long id) {
		Password passwordActual=passwordService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (passwordActual==null) {
			response.put("mensaje", "El password ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			passwordActual.setCuenta(password.getCuenta());
			passwordActual.setPass(password.getPass());
			
			passwordService.save(passwordActual);			
			
		} catch(DataAccessException e) {
			response.put("mensaje", "El al actualizar el password");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El password se ha actualizado con exito");
		response.put("password",passwordActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("eliminarContrasena/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Password passwordActual= passwordService.findById(id);
		
		Map<String, Object> response=new HashMap<>();
		
		response.put("password",passwordActual);
		
		if(passwordActual==null) {
			response.put("mensaje", "Error: no se pudo eliminar");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		try {
			
			passwordActual=passwordService.delete(id);
			
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el password");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El password se ha eliminado con exito");
		response.put("password",passwordActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
