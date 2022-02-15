package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.apirest.dao.RolDAO;
import com.formacion.app.apirest.entity.Rol;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolDAO rolDAO;
	
	@Transactional(readOnly = true)
	@Override
	public List<Rol> getRoles() {
		return (List<Rol>) this.rolDAO.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Rol getRol(long id) {
		return this.rolDAO.findById(id).orElse(null);
	}

	@Override
	public Rol postRol(Rol rol) {
		return this.rolDAO.save(rol);
	}

	@Override
	public Rol putRol(Rol rol, long id) {
		Rol toUpdateRol = getRol(id);

		if (toUpdateRol == null)
			return null;

		toUpdateRol.setTipo_empleado(rol.getTipo_empleado());
		return this.rolDAO.save(toUpdateRol);
	}

	@Override
	public Rol deleteRol(long id) {
		Rol deletedRol = this.rolDAO.findById(id).orElse(null);
		this.rolDAO.deleteById(id);
		return deletedRol;
	}

}
