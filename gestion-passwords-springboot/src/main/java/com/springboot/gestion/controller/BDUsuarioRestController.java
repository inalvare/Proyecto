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

import com.springboot.gestion.service.BDUsuarioService;
import com.springboot.gestion.entity.BDUsuario;
import com.springboot.gestion.entity.Usuario;

@RestController
@RequestMapping("/api")
public class BDUsuarioRestController {
	
	@Autowired
	private BDUsuarioService usuarioService;
	
	@GetMapping({"/usuarios"})
	public List<BDUsuario> index(){
		return usuarioService.findAll();
	}
	
	@GetMapping("buscarUsuario/{id}")
	public ResponseEntity<?> findUsuarioById(@PathVariable Long id) {
		BDUsuario usuario=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		usuario=usuarioService.findById(id);
		if (usuario==null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BDUsuario>(usuario,HttpStatus.OK);
	}	
	
	@PutMapping("nuevoUsuario")
	@ResponseStatus(HttpStatus.CREATED)
	public BDUsuario saveCliente(@RequestBody BDUsuario usuario) {
		return usuarioService.save(usuario);
	}
	
	@PutMapping("actualizarUsuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateUsuario(@RequestBody BDUsuario usuario, @PathVariable Long id) {
		BDUsuario usuarioActual=usuarioService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (usuarioActual==null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setPassword(usuario.getPassword());
			usuarioActual.setEmail(usuario.getEmail());
			
			usuarioService.save(usuarioActual);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "El al actualizar el usuario");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El usuario se ha actualizado con exito");
		response.put("usuario",usuarioActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("eliminarUsuario/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
		BDUsuario usuarioActual= usuarioService.findById(id);
		
		Map<String, Object> response=new HashMap<>();
		
		response.put("usuario",usuarioActual);
		
		if(usuarioActual==null) {
			response.put("mensaje", "Error: no se pudo eliminar");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		try {
			
			usuarioActual=usuarioService.delete(id);
			
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El usuario se ha eliminado con exito");
		response.put("usuario",usuarioActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
