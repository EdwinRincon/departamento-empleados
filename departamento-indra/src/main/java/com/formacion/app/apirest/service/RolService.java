package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formacion.app.apirest.entity.Rol;

@Service
public interface RolService {

	List<Rol> getRoles();

	Rol getRol(long id);

	Rol postRol(Rol rol);

	Rol putRol(Rol rol, long id);
	
	Rol deleteRol(long id);
	
}
