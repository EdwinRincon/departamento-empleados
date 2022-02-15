package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formacion.app.apirest.entity.Empleado;

@Service
public interface EmpleadoService {

	List<Empleado> getEmpleados();

	Empleado getEmpleado(long id);

	Empleado postEmpleado(Empleado empleado);

	Empleado putEmpleado(Empleado empleado, long id);
	
	Empleado deleteEmpleado(long id);
	
	String loginEmpleado(String dni, String password);
}
