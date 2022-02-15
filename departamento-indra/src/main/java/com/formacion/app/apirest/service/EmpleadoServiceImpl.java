package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.apirest.dao.EmpleadoDAO;
import com.formacion.app.apirest.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	EmpleadoDAO empleadoDAO;
	
	@Transactional(readOnly = true)
	@Override
	public List<Empleado> getEmpleados() {
		return (List<Empleado>) this.empleadoDAO.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Empleado getEmpleado(long id) {
		return this.empleadoDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Empleado postEmpleado(Empleado empleado) {
		return this.empleadoDAO.save(empleado);
	}

	@Override
	public Empleado putEmpleado(Empleado empleado, long id) {
		Empleado toUpdateEmpleado = getEmpleado(id);

		if (toUpdateEmpleado == null)
			return null;

		toUpdateEmpleado.setNombre(empleado.getNombre());
		toUpdateEmpleado.setDni(empleado.getDni());
		toUpdateEmpleado.setTelefono(empleado.getTelefono());
		toUpdateEmpleado.setPassword(empleado.getPassword());
		toUpdateEmpleado.setSalario(empleado.getSalario());
		return this.empleadoDAO.save(toUpdateEmpleado);
	}

	@Transactional
	@Override
	public Empleado deleteEmpleado(long id) {
		Empleado deletedEmpleado = this.empleadoDAO.findById(id).orElse(null);
		this.empleadoDAO.deleteById(id);
		return deletedEmpleado;
	}

	@Transactional
	@Override
	public String loginEmpleado(String dni, String password) {
		int loginEmpleado = this.empleadoDAO.loginEmpleado(dni, password);
		String loginMensaje = "";

		// SI encuentra un empleado con ese dni y pwd
		if (loginEmpleado == 1) {
			return "Has iniciado sesion";
		} else {
			// Verificar que dato se ha enviado mal, dni o pwd
			Empleado isDni = this.empleadoDAO.findByDni(dni);
			if (isDni == null) {
				loginMensaje += "DNI incorrecto";
			}
			Empleado isPassword = this.empleadoDAO.findByDniAndPassword(dni, password);
			if (isPassword == null) {
				loginMensaje += " contraseña incorrecta";
			}

		}
		return loginMensaje;
	}

}
