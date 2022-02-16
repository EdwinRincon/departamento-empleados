package com.formacion.app.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.apirest.entity.Empleado;

@Repository
public interface EmpleadoDAO  extends CrudRepository<Empleado, Long>{
	
	@Query(value = "SELECT COUNT(*) NOMBRE FROM EMPLEADOS WHERE DNI = ?1 AND PASSWORD= ?2", nativeQuery = true)
	  int loginEmpleado(String dni, String password);
	
	 Empleado findByDni(String dni);
	 Empleado findByDniAndPassword(String dni,String password);
	 
	 Empleado findByUsername(String username);
}
