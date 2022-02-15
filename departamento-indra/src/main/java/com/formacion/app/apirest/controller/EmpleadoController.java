package com.formacion.app.apirest.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.apirest.entity.Empleado;
import com.formacion.app.apirest.service.EmpleadoServiceImpl;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	EmpleadoServiceImpl empleadoServiceImpl;

	@GetMapping("/all")
	public ResponseEntity<List<Empleado>> getAllEmpleados() {
		return new ResponseEntity<>(empleadoServiceImpl.getEmpleados(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpleado(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empleado = empleadoServiceImpl.getEmpleado(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (empleado == null) {
			response.put("mensaje", "El empleado ID: " + id.toString() + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> postEmpleado(@RequestBody Empleado empleado) {
		Empleado newEmpleado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newEmpleado = empleadoServiceImpl.postEmpleado(empleado);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al guardar en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (newEmpleado == null) {
			response.put("mensaje", "El empleado: " + empleado.getNombre()+" no se ha guardado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("empleado", newEmpleado);
		response.put("mensaje", "Se ha guardado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginEmpleado(@RequestBody Empleado empleado) {
		String loginEmpleado = "";
		Map<String, Object> response = new HashMap<>();
		try {
			loginEmpleado = empleadoServiceImpl.loginEmpleado(empleado.getDni(),empleado.getPassword());
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al consultar en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("login", loginEmpleado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putEmpleado(@RequestBody Empleado empleado, @PathVariable long id) {
		Empleado editEmpleado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			editEmpleado = empleadoServiceImpl.putEmpleado(empleado, id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al editar al empleado");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (editEmpleado == null) {
			response.put("mensaje", "No se han hecho cambios para este empleado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("empleado", editEmpleado);
		response.put("mensaje", "Se ha editado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable long id) {
		Empleado deleteEmpleado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			deleteEmpleado = empleadoServiceImpl.deleteEmpleado(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			if (deleteEmpleado == null) {
				response.put("error2", "No existe el empleado: " + id + " o no se puede eliminar");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}
			response.put("mensaje", "Error al eliminar al empleado");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("empleado", deleteEmpleado);
		response.put("mensaje", "Se ha eliminado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
