package com.springboot.gestion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="password")
public class Password implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=40, nullable = false)
	private String cuenta;
	
	@Column(length=60, nullable = false)
	private String pass;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private BDUsuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public BDUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BDUsuario usuario) {
		this.usuario = usuario;
	}
	
	
}
